package com.flipkartservice.productservice.product;


public class SellerDetails {
    private String seller_name;
    private double seller_rating;
    private String seller_highlights;


    public SellerDetails() {
    }

    public SellerDetails(String seller_name, double seller_rating, String seller_highlights) {
        this.seller_name = seller_name;
        this.seller_rating = seller_rating;
        this.seller_highlights = seller_highlights;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public SellerDetails setSeller_name(String seller_name) {
        this.seller_name = seller_name;
        return this;
    }

    public double getSeller_rating() {
        return seller_rating;
    }

    public SellerDetails setSeller_rating(double seller_rating) {
        this.seller_rating = seller_rating;
        return this;
    }

    public String getSeller_highlights() {
        return seller_highlights;
    }

    public SellerDetails setSeller_highlights(String seller_highlights) {
        this.seller_highlights = seller_highlights;
        return this;
    }
}
