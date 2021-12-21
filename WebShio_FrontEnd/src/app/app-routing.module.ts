import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddCategoryComponent } from './admin/add-category/add-category.component';
import { AddItemComponent } from './admin/add-item/add-item.component';
import { AdminHomeComponent } from './admin/admin-home/admin-home.component';
import { CategoryComponent } from './admin/category/category.component';
import { EditItemComponent } from './admin/edit-item/edit-item.component';
import { ViewItemsComponent } from './admin/view-items/view-items.component';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import { CartComponent } from './cart/cart.component';
import { HomeComponent } from './home/home.component';
import { SingleItemComponent } from './single-item/single-item.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'shopping-cart', component: CartComponent },
  { path: 'item/:itemId', component: SingleItemComponent },
  { path: 'admin', component: AdminHomeComponent },
  { path: 'admin/categories', component: CategoryComponent },
  { path: 'admin/add-item', component: AddItemComponent },
  { path: 'admin/edit-item/:itemId', component: EditItemComponent },
  { path: 'admin/items', component: ViewItemsComponent },
  { path: 'admin/add-category', component: AddCategoryComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: SignupComponent },
  { path: '**', redirectTo: '' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
