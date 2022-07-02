package com.javatechbd.productservice.controller;

import com.javatechbd.productservice.dto.request.ProductDto;
import com.javatechbd.productservice.dto.response.ProductResponse;
import com.javatechbd.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
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
    public ProductResponse getProductById(@PathVariable Long id) {

        return productService.getProductById(id);

    }

    @PutMapping("{id}")
    public void updateProductById(@PathVariable Long id,
                                  @RequestBody ProductDto productDto) {

        productService.updateProduct(id, productDto);

    }

    @DeleteMapping("{id}")
    public void deleteProductById(@PathVariable Long id) {

        productService.deleteProductById(id);

    }

    @GetMapping
    public List<ProductResponse> getProductList() {

        return productService.getProductList();

    }

    @GetMapping("/search/{name}")
    public List<ProductResponse> searchProductByName(@PathVariable String name) {

        return productService.searchByProductName(name);

    }
}
