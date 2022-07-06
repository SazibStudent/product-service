package com.javatechbd.productservice.dto.request;

import lombok.Data;

@Data
public class ProductDto {

    private String productName;
    private Long brandId;
    private Double purchasePrice;
    private Double salesPrice;
}
