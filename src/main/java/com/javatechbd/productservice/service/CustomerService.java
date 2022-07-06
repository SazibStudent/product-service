package com.javatechbd.productservice.service;

import com.javatechbd.productservice.dto.request.CustomerDto;
import com.javatechbd.productservice.dto.response.CustomerRest;
import com.javatechbd.productservice.entity.CustomerEntity;
import com.javatechbd.productservice.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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



    public List<CustomerRest> getCustomerList() {
        return customerRepository.findAll().stream()
                .map(itm -> {
                    var res = new CustomerRest();
                    BeanUtils.copyProperties(itm, res);
                    return res;
                }).collect(Collectors.toList());
    }


    public CustomerRest getCustomerById(Long id) {

        var customerEntity = entityValidationService.validateCustomer(id);
        var response = new CustomerRest();
        BeanUtils.copyProperties(customerEntity, response);

       return  response;
    }


    public void updateCustomer(Long id, CustomerDto customerDto) {

        var customerEntity = entityValidationService.validateCustomer(id);

        BeanUtils.copyProperties(customerDto, customerEntity);

        customerRepository.save(customerEntity);
    }

    public void deleteCustomerById(Long id) {

        var customerEntity = entityValidationService.validateCustomer(id);
        customerRepository.deleteById(customerEntity.getId());
    }


}
