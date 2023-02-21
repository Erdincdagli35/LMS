import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Book } from '../models/book';
import { Shelf } from '../models/shelf';

@Injectable({
  providedIn: 'root'
})
export class ShelfService {
  private baseURL = "http://localhost:8080/shelves";
  private addToShelfURL = "http://localhost:8080/shelves/addBook";

  constructor(private httpClient: HttpClient) { }

  getShelfList(): Observable<Shelf[]> {
    return this.httpClient.get<Shelf[]>(this.baseURL);
  }

  deleteShelf(id: number): Observable<Object> {
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }

  getShelfById(id: number): Observable<Shelf> {
    return this.httpClient.get<Shelf>(`${this.baseURL}/${id}`);
  }
  
  updateShelf(shelf: Shelf): Observable<Object> {
    return this.httpClient.put(this.baseURL, shelf);
  }

  addToBook(id : number, book : Book): Observable<Object>{
    return this.httpClient.post(`${this.addToShelfURL}/${id}`,book);
  }
}