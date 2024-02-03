import {Component, OnDestroy, OnInit} from '@angular/core';
import * as HomeCategoryPanelItems from 'src/app/models/home-category-panel';
import {ActivatedRoute} from "@angular/router";
import {
  CategoryPanel,
  CategoryPanelProducts,
  CategoryPanelWithProduct,
  HomeCategoryPanel
} from "src/app/models/home-category-panel";
import {ProductCacheService} from "../services/product-cache.service";
import {tap} from "rxjs";
import {ProductHttpService} from "../services/product-http.service";

@Component({
  selector: 'app-panel-interior-page',
  templateUrl: './panel-interior-page.component.html',
  styleUrls: ['./panel-interior-page.component.css']
})
export class PanelInteriorPageComponent implements OnInit {

  panelItems: HomeCategoryPanel[];
  interiorPanel: CategoryPanelWithProduct;
  interiorPanelItemsList: CategoryPanelProducts[] = [];
  panelTitle: string = '';

  constructor(private activatedRoute: ActivatedRoute,
              private productCacheService: ProductCacheService,
              private productHttpService: ProductHttpService) {
  }

  ngOnInit() {
    const panelId = this.activatedRoute.snapshot.params['id'];
    this.productHttpService.getAllCategoryPanelsWithProducts().pipe(
      tap((panelProducts: CategoryPanelWithProduct[]) => {
        this.interiorPanel = panelProducts.filter((panelInfo: CategoryPanelWithProduct) => panelInfo.panel.panelId === panelId)[0];
        this.panelTitle = this.interiorPanel.panel.panelName;
        this.interiorPanelItemsList = this.interiorPanel.products;
      })
    ).subscribe();
  }
}
