package com.javatechbd.productservice.service;

import com.javatechbd.productservice.entity.ProductEntity;
import com.javatechbd.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class EntityValidationService {

    private final ProductRepository productRepository;

    public ProductEntity validateProduct(Long id) {
        Objects.requireNonNull(id);
        return productRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(String
                        .format("Product not found with id [%s]", id)));
    }

}
