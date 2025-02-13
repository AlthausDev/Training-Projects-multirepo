import { Component, OnInit, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ProductsComponent } from '../components/products/products.component';
import { Product } from '../model/product';
import { FormComponent } from "../components/form/form.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [ProductsComponent, FormComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'] 
})
export class AppComponent implements OnInit {

  products: Product[] = [];
  countId = signal(4);
  productSelected: Product = {id: 0, name: '', description:'', price: 0}

  ngOnInit(): void {
    this.products = [
      {
        id: 1,
        name: 'producto 1',
        price: 1000,
        description: 'Descripcion producto 1'
      },
      {
        id: 2,
        name: 'producto 2',
        price: 2000,
        description: 'Descripcion producto 2'
      },
      {
        id: 3,
        name: 'producto 3',
        price: 3000,
        description: 'Descripcion producto 3'
      },
      {
        id: 4,
        name: 'producto 4',
        price: 4000,
        description: 'Descripcion producto 4'
      }
    ];
  }

  addProduct(product: Product): void {
    this.products = [... this.products, {... product, id: this.countId()}]
    this.countId.update(id => id + 1)
  }

  onRemoveProductEvent(productId: number) : void {
    this.products = this.products.filter(product => product.id != productId);

    }

  onUpdateProductEvent(product: Product) : void {
    this.productSelected = {... product}
  }
}
