import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Product } from '../../model/product';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'product-form',
  imports: [FormsModule],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent {

  @Input() public product: Product = {
    id: 0,
    name: '',
    description: '',
    price: 0
    }

    @Output() addProductEvent = new EventEmitter();
    onSubmit(): void {
      console.log(this.product);
      this.addProductEvent.emit(this.product);
    }
      
}
