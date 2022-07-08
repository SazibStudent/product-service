package com.javatechbd.productservice.controller;

import com.javatechbd.productservice.common.ResponseFactory;
import com.javatechbd.productservice.dto.RestResponse;
import com.javatechbd.productservice.dto.request.CustomerDto;

import com.javatechbd.productservice.dto.response.CustomerRest;
import com.javatechbd.productservice.service.CustomerService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public RestResponse createNewCustomer(@RequestBody CustomerDto customerDto) {
        customerService.createNewCustomer(customerDto);
        return ResponseFactory.saveSuccess();
    }

    @GetMapping
    public RestResponse getCustomerList() {

        return ResponseFactory.responseData(customerService.getCustomerList());
    }

    @GetMapping("{id}")
    public CustomerRest getCustomerById(@PathVariable Long id) {

        return customerService.getCustomerById(id);
    }

    @PutMapping("{id}")
    public RestResponse updateCustomer(@PathVariable Long id,
                                       @RequestBody CustomerDto customerDto) {

        customerService.updateCustomer(id, customerDto);
        return ResponseFactory.updateSuccess();
    }

    @DeleteMapping("{id}")
    public RestResponse deleteCustomerById(@PathVariable Long id) {

        customerService.deleteCustomerById(id);
        return ResponseFactory.deleteSuccess();
    }


}
