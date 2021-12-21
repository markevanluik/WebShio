import { Component, OnInit } from '@angular/core';
import { Item } from '../model/item.model';
import { CartService } from '../service/cart.service';
import { ItemService } from '../service/item.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  items!: Item[];

  constructor(
    private itemService: ItemService,
    private cartService: CartService
  ) {}

  ngOnInit(): void {
    this.itemService.getItemsFromDb().subscribe((items) => {
      this.items = items;
    });
  }

  onAddToCart(item: Item) {
    this.cartService.addToCart(item);
  }
}
