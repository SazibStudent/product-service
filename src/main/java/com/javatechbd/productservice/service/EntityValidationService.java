package com.javatechbd.productservice.service;

import com.javatechbd.productservice.entity.BrandEntity;
import com.javatechbd.productservice.entity.CustomerEntity;
import com.javatechbd.productservice.entity.ProductEntity;
import com.javatechbd.productservice.repository.BrandRepository;
import com.javatechbd.productservice.repository.CustomerRepository;
import com.javatechbd.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class EntityValidationService {

    private final ProductRepository productRepository;
    private  final BrandRepository brandRepository;
    private  final CustomerRepository customerRepository;

    public ProductEntity validateProduct(Long id) {
        Objects.requireNonNull(id);
        return productRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(String
                        .format("Product not found with id [%s]", id)));
    }


    public BrandEntity validateBrand(Long id) {
        Objects.requireNonNull(id);
        return brandRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(String
                        .format("Brand not found with id [%s]", id)));
    }

    public CustomerEntity validateCustomer(Long id) {
        Objects.requireNonNull(id);
        return customerRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(String
                        .format("Customer not found with id [%s]", id)));
    }


}
