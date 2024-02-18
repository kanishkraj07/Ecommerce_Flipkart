package com.flipkartservice.productservice.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<>(categoryDao.getCategories(), HttpStatus.OK);
    }

    public List<CategoryPanelWithProduct> getAllPanelWithProducts() {
       List<CategoryPanelWIthProductModel> allPanelProductsList = categoryDao.getAllPanelWithProducts();
       Map<UUID, List<CategoryPanelWIthProductModel>> map = allPanelProductsList.stream().collect(Collectors.groupingBy(CategoryPanelWIthProductModel::getPanelId,
               LinkedHashMap::new, Collectors.toList()));
       List<CategoryPanelWithProduct> categoryPanelWithProductList = new ArrayList<>(List.of());
        map.forEach((panelId, panelProduct) -> {
            List<CategoryPanelProduct> categoryPanelProductList = new ArrayList<>(List.of());
            CategoryPanel categoryPanel = CategoryPanel.builder()
                   .setPanelId(panelId)
                   .setPanelName(panelProduct.get(0).getPanelName())
                    .setMiniPanel(panelProduct.get(0).isMiniPanel())
                   .build();

           CategoryPanelProduct.Builder panelProductBuilder = CategoryPanelProduct.builder();

           panelProduct.stream()
                   .forEach(panelProducts -> categoryPanelProductList.add(
                           panelProductBuilder
                           .setProductId(panelProducts.getProductId())
                           .setProductImg(panelProducts.getProductImg())
                           .setProductName(panelProducts.getProductName())
                           .setMaxOffer(panelProducts.getMaxOffer())
                           .setMinOffer(panelProducts.getMinOffer())
                           .setProductPrice(panelProducts.getProductPrice()).build()));
           categoryPanelWithProductList.add(new CategoryPanelWithProduct(categoryPanel, categoryPanelProductList));

       });
       return categoryPanelWithProductList;
    }
}
