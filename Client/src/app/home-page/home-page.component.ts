import {AfterViewInit, Component, ElementRef, OnInit, QueryList, ViewChildren} from '@angular/core';
import {CategoryItem} from 'src/app/models/home-category-item';
import {faAngleRight, faLessThan, faAngleLeft} from "@fortawesome/free-solid-svg-icons";
import {CategoryPanelProducts, CategoryPanelWithProduct, HomeCategoryPanel} from "../models/home-category-panel";
import {ActivatedRoute, Router} from "@angular/router";
import {finalize, forkJoin, map, Observable, shareReplay, tap} from "rxjs";
import {ProductCacheService} from "../services/product-cache.service";
import {IconDefinition} from "@fortawesome/free-brands-svg-icons";
import {AuthenticationService} from "../services/authentication.service";
import {CustomerContextService} from "../services/customer-context.service";
import {ProductHttpService} from "../services/product-http.service";
import { LogOut } from 'lucide-angular';
import {RawProduct} from "../models/product-details";
import {CartHttpService} from "../services/cart-http.service";
import {CartService} from "../services/cart.service";
import {CartItem} from "../models/cart-details";



@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  faAngleRight: IconDefinition = faAngleRight;
  faAngleLeft: IconDefinition = faAngleLeft;
  logout = LogOut
  panelList: HomeCategoryPanel[] = [];


  images: string[] = ['https://rukminim1.flixcart.com/fk-p-flap/1600/270/image/a2d45385904d2bfa.jpg?q=20',
                      'https://rukminim1.flixcart.com/fk-p-flap/1600/270/image/7e6f7f22ab2b746f.jpg?q=20',
                      'https://rukminim1.flixcart.com/fk-p-flap/1600/270/image/039ec2390c9d9c67.jpeg?q=20',
                      'https://rukminim1.flixcart.com/fk-p-flap/1600/270/image/5f515750b4e4ec8e.jpeg?q=20',
                      'https://rukminim1.flixcart.com/fk-p-flap/1600/270/image/17821708e64ed52d.jpg?q=20',
                      'https://rukminim1.flixcart.com/fk-p-flap/1600/270/image/4732d7a1b8921f82.jpg?q=20',
                      'https://rukminim1.flixcart.com/fk-p-flap/1600/270/image/81a6ce54a0a3230a.jpg?q=20'];
  ads: string[] = [
    'https://static-assets-web.flixcart.com/www/linchpin/batman-returns/images/fk-default-image-75ff340b.png?q=90',
    'https://cdn0.desidime.com/cdn-cgi/image/fit=crop,f=auto,onerror=redirect,w=1200,h=1200,q=90/topics/photos/1657290/original/Best-Smartphone-Deals-on-Flipkart-Big-Billion-Day-Sale.jpg'
  ];
  index = 0;
  deviation: any;
  sliderInterval: any;
  panelsInfo: CategoryPanelWithProduct[];
  finishesLoading: boolean;
  customerId: string;

  @ViewChildren('panelItems') panelItemsRef: QueryList<ElementRef>;

  @ViewChildren('sliderImages') sliderImages: QueryList<any>;
  sliderRev: boolean;
  constructor(private router: Router,
              private productCacheService: ProductCacheService,
              private productHttpService: ProductHttpService,
              private authenticationService: AuthenticationService,
              private customerContextService: CustomerContextService,
              private activatedRoute: ActivatedRoute,
              private cartService: CartService) {
  }

  ngOnInit() {
    this.customerId = this.activatedRoute.snapshot.queryParams['cId']
    if(!this.authenticationService.isUserAuthenticated() && this.customerId) {
      this.authenticationService.updateCustomerIdParam({cId: null});
    }
    this.initializeUserAuthentication();
    this.startSlider();

    this.loadCategoryPanelsData().pipe(
      finalize(() => {
        this.finishesLoading = true;
        }
      )
    ).subscribe();
  }

  loadCategoryPanelsData(): Observable<CategoryPanelWithProduct[]> {
    return this.productHttpService.getAllCategoryPanelsWithProducts().pipe(
      tap((panelsInfo: CategoryPanelWithProduct[]) => {
        this.panelsInfo = panelsInfo;
        let gotMiniPanel: boolean = false;
        let count: number = 0;
        this.panelsInfo.forEach((panelsInfo: CategoryPanelWithProduct) => {
          if (gotMiniPanel && !panelsInfo.panel.miniPanel) {
            panelsInfo.panel.brandedAds = this.ads[count++];
            gotMiniPanel = false;
          } else if (panelsInfo.panel.miniPanel) {
            gotMiniPanel = true;
          }
        });
      })
    );
  }

  initializeUserAuthentication(): void {
    if (this.authenticationService.isUserAuthenticated()) {
      this.authenticationService.setCustomerAuthDetails();
      this.customerContextService.setInitialCustomerDetails(this.authenticationService.customerAuthId, this.authenticationService.customerAuthSub);
    }
  }


  startSlider(): void {
    this.sliderInterval = window.setInterval(() => {
      if(this.index === this.sliderImages.length - 1) {
        this.deviation = {'transform': 'translateX(0)'};
        this.index = 0;
      } else {
        const incrementWidth = 100 * (this.index + 1);
        this.deviation = {'transform': 'translateX(-' + incrementWidth + '%)'};
        this.index++;
      }
    }, 3000);
  }

  navigateToInternalPanelPage(id: string) : void {
    this.router.navigate(['/internal-products/' + `${id}`]);
  }

  redirectToProductPage(panelId: string, productId: string) {
    this.router.navigate([ '/' + `${panelId}` + '/product-details/pid/' + `${productId}`], {
      queryParams: {cId: this.authenticationService.customerAuthId }
    });
  }

  previousSlide(): void {
    clearInterval(this.sliderInterval);
    if(this.index === 0) {
      const backWordDeviation = (this.sliderImages.length - 1) * 100;
      this.deviation = {'transform': 'translateX(-' + backWordDeviation + '%)'};
      this.index = this.sliderImages.length - 1;
      this.sliderRev = true;
    } else {
      const incrementWidth = 100 * (this.index - 1);
      this.deviation = {'transform': 'translateX(-'+ incrementWidth + '%)'};
      this.index--;
    }
    this.startSlider();
  }

  nextSlide(): void {
    clearInterval(this.sliderInterval);
    if(this.index === this.sliderImages.length - 1) {
      this.deviation = {'transform': 'translateX(0)'};
      this.index = 0;
    } else {
      const incrementWidth = 100 * (this.index + 1);
      this.deviation = {'transform': 'translateX(-' + incrementWidth + '%)'};
      this.index++;
    }
    this.startSlider();
  }

  expandPanel(panelIndex: number): void {
    console.log(panelIndex);
  }
}
