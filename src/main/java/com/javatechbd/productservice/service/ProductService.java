package com.javatechbd.productservice.service;

import com.javatechbd.productservice.dto.request.ProductDto;
import com.javatechbd.productservice.dto.response.ProductResponse;
import com.javatechbd.productservice.entity.ProductEntity;
import com.javatechbd.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final EntityValidationService entityValidationService;

    public void createNewProduct(ProductDto productDto) {

        var productEntity = new ProductEntity();
        BeanUtils.copyProperties(productDto, productEntity);

        productRepository.save(productEntity);
    }

    public ProductResponse getProductById(Long id) {

        var productEntity = entityValidationService.validateProduct(id);

        var response = new ProductResponse();
        BeanUtils.copyProperties(productEntity, response);

        return response;
    }


    public void updateProduct(Long id, ProductDto productDto) {

        var productEntity = entityValidationService.validateProduct(id);

        productEntity.setProductName(productDto.getProductName());
        productEntity.setBrand(productDto.getBrand());
        productEntity.setPurchasePrice(productDto.getPurchasePrice());
        productEntity.setSalesPrice(productDto.getSalesPrice());

        productRepository.save(productEntity);
    }

    public void deleteProductById(Long id) {

        var productEntity = entityValidationService.validateProduct(id);

        productRepository.deleteById(productEntity.getId());

    }

    public List<ProductResponse> getProductList() {

        return productRepository.findAll().stream()
                .map(itm -> {
                    var res = new ProductResponse();
                    BeanUtils.copyProperties(itm, res);
                    return res;
                }).collect(Collectors.toList());
    }

    public List<ProductResponse> searchByProductName(String productName) {

        return getProductList().stream()
                .filter(itm -> itm.getProductName().toLowerCase().contains(productName.toLowerCase()))
                .collect(Collectors.toList());
    }
}
