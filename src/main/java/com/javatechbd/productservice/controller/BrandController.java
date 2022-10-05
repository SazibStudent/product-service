package com.javatechbd.productservice.controller;

import com.javatechbd.productservice.common.ResponseFactory;
import com.javatechbd.productservice.dto.RestResponse;
import com.javatechbd.productservice.dto.request.BrandDto;
import com.javatechbd.productservice.dto.request.ProductDto;
import com.javatechbd.productservice.dto.response.BrandRest;
import com.javatechbd.productservice.service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/brands")
@AllArgsConstructor
public class BrandController {

     private  final BrandService brandService;

    @PostMapping
    public RestResponse createNewBrand(@RequestBody BrandDto brandDto) {
        brandService.createNewBrand(brandDto);
        return ResponseFactory.saveSuccess();
    }

    @GetMapping
    public RestResponse getBrandList() {

        return ResponseFactory.responseData(brandService.getBrandList());
    }


    @GetMapping("{id}")
    public BrandRest getBrandById(@PathVariable Long id) {

        return brandService.getBrandById(id);

    }
    @PutMapping("{id}")
    public RestResponse updateBrand(@PathVariable Long id, @RequestBody BrandDto brandDto) {

        brandService.updateBarnd(id,brandDto);
        return  ResponseFactory.updateSuccess();
    }

    @DeleteMapping("{id}")
    public RestResponse deleteBrand(@PathVariable Long id) {

        brandService.deleteBrandById(id);
        return ResponseFactory.deleteSuccess();
    }




}
