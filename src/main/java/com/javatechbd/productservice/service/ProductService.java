package com.javatechbd.productservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.javatechbd.productservice.common.PredicateFactory;
import com.javatechbd.productservice.dto.request.ProductDto;
import com.javatechbd.productservice.dto.response.ProductRest;
import com.javatechbd.productservice.dto.search.ProductSearchDTO;
import com.javatechbd.productservice.entity.ProductEntity;
import com.javatechbd.productservice.repository.ProductRepository;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

  private final Environment environment;
  private final ProductRepository productRepository;
  private final EntityValidationService entityValidationService;
  private final KafkaTemplate<String, String> kafkaTemplate;

  public void createNewProduct(ProductDto productDto) {

    var productEntity = new ProductEntity();
    var brandEntity = entityValidationService.validateBrand(productDto.getBrandId());
    BeanUtils.copyProperties(productDto, productEntity);
    productEntity.setBrand(brandEntity);

    productRepository.save(productEntity);
    sendProductToKafka(productEntity);

  }

  public ProductRest getProductById(Long id) {

    var productEntity = entityValidationService.validateProduct(id);

    var response = new ProductRest();
    BeanUtils.copyProperties(productEntity, response);

    return response;
  }


  public void updateProduct(Long id, ProductDto productDto) {

    var productEntity = entityValidationService.validateProduct(id);
    var brandEntity = entityValidationService.validateBrand(productDto.getBrandId());

    productEntity.setProductName(productDto.getProductName());
    productEntity.setBrand(brandEntity);
    productEntity.setPurchasePrice(productDto.getPurchasePrice());
    productEntity.setSalesPrice(productDto.getSalesPrice());

    productRepository.save(productEntity);
  }

  public void deleteProductById(Long id) {

    var productEntity = entityValidationService.validateProduct(id);

    productRepository.deleteById(productEntity.getId());

  }

  public List<ProductRest> getProductList() {

    return productRepository.findAll().stream()
        .map(itm -> getProductRest(itm))
        .sorted(Comparator.comparing(ProductRest::getProductName))
        .collect(Collectors.toList());
  }

  public List<ProductRest> searchByProductName(String productName) {

    return getProductList().stream()
        .filter(itm -> itm.getProductName().toLowerCase().contains(productName.toLowerCase()))
        .collect(Collectors.toList());
  }

  public Page<ProductRest> searchProduct(ProductSearchDTO searchDTO) {

    Predicate predicate = PredicateFactory.productSearchPredicate(searchDTO);

    Pageable pageable = PageRequest.of(searchDTO.getPage(), searchDTO.getSize(),
        Sort.by("productName").ascending());

    Page<ProductEntity> resultPageList = productRepository.findAll(predicate, pageable);

    var resultRestList = resultPageList.getContent().stream()
        .map(itm -> getProductRest(itm))
        .collect(Collectors.toList());

    return new PageImpl<>(resultRestList, pageable, resultPageList.getTotalElements());

  }

  public List<ProductRest> searchProductById(ProductSearchDTO searchDTO) {

    Predicate predicate = PredicateFactory.productSearchPredicate(searchDTO);

    var iterable = productRepository.findAll(predicate);

    return StreamSupport.stream(iterable.spliterator(), false)
        .map(itm -> getProductRest(itm))
        .collect(Collectors.toList());
  }

  private ProductRest getProductRest(ProductEntity itm) {
    var res = new ProductRest();
    BeanUtils.copyProperties(itm, res);
    Optional.ofNullable(itm.getBrand())
        .ifPresent(brand -> {
          res.setBrandId(brand.getId());
        });
    return res;
  }

  public void sendProductToKafka(ProductEntity productEntity) {
    var productString = getProductAsString(productEntity);
    ListenableFuture<SendResult<String, String>> future =
        this.kafkaTemplate.send(environment.getProperty("application.topic.product-request"), productString);

    future.addCallback(new ListenableFutureCallback<>() {
      @Override
      public void onSuccess(SendResult<String, String> result) {
        log.info("Sent ProductEntity: " + productString + " with offset: " + result.getRecordMetadata().offset());
      }

      @Override
      public void onFailure(Throwable ex) {
        log.error("Unable to send productString : " + productString, ex);
      }
    });
  }

  private String getProductAsString(ProductEntity productEntity) {
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    try {
      return ow.writeValueAsString(productEntity);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
