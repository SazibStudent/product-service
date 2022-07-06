package com.javatechbd.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestResponse {

    private String message;
    private HttpStatus status;
    private Object data;
    private LocalDateTime timestamp = LocalDateTime.now();
}
