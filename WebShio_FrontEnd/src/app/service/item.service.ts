import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { Item } from '../model/item.model';

@Injectable({
  providedIn: 'root',
})
export class ItemService {
  private backendUrl = 'http://localhost:8080/items';

  constructor(private http: HttpClient, private authService: AuthService) {}

  getItemsFromDb() {
    return this.http.get<Item[]>(this.backendUrl);
  }

  getOneItemFromDb(itemId: number) {
    return this.http.get<Item>(this.backendUrl + '/' + itemId);
  }

  addItemToDb(item: Item) {
    let headers = this.authService.addTokenToHeader();
    return this.http.post<{ responseMessage: string }>(this.backendUrl, item, {
      headers: headers,
    });
  }

  editItemFromDb(item: Item) {
    let headers = this.authService.addTokenToHeader();
    return this.http.put<{ responseMessage: string }>(this.backendUrl, item);
  }

  deleteItemFromDb(index: number) {
    let headers = this.authService.addTokenToHeader();
    return this.http.delete<{ responseMessage: string }>(
      this.backendUrl + '/' + index
    );
  }
}
