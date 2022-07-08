package com.javatechbd.productservice.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class BrandDto {

    @NotBlank
    @Size(min = 20)
    private String brandName;
}
