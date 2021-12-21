import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Item } from '../model/item.model';
import { ItemService } from '../service/item.service';

@Component({
  selector: 'app-single-item',
  templateUrl: './single-item.component.html',
  styleUrls: ['./single-item.component.css'],
})
export class SingleItemComponent implements OnInit {
  item!: Item;

  constructor(
    private itemService: ItemService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    let id = Number(this.route.snapshot.paramMap.get('itemId'));

    if (id) {
      this.itemService.getOneItemFromDb(id).subscribe((item) => {
        this.item = item;
      });
    }
  }
}
