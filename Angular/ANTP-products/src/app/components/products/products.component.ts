import { Component, EventEmitter, Input, Output, ChangeDetectionStrategy } from '@angular/core';
import { Product } from '../../model/product';


@Component({
  selector: 'table-product',
  imports: [],
  templateUrl: './products.component.html',
  changeDetection: ChangeDetectionStrategy.Eager,
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
