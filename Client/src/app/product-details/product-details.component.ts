import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {HomeCategoryPanel} from "../models/home-category-panel";
import {IconDefinition} from "@fortawesome/free-brands-svg-icons";
import {faBoltLightning, faCartShopping, faHeart, faStar, faLocationDot} from "@fortawesome/free-solid-svg-icons";
import {
  FormBuilder,
  FormGroup
} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import {ProductHttpService} from "../services/product-http.service";
import {finalize, tap} from "rxjs";
import {ProductDetails} from "../models/product-details";
import {CartHttpService} from "../services/cart-http.service";
import {CartService} from "../services/cart.service";
import {CartDetails} from "../models/cart-details";

@Component({
  selector: 'app-product-page',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {
  productsList: HomeCategoryPanel[];
  faCart: IconDefinition = faCartShopping;
  faBolt: IconDefinition = faBoltLightning;
  faHeart: IconDefinition = faHeart;
  faStar: IconDefinition = faStar;
  faLocation: IconDefinition = faLocationDot;
  wishlistEnabled: boolean;
  bankOffers: string[] = []
  pincodeForm: FormGroup;
  pincodeVerificationText: string;
  errors: string;
  currentProductId: string;
  productDetails: ProductDetails;
  currentSellerHighlights: string[];
  doneDataLoading: boolean;
  addingToCart: boolean;
  userId: string;

  readonly pincodeLimit = '6';

  constructor(private formBuilder: FormBuilder,
              private activatedRoute: ActivatedRoute,
              private productHttpService: ProductHttpService,
              private cartService: CartService) {
  }

  ngOnInit() {
    this.userId = this.activatedRoute.snapshot.queryParams?.['cId'];
    this.pincodeVerificationText = 'Check';
    this.bankOffers = [
      '10% off on Kotak Bank Credit Card, up to ₹1250 on orders of ₹5,000 and above',
      '10% off on RBL Bank Credit Card, up to ₹1250 on orders of ₹5,000 and above',
      '10% off on SBI Credit Card, up to ₹1250 on orders of ₹5,000 and above',
      '10% off on Kotak Bank Credit Card EMI Txns, up to ₹1500 on orders of ₹5,000 and above',
      '10% off on RBL Bank Credit Card EMI Txns, up to ₹1500 on orders of ₹5,000 and above',
      '10% off on SBI Credit Card EMI Txns, up to ₹1500 on orders of ₹5,000 and above',
      '10% off on Kotak Bank Debit Card, up to ₹1250 on orders of ₹5,000 and above'
    ];
    this.pincodeForm = this.formBuilder.group({
      pincode: this.formBuilder.control('')
    });
    this.currentProductId = this.activatedRoute.snapshot.params['productId'];
    this.getProductDetails();
  }

  getProductDetails(): void {
    this.productHttpService.getProductDetailsByProductId(this.currentProductId).pipe(
      tap((productDetailsResp: ProductDetails) => {
        this.productDetails = productDetailsResp;
        this.mapSellerDetails();
      }),
      finalize(() => this.doneDataLoading = true)
    ).subscribe();
  }

  mapSellerDetails(): void {
    this.currentSellerHighlights =  this.productDetails.sellerDetails.seller_highlights.split(",");
  }

  addToWishlist() {
    this.wishlistEnabled = !this.wishlistEnabled;
    console.log(this.wishlistEnabled);
  }

  checkPincode() {
    console.log(this.pincodeForm.value);
    if (this.pincodeForm.valid) {
      this.pincodeVerificationText = 'Change';
    } else {
    }
  }

//   pincodeValidation: ValidatorFn = (pincodeForm: AbstractControl) : ValidationErrors | null => {
//     const pincodeLength = pincodeForm.value.pincode.toString().length;
//     if(pincodeLength === 6) {
//       return null;
//     }
//     return {pincodeError: 'Not a valid pincode'};
// }

  addCartItem() {
    this.addingToCart = true;
    this.cartService.addCartItem(this.userId, this.productDetails, 1).pipe(
      finalize(() => this.addingToCart = false)
    ).subscribe();
  }
}
