import {Component, Input, OnInit} from '@angular/core';
import {CategoryItem} from "../../models/home-category-item";
import * as CategoryItems from "../../models/home-category-item";
import {ProductHttpService} from "../../services/product-http.service";
import {tap} from "rxjs";

@Component({
  selector: 'app-category-items',
  templateUrl: './category-items.component.html',
  styleUrls: ['./category-items.component.css']
})
export class CategoryItemsComponent implements OnInit{
  constructor(private productHttpService: ProductHttpService) {
  }

  @Input() isInternalCategoryItems: boolean;

  categoryItemsList: CategoryItem[] = [];

  ngOnInit() {
    this.productHttpService.getAllCategories().pipe(
      tap((categories: CategoryItem[]) => {
        this.categoryItemsList = categories;
      })
    ).subscribe();
  }
}

