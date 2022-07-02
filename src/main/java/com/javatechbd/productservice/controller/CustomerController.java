package com.javatechbd.productservice.controller;

import com.javatechbd.productservice.dto.request.CustomerDto;
import com.javatechbd.productservice.dto.request.ProductDto;
import com.javatechbd.productservice.dto.response.CustomerResponse;
import com.javatechbd.productservice.service.CustomerService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/save")
    public void createNewCustomer(@RequestBody CustomerDto customerDto) {
        customerService.createNewCustomer(customerDto);
    }

    @GetMapping("/list")
    public List<CustomerResponse> getCustomerList() {

        return customerService.getCustomarList();
    }


    @GetMapping("{id}")
    public  CustomerResponse getCustomerById(@PathVariable Long id) {

        return  customerService.getCustomerById(id);
    }

    @PutMapping("{id}")
    public void updateBrandById(@PathVariable Long id, @RequestBody ProductDto productDto) {

        customerService.updateCustomer(id, new CustomerDto());
    }

    @DeleteMapping("{id}")
    public void deleteCustomerById(@PathVariable Long id) {

        customerService.deleteCustomerById(id);
    }




}
