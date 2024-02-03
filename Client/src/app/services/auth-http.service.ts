import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {UserAuthInfo} from "../models/otp-auth";

@Injectable({
  providedIn: 'root'
})
export class AuthHttpService {

  envUrl: string = 'http://localhost:6782';
  constructor(private httpClient: HttpClient) { }

  sendOtp(userMobileNo: number): Observable<string> {
    return this.httpClient.post(`${this.envUrl}/otp/send?mobileNo=${userMobileNo}`, {}, {responseType: "text"});
  }

  verifyOtp(userAuthInfo: UserAuthInfo, isToRegister: boolean): Observable<string> {
    return this.httpClient.post(`${this.envUrl}/otp/validate?isToRegister=${isToRegister}`, userAuthInfo, {responseType: 'text'});
  }
}
