import {ProductDetails} from "./product-details";

export interface CartDetails {
  productId: string,
  quantity: number,
  cartId?: string
}

export interface CartItem {
  cartId: string;
  quantity: number;
  productDetails: ProductDetails;
}

export interface CartPriceDetails {
  totalActualPrice: number,
  totalDiscount: number,
  totalFinalPrice: number,
  totalDeliveryCharges: number,
  totalPackagingFee: number,
  totalSaveAmount: number
}

