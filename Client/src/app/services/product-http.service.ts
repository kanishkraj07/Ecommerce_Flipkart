import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CategoryPanelWithProduct, HomeCategoryPanel, PanelProductWithPanelId} from "../models/home-category-panel";
import {ProductDetails, RawProduct} from "../models/product-details";

@Injectable({
  providedIn: 'root'
})
export class ProductHttpService {

  env_url: string = 'http://localhost:6782';

  constructor(private httpClient: HttpClient) { }

   getAllCategories(): Observable<any> {
    return this.httpClient.get(`${this.env_url}/product/category`);
  }

  getAllCategoryPanels(): Observable<HomeCategoryPanel[]> {
    return this.httpClient.get(`${this.env_url}/product/category/panel`) as Observable<HomeCategoryPanel[]>
  }

  getAllCategoryPanelProducts(): Observable<PanelProductWithPanelId[]> {
    return this.httpClient.get(`${this.env_url}/product/category/panelProduct`) as Observable<PanelProductWithPanelId[]>;
  }

  getAllCategoryPanelsWithProducts(): Observable<CategoryPanelWithProduct[]> {
    return this.httpClient.get(`${this.env_url}/product/category/allPanelsWithProducts`) as Observable<CategoryPanelWithProduct[]>;
  }

  getProductDetailsByProductId(productId: string): Observable<ProductDetails> {
    return this.httpClient.get(`${this.env_url}/product/itemInfo/getProductById?productId=${productId}`) as Observable<ProductDetails>;
  }

  getProductRawData(): Observable<RawProduct[]> {
    return this.httpClient.get(`${this.env_url}/product/itemInfo/raw`) as Observable<RawProduct[]>;
  }
}
