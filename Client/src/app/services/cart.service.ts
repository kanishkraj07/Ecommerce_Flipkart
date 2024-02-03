import {computed, Injectable, signal, WritableSignal} from '@angular/core';
import {debounce, debounceTime, finalize, forkJoin, map, Observable, Subject, switchMap, tap} from "rxjs";
import {CartHttpService} from "./cart-http.service";
import {CartDetails, CartItem, CartPriceDetails} from "../models/cart-details";
import {ProductDetails} from "../models/product-details";
import {ProductHttpService} from "./product-http.service";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private cartHttpService: CartHttpService,
              private productHttpService: ProductHttpService) { }

  private placeNewOrder: Subject<number> = new Subject<number>();

  private _allCartItems: WritableSignal<CartItem[]> = signal<CartItem[]>([] as CartItem[]);
  private _cartPriceDetails: WritableSignal<CartPriceDetails> = signal<CartPriceDetails>({} as CartPriceDetails);
  private _cartCount: WritableSignal<number> = signal<number>(0);


  get allCartItems(): WritableSignal<CartItem[]> {
    return this._allCartItems;
  }


  clearCartItems() {
    this._allCartItems.mutate((cartItems: CartItem[]) => cartItems.splice(0));
  }

  get cartPriceDetails(): WritableSignal<CartPriceDetails> {
    return this._cartPriceDetails;
  }

  get cartCount(): WritableSignal<number> {
    return this._cartCount;
  }

  setPlaceNewOrder(totalAmount: number): void {
    this.placeNewOrder.next(totalAmount);
  }

  getNewOrder(): Observable<number> {
    return this.placeNewOrder.asObservable();
  }

  getAndMapAllCartItems(userId: string): Observable<CartItem[]> {
   return this.getAllCartItems(userId).pipe(
      switchMap((cartItems: CartDetails[]) => this.mapCartItems(cartItems)),
      tap((items: CartItem[]) => this._allCartItems.mutate(cartItems => {
        cartItems.splice(0);
        cartItems.push(...items);
      }))
    );
  }

  getAllCartItems(userId: string): Observable<CartDetails[]> {
    return this.cartHttpService.getCartItemsByUserId(userId).pipe(
      tap(items => this.cartCount.set(items.length))
    );
  }

  calculatePriceDetails(): void {
    this.cartPriceDetails.mutate((priceDetails: CartPriceDetails) => priceDetails.totalDeliveryCharges = 40 * this.allCartItems()?.length);
    this.allCartItems().forEach((cartItem: CartItem) => {
      this.cartPriceDetails.mutate((priceDetails: CartPriceDetails) => {
        priceDetails.totalActualPrice += cartItem.productDetails?.actualPrice;
        priceDetails.totalDiscount += (cartItem.productDetails?.actualPrice - cartItem.productDetails?.finalPrice);
        priceDetails.totalPackagingFee += cartItem.productDetails?.packagingFee;
        priceDetails.totalFinalPrice = priceDetails.totalActualPrice - priceDetails.totalDiscount + priceDetails.totalPackagingFee;
        priceDetails.totalSaveAmount = Math.abs(priceDetails.totalActualPrice - priceDetails.totalFinalPrice);
      });
    });
  }

  addCartItem(userId: string, newCartProduct: ProductDetails, quantity: number): Observable<CartDetails> {
    const newCartItem: CartDetails = {
      productId: newCartProduct.productId,
      quantity: quantity
    } as CartDetails;
   return this.cartHttpService.addCartItemByUserId(userId, newCartItem).pipe(
     tap(() => {
       this.allCartItems.mutate((cartItems: CartItem[]) => cartItems.push({productDetails: newCartProduct} as CartItem));
       this.cartCount.update((count: number) => count + 1);
     })
   );
  }

  mapCartItems(allCartItemsList: CartDetails[]): Observable<CartItem[]> {
   const observables: Observable<CartItem>[] = allCartItemsList.map((cartItem: CartDetails) => {
    return this.getMappedCartItem(cartItem)
    });
   return forkJoin(observables);
  }

  getMappedCartItem(cartItem: CartDetails): Observable<CartItem> {
   return this.productHttpService.getProductDetailsByProductId(cartItem.productId).pipe(map((productInfo: ProductDetails) => {
     this.cartPriceDetails.mutate((priceDetails: CartPriceDetails) => {
       priceDetails.totalActualPrice += productInfo?.actualPrice;
       priceDetails.totalDiscount += (productInfo?.actualPrice - productInfo?.finalPrice);
       priceDetails.totalPackagingFee += productInfo?.packagingFee;
       priceDetails.totalFinalPrice = priceDetails.totalActualPrice - priceDetails.totalDiscount + priceDetails.totalPackagingFee;
       priceDetails.totalSaveAmount = Math.abs(priceDetails.totalActualPrice - priceDetails.totalFinalPrice);
     });
     return {
       cartId: cartItem.cartId,
       quantity: cartItem.quantity,
       productDetails: productInfo
     } as CartItem;
    }));
  }
}
