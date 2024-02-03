package com.flipkartservice.productservice.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/itemInfo")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping("/raw")
    public List<RawProduct> getRawProducts() {
        return productService.getRawProducts();
    }

    @GetMapping("/getProductById")
    public ProductDetails getProductByProductId(@RequestParam("productId") UUID productId) {
        return productService.getProductById(productId);
    }
}
