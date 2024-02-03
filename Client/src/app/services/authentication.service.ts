import { Injectable } from '@angular/core';
import {CookieService} from "ngx-cookie-service";
import {JwtHelperService} from "@auth0/angular-jwt";
import {Router} from "@angular/router";
import {finalize} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private cookieService: CookieService,
              private jwtHelperService: JwtHelperService,
              private router: Router) { }

  private _isUserLoggedIn: boolean;
  private _customerAuthId: string;
  private _customerAuthSub: number;
  private _authToken: string;

  setAuthenticationDetails(token: string) {
    if(this.verifyAuthToken(token)) {
      this.cookieService.set('CUSTOMER_AUTH_TOKEN', token, {path: '/', domain: 'localhost', secure: true, sameSite: 'Lax'});
      this.cookieService.set('CUSTOMER_ID', this.getCustomerIdByToken(token), {path: '/', domain: 'localhost', secure: true, sameSite: 'Lax'});
      this.cookieService.set('CUSTOMER_MOBILE', this.getCustomerSubjectByToken(token), {path: '/', domain: 'localhost', secure: true, sameSite: 'Lax'});
      this.setCustomerAuthDetails();
    }
  }

  logout() {
    if(this.isUserAuthenticated()) {
      this.cookieService.delete('CUSTOMER_AUTH_TOKEN', '/', 'localhost', true, 'Lax');
      this.cookieService.delete('CUSTOMER_ID', '/', 'localhost', true, 'Lax');
      this.cookieService.delete('CUSTOMER_MOBILE', '/', 'localhost', true, 'Lax');
      this.updateCustomerIdParam(null).finally(() => window.location.reload());
    }
  }


  get isUserLoggedIn(): boolean {
    return this._isUserLoggedIn;
  }

  get customerAuthId(): string {
    return this._customerAuthId;
  }

  set customerAuthId(value: string) {
    this._customerAuthId = value;
  }

  get customerAuthSub(): number {
    return this._customerAuthSub;
  }

  set customerAuthSub(value: number) {
    this._customerAuthSub = value;
  }

  get authToken(): string {
    return this._authToken;
  }

  verifyAuthToken(token: string) {
    return !this.jwtHelperService.isTokenExpired(token);
  }

  getCustomerIdByToken(token: string) {
    return this.jwtHelperService.decodeToken(token).customerId;
  }

  getCustomerSubjectByToken(token: string) {
    return this.jwtHelperService.decodeToken(token).sub;
  }

  isUserAuthenticated(): boolean {
    const authToken: string = this.getCustomerAuthToken();
    this._isUserLoggedIn = authToken !== null && this.verifyAuthToken(authToken) && this.getCustomerIdByToken(authToken) !== null;
    return this._isUserLoggedIn;
  }

  setCustomerAuthDetails() {
    this._authToken = this.getCustomerAuthToken();
    this.customerAuthId = this.getCustomerIdByToken(this.getCustomerAuthToken());
    this.customerAuthSub = this.getCustomerSubjectByToken(this.getCustomerAuthToken());
    this.updateCustomerIdParam({cId: this.customerAuthId});
  }

  updateCustomerIdParam(param): Promise<boolean> {
    return this.router.navigate([''], {
      queryParams: param
    });
  }

   getCustomerAuthToken(): string {
    return this.cookieService.get('CUSTOMER_AUTH_TOKEN');
  }
}
