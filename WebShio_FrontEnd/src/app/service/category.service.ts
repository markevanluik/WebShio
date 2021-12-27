import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { Category } from '../model/category.model';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  private backendUrl = 'http://localhost:8080/categories';

  constructor(private http: HttpClient, private authService: AuthService) {}

  getCategoriesFromDb() {
    let headers = this.authService.addTokenToHeader();
    return this.http.get<Category[]>(this.backendUrl, {
      headers: headers,
    });
  }

  getOneCategoryFromDb(id: number) {
    return this.http.get<Category>(this.backendUrl + '/' + id);
  }

  addCategoryToDb(category: Category) {
    let headers = this.authService.addTokenToHeader();
    return this.http.post<{ responseMessage: string }>(
      this.backendUrl,
      category,
      { headers: headers }
    );
  }

  editCategoryInDb(category: Category) {
    return this.http.put<{ responseMessage: string }>(
      this.backendUrl,
      category
    );
  }

  deleteCategoryFromDb(id: number) {
    return this.http.delete<{ responseMessage: string }>(
      this.backendUrl + '/' + id
    );
  }
}
