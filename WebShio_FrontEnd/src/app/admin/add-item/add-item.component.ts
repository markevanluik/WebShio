import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Category } from 'src/app/model/category.model';
import { Item } from 'src/app/model/item.model';
import { CategoryService } from 'src/app/service/category.service';
import { ItemService } from 'src/app/service/item.service';

@Component({
  selector: 'app-add-item',
  templateUrl: './add-item.component.html',
  styleUrls: ['./add-item.component.css'],
})
export class AddItemComponent implements OnInit {
  categories!: Category[];
  categoryNames!: string[];
  message = '';

  constructor(
    private itemService: ItemService,
    private categoryService: CategoryService
  ) {}

  ngOnInit(): void {
    this.categoryService.getCategoriesFromDb().subscribe((categories) => {
      this.categories = categories;
      this.categoryNames = categories.map((cat) => cat.categoryName);
    });
  }

  onSubmit(form: NgForm) {
    if (form.valid) {
      let category = this.categories.find(
        (c) => c.categoryName === form.value.category
      );
      if (category) {
        let item = new Item(form.value.title, form.value.price, category);
        this.itemService.addItemToDb(item).subscribe(
          (response) => {
            this.message = response.responseMessage;
            this.resetMessage();
            form.reset();
          },
          (errorRes) => {
            this.message = errorRes.error.message;
          }
        );
      }
    }
  }

  resetMessage() {
    setTimeout(() => {
      this.message = '';
    }, 5000);
  }
}
