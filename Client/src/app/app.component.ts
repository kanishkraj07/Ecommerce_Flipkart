import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, RouterModule} from "@angular/router";
import {AuthenticationService} from "./services/authentication.service";
import {CustomerContextService} from "./services/customer-context.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  constructor(private authenticationService: AuthenticationService,
              private customerContextService: CustomerContextService) {

  }

  ngOnInit() {
    // if(this.authenticationService.isUserAuthenticated()) {
    //   this.authenticationService.setCustomerAuthDetails();
    //   this.customerContextService.setInitialCustomerDetails(this.authenticationService.customerAuthId, this.authenticationService.customerAuthSub);
    // }
  }
}
