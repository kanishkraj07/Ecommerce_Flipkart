import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {faMinus, faPlus} from "@fortawesome/free-solid-svg-icons";
import {CartService} from "../services/cart.service";
import {PaymentHttpService} from "../services/payment-http.service";
import {ActivatedRoute, Router} from "@angular/router";
import {debounce, debounceTime, finalize, tap} from "rxjs";
import {CartDetails, CartItem} from "../models/cart-details";
import {ProductDetails} from "../models/product-details";
import {ProductHttpService} from "../services/product-http.service";
import {CartHttpService} from "../services/cart-http.service";
import {DialogService} from "../services/dialog-service.service";
import {AuthenticationService} from "../services/authentication.service";

@Component({
  selector: 'app-cart-page',
  templateUrl: './cart-page.component.html',
  styleUrls: ['./cart-page.component.css']
})
export class CartPageComponent implements OnInit{
  faMinus = faMinus;
  faPlus = faPlus;
  userId: string;
  doneLoading: boolean;
  cartItems: CartItem[] = [];
  totalActualPrice: number = 0;
  totalDiscount: number = 0;
  totalFinalPrice: number = 0;
  totalDeliveryCharges: number = 0;
  totalPackagingFee: number = 0;
  totalSaveAmount: number = 0;

  readonly REMOVE_ITEM_DIALOG = 'remove-item-dialog';

  constructor(private cartService: CartService,
              private cartHttpService: CartHttpService,
              private activatedRoute: ActivatedRoute,
              private dialogService: DialogService,
              private router: Router,
              private authenticationService: AuthenticationService) {

  }

  ngOnInit(): void {
    this.userId = this.activatedRoute.snapshot.queryParams?.cId;
    this.loadCartData();
  }

  loadCartData(): void {
    this.cartService.getAndMapAllCartItems(this.userId).pipe(
      tap(() => this.cartItems = this.cartService.allCartItems()),
      tap(() =>  this.calculatePaymentDetails()),
      finalize(() => {
        this.doneLoading = true;
      })
    ).subscribe();
  }


  calculatePaymentDetails(): void {
    this.initializeDefaultPrices();
    this.totalDeliveryCharges = 40 * this.cartService.allCartItems()?.length;
    this.cartService.allCartItems()?.forEach((cartItem: CartItem) => {
      this.totalActualPrice += (cartItem.productDetails?.actualPrice * cartItem.quantity);
      this.totalDiscount += ((cartItem.productDetails?.actualPrice - cartItem.productDetails?.finalPrice) * cartItem.quantity);
      this.totalPackagingFee += cartItem.productDetails?.packagingFee;
      this.totalFinalPrice = this.totalActualPrice - this.totalDiscount + this.totalPackagingFee;
      this.totalSaveAmount = Math.abs(this.totalActualPrice - this.totalFinalPrice);
    });
  }

  initializeDefaultPrices(): void {
    this.totalActualPrice = 0;
    this.totalDiscount = 0;
    this.totalFinalPrice = 0;
    this.totalDeliveryCharges = 0;
    this.totalPackagingFee = 0;
    this.totalSaveAmount = 0;
  }

  openRemoveDialog(cartId: string): void {
    this.dialogService.openDialog(this.REMOVE_ITEM_DIALOG, cartId);
  }

  removeCartItem(cartId: string): void {
    console.log(cartId);
    this.cartHttpService.removeCartItemByCartId(cartId).pipe(
      tap(() => {
        this.cartService.allCartItems.mutate((cartItems: CartItem[]) => {
         cartItems.splice(cartItems.findIndex((cartItem) => cartItem.cartId === cartId), 1);
        });
        this.calculatePaymentDetails();
      })
    ).subscribe();
  }

  updateItemQuantity(cartId: string, updatedQuantity: number): void {
    this.cartHttpService.updateQuantityByCartId(cartId, updatedQuantity).pipe(
      tap(() => {
        this.cartService.allCartItems.mutate((cartItems: CartItem[]) => {
          cartItems.forEach((item: CartItem) => {
            if(item.cartId === cartId) {
              item.quantity = updatedQuantity;
            }
          });
        })
      }),
      finalize(() => this.calculatePaymentDetails())
    ).subscribe();
  }

  placeOrder(): void {
    this.cartService.setPlaceNewOrder(this.totalFinalPrice);
  }

  isAuthenticated(): boolean {
    return this.authenticationService.isUserAuthenticated();
  }

  redirect(): void {
    this.router.navigate(['/account'], {
      queryParams: {login: true}
    })
  }
}
