package com.flipkartservice.productservice.product;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;
    public List<RawProduct> getRawProducts() {
        return productDao.getAllRawProducts();
    }

    public ProductDetails getProductById(UUID productId) {
        return  productDao.getProductById(productId);
    }
}
