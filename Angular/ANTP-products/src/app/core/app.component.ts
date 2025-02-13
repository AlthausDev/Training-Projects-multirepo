import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ProductsComponent } from '../components/products/products.component';
import { Product } from '../model/product';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [ProductsComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'] 
})
export class AppComponent implements OnInit {
  products: Product[] = [];

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
}
