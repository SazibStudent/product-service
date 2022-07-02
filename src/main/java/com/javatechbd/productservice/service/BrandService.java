package com.javatechbd.productservice.service;

import com.javatechbd.productservice.dto.request.BrandDto;

import com.javatechbd.productservice.dto.request.ProductDto;
import com.javatechbd.productservice.dto.response.BrandResponse;
import com.javatechbd.productservice.dto.response.ProductResponse;
import com.javatechbd.productservice.entity.BrandEntity;
import com.javatechbd.productservice.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;
    private final EntityValidationService entityValidationService;

    public void createNewBrand(BrandDto brandDto) {
        var brandEntity= new BrandEntity();
        BeanUtils.copyProperties(brandDto,brandEntity);
        brandRepository.save(brandEntity);
    }


    public BrandResponse getBrandById(long id){

        var brandEntity=entityValidationService.validateBrand(id);

        var response =new BrandResponse();
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


    public List<BrandResponse> getBrandList() {

        return brandRepository.findAll().stream()
                .map(itm -> {
                    var res = new BrandResponse();
                    BeanUtils.copyProperties(itm, res);
                    return res;
                }).collect(Collectors.toList());
    }

}
