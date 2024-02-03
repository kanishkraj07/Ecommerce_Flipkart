package com.flipkartservice.productservice.category;


import java.util.UUID;

public class CategoryPanelWIthProductModel {
   private UUID panelId;
   private String panelName;

   private boolean miniPanel;

   private Integer rowOrder;
   private UUID productId;
   private String productImg;
   private String productName;
   private Integer productPrice;
   private Integer minOffer;
   private Integer maxOffer;

    public CategoryPanelWIthProductModel() {
    }

    public CategoryPanelWIthProductModel(UUID panelId, String panelName, boolean miniPanel, Integer rowOrder, UUID productId, String productImg, String productName, Integer productPrice, Integer minOffer, Integer maxOffer) {
        this.panelId = panelId;
        this.panelName = panelName;
        this.miniPanel = miniPanel;
        this.rowOrder = rowOrder;
        this.productId = productId;
        this.productImg = productImg;
        this.productName = productName;
        this.productPrice = productPrice;
        this.minOffer = minOffer;
        this.maxOffer = maxOffer;
    }

    public boolean isMiniPanel() {
        return miniPanel;
    }

    public Integer getRowOrder() {
        return rowOrder;
    }

    public CategoryPanelWIthProductModel setRowOrder(Integer rowOrder) {
        this.rowOrder = rowOrder;
        return this;
    }

    public CategoryPanelWIthProductModel setMiniPanel(boolean miniPanel) {
        this.miniPanel = miniPanel;
        return this;
    }

    public UUID getPanelId() {
        return panelId;
    }

    public CategoryPanelWIthProductModel setPanelId(UUID panelId) {
        this.panelId = panelId;
        return this;
    }

    public String getPanelName() {
        return panelName;
    }

    public CategoryPanelWIthProductModel setPanelName(String panelName) {
        this.panelName = panelName;
        return this;
    }

    public UUID getProductId() {
        return productId;
    }

    public CategoryPanelWIthProductModel setProductId(UUID productId) {
        this.productId = productId;
        return this;
    }

    public String getProductImg() {
        return productImg;
    }

    public CategoryPanelWIthProductModel setProductImg(String productImg) {
        this.productImg = productImg;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public CategoryPanelWIthProductModel setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public CategoryPanelWIthProductModel setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
        return this;
    }

    public Integer getMinOffer() {
        return minOffer;
    }

    public CategoryPanelWIthProductModel setMinOffer(Integer minOffer) {
        this.minOffer = minOffer;
        return this;
    }

    public Integer getMaxOffer() {
        return maxOffer;
    }

    public CategoryPanelWIthProductModel setMaxOffer(Integer maxOffer) {
        this.maxOffer = maxOffer;
        return this;
    }
}


