package com.javatechbd.productservice.dto.search;

import lombok.Data;

import java.util.List;

@Data
public class ProductSearchDTO {

    private String productName;
    private String brandName;
    private Double purchasePrice;
    private Double salesPrice;
    private List<Long> productIds;
    private int page = 0;
    private int size = 10;
}
