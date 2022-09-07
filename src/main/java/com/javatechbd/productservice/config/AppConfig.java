package com.javatechbd.productservice.config;

import com.javatechbd.productservice.entity.BrandEntity;
import com.javatechbd.productservice.entity.CustomerEntity;
import com.javatechbd.productservice.entity.ProductEntity;
import com.javatechbd.productservice.repository.BrandRepository;
import com.javatechbd.productservice.repository.CustomerRepository;
import com.javatechbd.productservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class AppConfig {

  @Bean
  CommandLineRunner commandLineRunner(BrandRepository brandRepository,
                                      CustomerRepository customerRepository,
                                      ProductRepository productRepository) {

    return args -> {

      var brand1 = BrandEntity.builder().brandName("Samsung").build();
      var brand2 = BrandEntity.builder().brandName("Nokia").build();
      var brand3 = BrandEntity.builder().brandName("Iphone").build();

      brandRepository.saveAll(List.of(brand1, brand2, brand3));


      var customer1 = CustomerEntity.builder().customerName("Mahadi1").email("mahadi1@gmail.com").createdAt(LocalDateTime.now()).createdBy("mahadi").build();
      var customer2 = CustomerEntity.builder().customerName("Mahadi2").email("mahadi2@gmail.com").createdAt(LocalDateTime.now()).createdBy("mahadi").build();
      var customer3 = CustomerEntity.builder().customerName("Mahadi3").email("mahadi3@gmail.com").createdAt(LocalDateTime.now()).createdBy("mahadi").build();

      customerRepository.saveAll(List.of(customer1, customer2, customer3));


      var product1 = ProductEntity.builder().productName("Phone 1").brand(brand1).purchasePrice(100D).salesPrice(200D).build();
      var product2 = ProductEntity.builder().productName("Phone 2").brand(brand2).purchasePrice(200D).salesPrice(300D).build();
      var product3 = ProductEntity.builder().productName("Phone 3").brand(brand3).purchasePrice(300D).salesPrice(400D).build();

      productRepository.saveAll(List.of(product1, product2, product3));


    };

  }
}
