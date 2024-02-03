import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {AuthenticationService} from "./authentication.service";
import {environment} from "../../environments/environment.development";
import {TransactionDetails} from "../models/transaction-details";
import {Order} from "../models/order";

@Injectable({
  providedIn: 'root'
})
export class PaymentHttpService {

  envUrl: string = environment.envUrl;

  constructor(private httpClient: HttpClient,
              private authenticationService: AuthenticationService) { }

  get authHeader(): HttpHeaders {
    return new HttpHeaders({'Authorization': `Bearer ${this.authenticationService.getCustomerAuthToken()}`});
  }

  createTransaction(amount: number): Observable<TransactionDetails> {
   return this.httpClient.get(`${this.envUrl}/payment/createTransaction?totalAmount=${amount}`, {headers: this.authHeader}) as Observable<TransactionDetails>;
  }

  createNewOrder(newOrder: Order): Observable<string> {
    return this.httpClient.post(`${this.envUrl}/order/create`, newOrder, {headers: this.authHeader}) as Observable<string>;
  }
}
