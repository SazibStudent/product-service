package com.javatechbd.productservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.javatechbd.productservice.dto.request.BrandDto;

import com.javatechbd.productservice.dto.response.BrandRest;
import com.javatechbd.productservice.entity.BrandEntity;
import com.javatechbd.productservice.entity.ProductEntity;
import com.javatechbd.productservice.repository.BrandRepository;
import jdk.security.jarsigner.JarSignerException;
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
public class BrandService {

    private final BrandRepository brandRepository;
    private final EntityValidationService entityValidationService;

    private final Environment environment;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void createNewBrand(BrandDto brandDto) {

        var brandEntity= new BrandEntity();
        BeanUtils.copyProperties(brandDto,brandEntity);
        brandRepository.save(brandEntity);
        sendBrandToKafka(brandEntity);
    }


    public BrandRest getBrandById(long id){

        var brandEntity=entityValidationService.validateBrand(id);

        var response =new BrandRest();
        BeanUtils.copyProperties(brandEntity,response);
        return response;
    }


    public void updateBarnd(Long id, BrandDto brandDto) {

        var brandEntity=entityValidationService.validateBrand(id);

        brandEntity.setBrandName(brandDto.getBrandName());
        brandRepository.save(brandEntity);

    }

    public void deleteBrandById(Long id) {

        var brandEntity = entityValidationService.validateBrand(id);

        brandRepository.deleteById(brandEntity.getId());

    }


    public List<BrandRest> getBrandList() {

        return brandRepository.findAll().stream()
                .map(itm -> {
                    var res = new BrandRest();
                    BeanUtils.copyProperties(itm, res);
                    return res;
                }).collect(Collectors.toList());
    }
    public void sendBrandToKafka(BrandEntity brandEntity)
    {
        var brandString = getBrandAsString(brandEntity);
        ListenableFuture<SendResult<String, String>> future =
                this.kafkaTemplate.send(environment.getProperty("application.topic.brand-request"), brandString);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Sent BrandEntity: " + brandString + " with offset: " + result.getRecordMetadata().offset());
            }
            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send brandEntity : " + brandEntity, ex);
            }
        });
    }

    private  String getBrandAsString(BrandEntity brandEntity){
            ObjectWriter ow=new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return ow.writeValueAsString(brandEntity);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }



}
