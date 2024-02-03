import {NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';

import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RouterModule, Routes} from "@angular/router";

import {PanelInteriorPageComponent} from "./panel-interior-page/panel-interior-page.component";
import { CategoryItemsComponent } from './home-page/category-items/category-items.component';
import {HeaderSectionComponent} from "./header-section/header-section.component";
import {FooterSectionComponent} from "./footer-section/footer-section.component";
import {HomePageComponent} from "./home-page/home-page.component";
import { SignInComponent } from './sign-in/sign-in.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import {CommonModule} from "@angular/common";
import { CartPageComponent } from './cart-page/cart-page.component';
import {HttpClientModule} from "@angular/common/http";
import { CookieService } from 'ngx-cookie-service';
import {JWT_OPTIONS, JwtHelperService} from "@auth0/angular-jwt";
import { OrderPaymentComponent } from './cart-page/order-payment/order-payment.component';
import {CartService} from "./services/cart.service";
import {WindowRef} from "./services/window-ref.service";
import {LogOut, LucideAngularModule, Search, ShoppingCart} from "lucide-angular";
import {NgbDropdownModule, NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { RemoveItemDialogComponent } from './cart-page/remove-item-dialog/remove-item-dialog.component';

export const routes: Routes = [
  {
    path: '',
    component: HomePageComponent,
    runGuardsAndResolvers: 'always'
  },
  {
    path: 'account',
    component: SignInComponent
  },
  {
    path: 'internal-products/:id',
    component: PanelInteriorPageComponent
  },
  {
    path: ':productType/product-details/pid/:productId',
    component: ProductDetailsComponent
  },
  {
    path: 'cart',
    component: CartPageComponent
  },
  {
    path: 'product-details/pid/:productId',
    component: ProductDetailsComponent
  }
];


@NgModule({
  declarations: [
    AppComponent,
    HeaderSectionComponent,
    FooterSectionComponent,
    PanelInteriorPageComponent,
    HomePageComponent,
    CategoryItemsComponent,
    SignInComponent,
    ProductDetailsComponent,
    CartPageComponent,
    OrderPaymentComponent,
    RemoveItemDialogComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    FontAwesomeModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    LucideAngularModule.pick({LogOut, Search, ShoppingCart}),
    RouterModule.forRoot(routes, {scrollPositionRestoration: 'enabled', onSameUrlNavigation: 'reload'}),
    NgbDropdownModule,

  ],
  providers: [CookieService, JwtHelperService, {provide: JWT_OPTIONS, useValue: JWT_OPTIONS}, CartService, WindowRef],
  bootstrap: [AppComponent]
})
export class AppModule { }
