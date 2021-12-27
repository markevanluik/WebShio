import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddCategoryComponent } from './admin/add-category/add-category.component';
import { AddItemComponent } from './admin/add-item/add-item.component';
import { AdminHomeComponent } from './admin/admin-home/admin-home.component';
import { CategoryComponent } from './admin/category/category.component';
import { EditItemComponent } from './admin/edit-item/edit-item.component';
import { ViewItemsComponent } from './admin/view-items/view-items.component';
import { AuthGuard } from './auth/auth.guard';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import { CartComponent } from './cart/cart.component';
import { HomeComponent } from './home/home.component';
import { SingleItemComponent } from './single-item/single-item.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'shopping-cart', component: CartComponent },
  { path: 'item/:itemId', component: SingleItemComponent },
  { path: 'admin', component: AdminHomeComponent, canActivate: [AuthGuard] },
  {
    path: 'admin/categories',
    component: CategoryComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'admin/add-item',
    component: AddItemComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'admin/edit-item/:itemId',
    component: EditItemComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'admin/items',
    component: ViewItemsComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'admin/add-category',
    component: AddCategoryComponent,
    canActivate: [AuthGuard],
  },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: SignupComponent },
  { path: '**', redirectTo: '' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
