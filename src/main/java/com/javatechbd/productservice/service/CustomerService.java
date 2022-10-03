package com.javatechbd.productservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.javatechbd.productservice.dto.request.CustomerDto;
import com.javatechbd.productservice.dto.response.CustomerRest;
import com.javatechbd.productservice.entity.CustomerEntity;
import com.javatechbd.productservice.entity.ProductEntity;
import com.javatechbd.productservice.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final EntityValidationService entityValidationService;

    private final Environment environment;
    private final KafkaTemplate<String, String> kafkaTemplate;

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

    public void sendCustomerToKafka(CustomerEntity customerEntity)
    {
        var customerString = getCustomerAsString(customerEntity);
        ListenableFuture<SendResult<String, String>> future =
                this.kafkaTemplate.send(environment.getProperty("application.topic.customer-request"), customerString);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Sent CustomerString: " + customerString + " with offset: " + result.getRecordMetadata().offset());
            }
            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send customerString : " + customerString, ex);
            }
        });
    }

    private String getCustomerAsString(CustomerEntity customerEntity) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return ow.writeValueAsString(customerEntity);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
