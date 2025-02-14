import { Component, OnInit, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ProductsComponent } from '../components/products/products.component';
import { Product } from '../model/product';
import { FormComponent } from "../components/form/form.component";
import Swal from 'sweetalert2';

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
    if (product.id > 0) {
      this.products = this.products.map(prod => {
        return prod.id === product.id ? { ...product } : prod;
      });

      Swal.fire({       
        text: "Producto actualizado con éxito!",
        icon: "success"

      });
    } else {
      this.products = [
        ...this.products,
        { ...product, id: this.countId() }        
      ];
      this.countId.update(id => id + 1);
      Swal.fire({       
        text: "Producto creado con éxito!",
        icon: "success"
      });
    }
    
  }

  onRemoveProductEvent(productId: number) : void {

    Swal.fire({
      title: "Confirmar borrado",
      text: "Esta acción no se puede revertir!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Eliminar"
    }).then((result) => {
      if (result.isConfirmed) {
        this.products = this.products.filter(product => product.id != productId);
        Swal.fire({
          title: "Borrado!",
          text: "La entrada ha sido eliminada con éxito",
          icon: "success"
        });
      }
    });   

    }

  onUpdateProductEvent(product: Product) : void {
    this.productSelected = {... product}
  }
}
