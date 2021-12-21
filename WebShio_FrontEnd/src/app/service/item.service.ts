import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Item } from '../model/item.model';

@Injectable({
  providedIn: 'root',
})
export class ItemService {
  private backendUrl = 'http://localhost:8080/items';

  constructor(private http: HttpClient) {}

  getItemsFromDb() {
    return this.http.get<Item[]>(this.backendUrl);
  }

  getOneItemFromDb(itemId: number) {
    return this.http.get<Item>(this.backendUrl + '/' + itemId);
  }

  addItemToDb(item: Item) {
    return this.http.post<{ responseMessage: string }>(this.backendUrl, item);
  }

  editItemFromDb(item: Item) {
    return this.http.put<{ responseMessage: string }>(this.backendUrl, item);
  }

  deleteItemFromDb(index: number) {
    return this.http.delete<{ responseMessage: string }>(
      this.backendUrl + '/' + index
    );
  }
}
