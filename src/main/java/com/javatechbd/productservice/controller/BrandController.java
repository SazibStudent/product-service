package com.javatechbd.productservice.controller;

import com.javatechbd.productservice.dto.request.BrandDto;
import com.javatechbd.productservice.dto.request.ProductDto;
import com.javatechbd.productservice.dto.response.BrandRest;
import com.javatechbd.productservice.service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/brands")
@AllArgsConstructor
public class BrandController {

     private  final BrandService brandService;

    @PostMapping
    public void createNewBrand(@RequestBody BrandDto brandDto) {
        brandService.createNewBrand(brandDto);
    }

    @GetMapping
    public List<BrandRest> getBrandList() {

        return brandService.getBrandList();
    }


    @GetMapping("{id}")
    public BrandRest getBrandById(@PathVariable Long id) {

        return brandService.getBrandById(id);

    }
    @PutMapping("{id}")
    public void updateBrandById(@PathVariable Long id, @RequestBody ProductDto productDto) {

        brandService.updateBarnd(id, new BrandDto());
    }

    @DeleteMapping("{id}")
    public void deleteBrandById(@PathVariable Long id) {

        brandService.deleteBrandById(id);

    }


}
