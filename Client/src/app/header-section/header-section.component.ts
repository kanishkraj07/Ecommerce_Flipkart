import {
  ChangeDetectionStrategy,
  ChangeDetectorRef,
  Component,
  ElementRef,
  Input,
  OnInit,
  signal,
  ViewChild
} from '@angular/core';
import {faCartShopping, faRightFromBracket, faSearch} from '@fortawesome/free-solid-svg-icons';
import {faUser} from "@fortawesome/free-regular-svg-icons";
import * as UrlConstants from 'src/app/models/url-constants';
import {ActivatedRoute, Router} from "@angular/router";
import {CustomerContextService} from "../services/customer-context.service";
import {AuthenticationService} from "../services/authentication.service";
import {LogOut} from "lucide-angular";
import {NgbDropdown} from "@ng-bootstrap/ng-bootstrap";
import {finalize, forkJoin, Observable, tap} from "rxjs";
import {RawProduct} from "../models/product-details";
import {ProductHttpService} from "../services/product-http.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {CartService} from "../services/cart.service";
import {CartDetails, CartItem} from "../models/cart-details";

@Component({
  selector: 'app-header-section',
  templateUrl: './header-section.component.html',
  styleUrls: ['./header-section.component.css'],
})
export class HeaderSectionComponent implements OnInit {
  @Input() isInternalHeader: boolean;
faSearch = faSearch;
faUser = faUser;
faCart = faCartShopping;
faLogout = faRightFromBracket;
logoutIcon =  LogOut;
storeUrl = UrlConstants.STORE_URL;
cartUrl = UrlConstants.CART_URL;
isCustLoggedIn: boolean;
searchValue = signal<string>('');
valueChanged = '';
searchedOptions = signal<RawProduct[]>([]);
customerId: string;
logoUrl:string = '';
customerMobileNo: number;
rawProducts: RawProduct[] = [];
doneDataLoading: boolean;
cartCount: number;

@ViewChild('ngbDropdownRef') ngbDropdownRef: NgbDropdown;

constructor(private router: Router,
            private customerContextService: CustomerContextService,
            private authenticationService: AuthenticationService,
            private productHttpService: ProductHttpService,
            private cartService: CartService,
            private activatedRoute: ActivatedRoute) {
}

ngOnInit() {
  this.isCustLoggedIn = this.authenticationService.isUserLoggedIn;
  this.customerId = this.activatedRoute.snapshot.queryParams?.['cId'];
  console.log(this.customerId);
  if(this.isCustLoggedIn) {
    this.customerMobileNo = this.customerContextService.getCustomerContext().customer.mobileNo;
  }
  this.logoUrl = this.isInternalHeader ? 'https://static-assets-web.flixcart.com/fk-p-linchpin-web/fk-cp-zion/img/flipkart-plus_8d85f4.png' : 'https://static-assets-web.flixcart.com/batman-returns/batman-returns/p/images/fkheaderlogo_exploreplus-44005d.svg';
  forkJoin([this.loadProductRawData(), this.loadInitialData()]).pipe(finalize(() => {
    this.cartCount = this.cartService.cartCount();
    this.doneDataLoading = true;
  })).subscribe();
}

  initiateSearchingProcess(searchedVal) {
    this.searchValue.set(searchedVal.trim());
    this.searchProducts();
    this.openSearchDropdown();
}

  loadInitialData(): Observable<CartDetails[]> {
    return this.cartService.getAllCartItems(this.customerId);
  }


  searchProducts(): void {
  this.searchedOptions.set(this.rawProducts.filter((rawProduct: RawProduct) => rawProduct.productName.trim().toLowerCase().includes(this.searchValue())));
}

  openSelectedProduct(productId: string): void {
  this.router.navigate([`/product-details/pid/${productId}`]);
  }

  loadProductRawData(): Observable<RawProduct[]> {
    return this.productHttpService.getProductRawData().pipe(
      tap((rawProducts: RawProduct[]) => this.rawProducts = rawProducts)
    )
  }

  openSearchDropdown(): void {
    if(this.searchValue() && this.searchedOptions().length) {
      this.ngbDropdownRef.open();
    } else {
      this.ngbDropdownRef.close();
    }
  }

  navigateTo(): void {
    this.router.navigate(['/account'], {
      queryParams: {
        login: true
      }
    });
}

  navigateToCart(): void {
    this.router.navigate(['cart'], {
      queryParams: {
        cId: this.customerId
      }
    });
  }

  logout() {
  this.authenticationService.logout();
  }
}
