import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {CartDetails} from "../models/cart-details";
import {AuthenticationService} from "./authentication.service";
import {Observable} from "rxjs";
import {environment} from "src/environments/environment.development";

@Injectable({
  providedIn: 'root'
})
export class CartHttpService {

  private envUrl: string = environment.envUrl;

  constructor(private httpClient: HttpClient,
              private authenticationService: AuthenticationService) {
  }

  get authHeader(): HttpHeaders {
    return new HttpHeaders({'Authorization': `Bearer ${this.authenticationService.getCustomerAuthToken()}`});
  }

  getCartItemsByUserId(userId: string): Observable<CartDetails[]> {
    return this.httpClient.get(`${this.envUrl}/cart/${userId}`, {headers: this.authHeader}) as Observable<CartDetails[]>;
  }

  addCartItemByUserId(userId: string, newCartItem: CartDetails): Observable<CartDetails> {
    return this.httpClient.post(`${this.envUrl}/cart/addItem/${userId}`, newCartItem, {headers: this.authHeader}) as Observable<CartDetails>;
  }

  removeCartItemByCartId(cartId: string) {
    return this.httpClient.post(`${this.envUrl}/cart/remove/${cartId}`, null, {headers: this.authHeader});
  }

  updateQuantityByCartId(cartId: string, quantity: number) {
    return this.httpClient.put(`${this.envUrl}/cart/updateQuantity/${cartId}?quantity=${quantity}`, null, {headers: this.authHeader});
  }
}
