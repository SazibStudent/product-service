package com.javatechbd.productservice.dto.request;

import lombok.Data;

@Data
public class ProductDto {

    private String productName;
    private String brand;
    private Double purchasePrice;
    private Double salesPrice;
}
