package com.javatechbd.productservice.service;

import com.javatechbd.productservice.dto.request.ProductDto;
import com.javatechbd.productservice.dto.response.ProductRest;
import com.javatechbd.productservice.entity.ProductEntity;
import com.javatechbd.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final EntityValidationService entityValidationService;

    public void createNewProduct(ProductDto productDto) {

        var productEntity = new ProductEntity();
        var brandEntity = entityValidationService.validateBrand(productDto.getBrandId());
        BeanUtils.copyProperties(productDto, productEntity);
        productEntity.setBrand(brandEntity);

        productRepository.save(productEntity);
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
                .map(itm -> {
                    var res = new ProductRest();
                    BeanUtils.copyProperties(itm, res);
                    Optional.ofNullable(itm.getBrand())
                            .ifPresent(brand-> {
                                res.setBrandId(brand.getId());
                            });
                    return res;
                }).collect(Collectors.toList());
    }

    public List<ProductRest> searchByProductName(String productName) {

        return getProductList().stream()
                .filter(itm -> itm.getProductName().toLowerCase().contains(productName.toLowerCase()))
                .collect(Collectors.toList());
    }
}
