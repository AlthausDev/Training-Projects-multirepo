import { Component, EventEmitter, Input, Output} from '@angular/core';
import { Product } from '../../model/product';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'table-product',
  imports: [],
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent {

  @Input() products: Product[] = [];

  title = "Lista de productos"

  @Output() updateProductEvent = new EventEmitter();
  @Output() removeProductEvent = new EventEmitter();
  
  onUpdateProduct(product: Product) : void {
      this.updateProductEvent.emit(product)
    }

  onDeleteProduct(productId: number) : void {
    this.removeProductEvent.emit(productId)
  }    
}
