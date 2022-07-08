package com.javatechbd.productservice.dto.search;

import lombok.Data;

@Data
public class ProductSearchDTO {

    private String productName;
    private String brandName;
    private Double purchasePrice;
    private Double salesPrice;
    private int page = 0;
    private int size = 10;
}
