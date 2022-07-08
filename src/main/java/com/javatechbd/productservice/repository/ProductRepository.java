package com.javatechbd.productservice.repository;

import com.javatechbd.productservice.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>,
        QuerydslPredicateExecutor<ProductEntity> {
}
