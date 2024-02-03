import { Injectable } from '@angular/core';
import {CustomerAccountDetails, CustomerContext} from "../models/customer-account-details";
import {AuthenticationService} from "./authentication.service";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class CustomerContextService {

  constructor(private router: Router) { }

  private customerContext: CustomerContext = {} as CustomerContext;

  getCustomerContext(): CustomerContext {
    return this.customerContext;
  }

  setInitialCustomerDetails(customerId: string, mobileNo: number) {
    this.customerContext.customer = {customerId: customerId, mobileNo: mobileNo} as CustomerAccountDetails;
  }
}
