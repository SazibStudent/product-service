package com.javatechbd.productservice.service;

import com.javatechbd.productservice.dto.request.CustomerDto;
import com.javatechbd.productservice.dto.request.ProductDto;
import com.javatechbd.productservice.dto.response.BrandResponse;
import com.javatechbd.productservice.dto.response.CustomerResponse;
import com.javatechbd.productservice.dto.response.ProductResponse;
import com.javatechbd.productservice.entity.CustomerEntity;
import com.javatechbd.productservice.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final EntityValidationService entityValidationService;


    public void createNewCustomer(CustomerDto customerDto) {

        var customerEntity = new CustomerEntity();
        BeanUtils.copyProperties(customerDto, customerEntity);
        customerRepository.save(customerEntity);
    }



    public List<CustomerResponse> getCustomarList() {
        return customerRepository.findAll().stream()
                .map(itm -> {
                    var res = new CustomerResponse();
                    BeanUtils.copyProperties(itm, res);
                    return res;
                }).collect(Collectors.toList());
    }


    public CustomerResponse getCustomerById(Long id) {

        var customerEntity = entityValidationService.validateCustomer(id);
        var response = new CustomerResponse();
        BeanUtils.copyProperties(customerEntity, response);

       return  response;
    }


    public void updateCustomer(Long id, CustomerDto customerDto) {

        var customerEntity = entityValidationService.validateCustomer(id);

        customerEntity.setCustomerName(customerDto.getCustomerName());
        customerEntity.setEmail(customerDto.getEmail());
        customerEntity.setCreated_at(customerDto.getCreated_at());
        customerEntity.setCreated_at(customerDto.getCreated_at());
        customerRepository.save(customerEntity);
    }

    public void deleteCustomerById(Long id) {

        var customerEntity = entityValidationService.validateCustomer(id);
        customerRepository.deleteById(customerEntity.getId());

    }


}
