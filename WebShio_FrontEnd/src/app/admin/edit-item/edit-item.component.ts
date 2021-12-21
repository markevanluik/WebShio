import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Item } from 'src/app/model/item.model';
import { CategoryService } from 'src/app/service/category.service';
import { ItemService } from 'src/app/service/item.service';

@Component({
  selector: 'app-edit-item',
  templateUrl: './edit-item.component.html',
  styleUrls: ['./edit-item.component.css'],
})
export class EditItemComponent implements OnInit {
  editItemForm!: FormGroup;
  itemEdited!: Item;
  categories!: string[];
  message = '';
  id!: number;

  constructor(
    private itemService: ItemService,
    private categoryService: CategoryService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.categoryService.getCategoriesFromDb().subscribe((categories) => {
      this.categories = categories.map((cat) => cat.categoryName);
    });
    console.log('before');

    this.id = Number(this.route.snapshot.paramMap.get('itemId'));
    console.log('after');

    if (this.id) {
      this.itemService.getOneItemFromDb(this.id).subscribe((item) => {
        if (item) {
          this.editItemForm = new FormGroup({
            title: new FormControl(item.title),
            price: new FormControl(item.price),
            category: new FormControl(item.category.categoryName),
          });
        }
      });
    }
  }

  onSubmit() {
    if (this.editItemForm.valid) {
      let formValue = this.editItemForm.value;
      this.itemEdited = new Item(
        formValue.title,
        formValue.price,
        { categoryName: formValue.category },
        this.id
      );
      this.itemService.editItemFromDb(this.itemEdited).subscribe(
        (res) => {
          this.message = res.responseMessage;
          this.resetMessage();
          this.editItemForm.reset();
          this.router.navigateByUrl('/admin/items');
        },
        (err) => {
          this.message = err.error.message;
          this.resetMessage();
        }
      );
    }
  }

  resetMessage() {
    setTimeout(() => {
      this.message = '';
    }, 3000);
  }
}
