package com.flipkartservice.productservice.product;


import java.util.Date;
import java.util.UUID;

public class ProductDetails {
    private UUID productId;
    private String productFullName;
    private Double productRating;
    private Integer totalRatings;
    private Integer totalReviews;
    private boolean enterpriseInsurance;
    private Integer extraOff;
    private double finalPrice;
    private double actualPrice;
    private Integer offerPercentage;
    private Integer packagingFee;
    private Date deliveryDate;
    private boolean freeDelivery;
    private String[] productHighlight;
    private SellerDetails sellerDetails;
    private String productImg;

    public ProductDetails() {
    }

    public ProductDetails(UUID productId, String productFullName, Double productRating, Integer totalRatings, Integer totalReviews, boolean enterpriseInsurance, Integer extraOff, double finalPrice, double actualPrice, Integer offerPercentage, Integer packagingFee, Date deliveryDate, boolean freeDelivery, String[] productHighlight, SellerDetails sellerDetails, String productImg) {
        this.productId = productId;
        this.productFullName = productFullName;
        this.productRating = productRating;
        this.totalRatings = totalRatings;
        this.totalReviews = totalReviews;
        this.enterpriseInsurance = enterpriseInsurance;
        this.extraOff = extraOff;
        this.finalPrice = finalPrice;
        this.actualPrice = actualPrice;
        this.offerPercentage = offerPercentage;
        this.packagingFee = packagingFee;
        this.deliveryDate = deliveryDate;
        this.freeDelivery = freeDelivery;
        this.productHighlight = productHighlight;
        this.sellerDetails = sellerDetails;
        this.productImg = productImg;
    }

    public String getProductImg() {
        return productImg;
    }

    public UUID getProductId() {
        return productId;
    }

    public String getProductFullName() {
        return productFullName;
    }

    public Double getProductRating() {
        return productRating;
    }

    public Integer getTotalRatings() {
        return totalRatings;
    }

    public Integer getTotalReviews() {
        return totalReviews;
    }

    public boolean isEnterpriseInsurance() {
        return enterpriseInsurance;
    }

    public Integer getExtraOff() {
        return extraOff;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public double getActualPrice() {
        return actualPrice;
    }

    public Integer getOfferPercentage() {
        return offerPercentage;
    }

    public Integer getPackagingFee() {
        return packagingFee;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public boolean isFreeDelivery() {
        return freeDelivery;
    }

    public String[] getProductHighlight() {
        return productHighlight;
    }

    public SellerDetails getSellerDetails() {
        return sellerDetails;
    }
}
