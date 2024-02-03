import { Injectable } from '@angular/core';
import {CategoryPanelProducts, HomeCategoryPanel, PanelProductWithPanelId} from "../models/home-category-panel";
import {finalize, forkJoin, map, Observable, tap} from "rxjs";
import {ProductHttpService} from "./product-http.service";

@Injectable({
  providedIn: 'root'
})
export class ProductCacheService {

  constructor(private productHttpService: ProductHttpService) {
  }

  getAndMapAllPanelProducts(): Observable<HomeCategoryPanel[]> {
    const observables = [];
    observables.push(this.productHttpService.getAllCategoryPanels(), this.productHttpService.getAllCategoryPanelProducts());
    return forkJoin(observables).pipe(
      map((response: any) => {
          const panels: HomeCategoryPanel[] = response[0];
          const products: PanelProductWithPanelId[] = response[1];
          return panels.map((panel: HomeCategoryPanel) => {
            return {
              ...panel,
              products: this.getProducts(panel, products)
            } as HomeCategoryPanel
          })
      }));
  }

  getProducts(panel: any, products: any): CategoryPanelProducts[] {
    return products.filter((product: any) => product.panelId === panel.panel_id);
  }

}
