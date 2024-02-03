export interface ProductDetails {
    productId: string;
    productFullName: string;
    productRating: number;
    totalRatings: number;
    totalReviews: number;
    enterpriseInsurance: boolean;
    extraOff: number;
    finalPrice: number;
    actualPrice: number;
    offerPercentage: number;
    packagingFee: number;
    deliveryDate: Date;
    freeDelivery: boolean;
    productHighlight: string[];
    sellerDetails: SellerDetails;
    productImg: string;
}

export interface SellerDetails {
  seller_name: string;
  seller_rating: number;
  seller_highlights: string;
}

export interface RawProduct {
  productId: string;
  productName: string;
}
