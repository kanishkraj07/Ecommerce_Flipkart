import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AccountHttpService {

  private envUrl: String = 'http://localhost:6782'

  constructor(private httpClient: HttpClient) { }

 isAccountRegistered(mobileNo: number): Observable<boolean> {
    return this.httpClient.get<boolean>(`${this.envUrl}/account/isAccountRegistered?mobileNo=${mobileNo}`);
}
}
