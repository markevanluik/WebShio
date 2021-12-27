import { Component, OnInit } from '@angular/core';
import { Item } from '../model/item.model';
import { CartService } from '../service/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent implements OnInit {
  cartItems!: Item[];
  sumOfCart = 0;

  constructor(private cartService: CartService) {}

  ngOnInit(): void {
    this.cartItems = this.cartService.getCartItems();
    this.cartItems.forEach((item) => (this.sumOfCart += item.price));
  }

  onDeleteFromCart(item: Item) {
    this.cartService.deleteFromCart(item);
    this.cartItems = this.cartService.getCartItems();
    this.sumOfCart = 0;
    this.cartItems.forEach((item) => (this.sumOfCart += item.price));
  }

  onEmptyCart() {
    this.cartService.emptyCart();
    this.cartItems = this.cartService.getCartItems();
    this.sumOfCart = 0;
  }

  onPay() {
    this.cartService
      .startPayment(this.cartItems)
      .subscribe((everyPayResponse) => {
        location.href = everyPayResponse.link;
      });
  }
}
