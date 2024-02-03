package com.flipkartservice.productservice.category;

import java.util.List;

public class CategoryPanelWithProduct {
    private CategoryPanel panel;
    private List<CategoryPanelProduct> products;

    public CategoryPanelWithProduct(CategoryPanel panel, List<CategoryPanelProduct> products) {
        this.panel = panel;
        this.products = products;
    }

    public CategoryPanelWithProduct() {
    }

    public CategoryPanel getPanel() {
        return panel;
    }

    public CategoryPanelWithProduct setPanel(CategoryPanel panel) {
        this.panel = panel;
        return this;
    }

    public List<CategoryPanelProduct> getProducts() {
        return products;
    }

    public CategoryPanelWithProduct setProducts(List<CategoryPanelProduct> products) {
        this.products = products;
        return this;
    }
}
