package com.javatechbd.productservice.dto.request;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class CustomerDto {


    String customerName;
    String email;
    LocalDateTime created_at;
    String  created_by;



}
