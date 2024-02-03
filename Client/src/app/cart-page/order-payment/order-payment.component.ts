import {Component, OnInit} from '@angular/core';
import {CartService} from "../../services/cart.service";
import {PaymentHttpService} from "../../services/payment-http.service";
import {finalize, switchMap, tap} from "rxjs";
import {animate} from "@angular/animations";
import {WindowRef} from "../../services/window-ref.service";
import {PmtRequest, Razorpay} from "razorpay-checkout";
import {TransactionDetails} from "../../models/transaction-details";
import {CartHttpService} from "../../services/cart-http.service";
import {Order} from "../../models/order";
import {CartItem} from "../../models/cart-details";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-order-payment',
  templateUrl: './order-payment.component.html',
  styleUrls: ['./order-payment.component.less']
})
export class OrderPaymentComponent implements OnInit{

  cartIds: string[] = [];
  userId: string;

  constructor(private cartService: CartService,
              private paymentHttpService: PaymentHttpService,
              private cartHttpService: CartHttpService,
              private activatedRoute: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.userId = this.activatedRoute.snapshot.queryParams?.cId;
    this.cartService.getNewOrder().pipe(
      switchMap((totalAmount: number) => {
        return this.createOrderTransaction(totalAmount);
      })).subscribe();
  }

  createOrderTransaction(totalAmount) {
   return this.paymentHttpService.createTransaction(totalAmount).pipe(tap((order: TransactionDetails) => {
     this.processOrderPayment(order);
   }));
  }

  processOrderPayment(transactionOrder: TransactionDetails) {
    const paymentOptions: PmtRequest = {
      key: transactionOrder.transactionKey,
      amount: transactionOrder.totalAmount,
      currency: transactionOrder.currency,
      order_id: transactionOrder.orderId,
      handler: (res => {
        this.cartIds = [];
        this.cartService.allCartItems().forEach((cartItem: CartItem) => {
          this.cartIds.push(cartItem.cartId);
        })
        const newOrder: Order = {
          cartIds: this.cartIds,
          userId: this.userId,
          transactionId: res.razorpay_payment_id,
          paymentStatus: 'SUCCESS'
        } as Order;
        this.paymentHttpService.createNewOrder(newOrder).pipe(finalize(() => this.cartService.clearCartItems())).subscribe();
      }),

      description: 'Payment of Flipkart product',
      name: 'Flipkart',
      image: 'https://seeklogo.com/images/F/flipkart-logo-C9E637A758-seeklogo.com.png',
      theme: {
        color: '#2874f0'
      },
      prefill: {
        name: ''
      }
    };

    new Razorpay(paymentOptions).open();
  }
  onPaymentSuccess(response) {
    console.log(response);
    return null;
  }

}
