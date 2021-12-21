import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Item } from '../model/item.model';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private cartItemsInService: Item[] = [];
  private cartItemsChanged = new Subject<number>();
  private backendUrl = 'http://localhost:4200';

  constructor(private http: HttpClient) {}

  startPayment(sumOfCart: number) {
    return this.http.post<any>(this.backendUrl, sumOfCart);
  }

  deleteFromCart(item: Item) {
    let index = this.cartItemsInService.indexOf(item);
    this.cartItemsInService.splice(index, 1);
    this.cartItemsChanged.next(this.calculateSumOfCart());
  }

  getCartItems() {
    return this.cartItemsInService.slice();
  }

  addToCart(item: Item) {
    this.cartItemsInService.push(item);
    this.cartItemsChanged.next(this.calculateSumOfCart());
  }

  emptyCart() {
    this.cartItemsInService = [];
    this.cartItemsChanged.next(this.calculateSumOfCart());
  }

  getCartItemsChangedSubject() {
    return this.cartItemsChanged;
  }

  calculateSumOfCart() {
    let sumOfCart = 0;
    this.cartItemsInService.forEach((item) => (sumOfCart += item.price));
    return sumOfCart;
  }
}
