package com.javatechbd.productservice.controller;

import com.javatechbd.productservice.dto.request.ProductDto;
import com.javatechbd.productservice.dto.response.ProductRest;
import com.javatechbd.productservice.dto.search.ProductSearchDTO;
import com.javatechbd.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public void createNewProduct(@RequestBody ProductDto productDto) {

        productService.createNewProduct(productDto);
    }

    @GetMapping("{id}")
    public ProductRest getProductById(@PathVariable Long id) {

        return productService.getProductById(id);
    }

    @PutMapping("{id}")
    public void updateProductById(@PathVariable Long id, @RequestBody ProductDto productDto) {

        productService.updateProduct(id, productDto);
    }

    @DeleteMapping("{id}")
    public void deleteProductById(@PathVariable Long id) {

        productService.deleteProductById(id);
    }

    @GetMapping
    public List<ProductRest> getProductList() {

        return productService.getProductList();
    }

    @PostMapping("/search")
    public Page<ProductRest> searchProduct(@RequestBody ProductSearchDTO searchDTO) {

        return productService.searchProduct(searchDTO);
    }

    @PostMapping("/search-product-by-id")
    public List<ProductRest> searchProductById(@RequestBody ProductSearchDTO searchDTO) {

        return productService.searchProductById(searchDTO);
    }
}
