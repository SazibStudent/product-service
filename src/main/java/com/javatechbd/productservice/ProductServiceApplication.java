package com.javatechbd.productservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.javatechbd.productservice.entity.ProductEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceApplication {

	public static void main(String[] args) {

		var productEntity = new ProductEntity();
		productEntity.setProductName("name");



		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
