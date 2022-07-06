package com.javatechbd.productservice.common;

import com.javatechbd.productservice.dto.RestResponse;
import org.springframework.http.HttpStatus;

public class ResponseFactory {

    public static RestResponse saveSuccess() {
        return RestResponse.builder()
                .message("Record Saved Successfully.")
                .status(HttpStatus.OK)
                .build();
    }

    public static RestResponse updateSuccess() {
        return RestResponse.builder()
                .message("Record Updated Successfully.")
                .status(HttpStatus.OK)
                .build();
    }

    public static RestResponse deleteSuccess() {
        return RestResponse.builder()
                .message("Record Deleted Successfully.")
                .status(HttpStatus.OK)
                .build();
    }

    public static RestResponse response(String message, HttpStatus httpStatus) {
        return RestResponse.builder()
                .message(message)
                .status(httpStatus)
                .build();
    }

    public static RestResponse responseData(Object data) {
        return RestResponse.builder()
                .message("Data Fetched Successfully")
                .status(HttpStatus.OK)
                .data(data)
                .build();
    }
}
