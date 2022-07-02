package com.javatechbd.productservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {


    Long id;
    String customerName;
    String email;
    LocalDateTime created_at;
    String  created_by;



}
