package com.javatechbd.productservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Long id;
    private String productName;
    private String brand;
    private Double purchasePrice; // 0.00
    private Double salesPrice; // 0.00
}
