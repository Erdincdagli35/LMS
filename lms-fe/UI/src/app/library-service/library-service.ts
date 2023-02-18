import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Library } from '../models/library';
import { Shelf } from '../models/shelf';

@Injectable({
  providedIn: 'root'
})
export class LibraryService {

  private baseURL = "http://localhost:8080/libraries";
  private deleteURL = "http://localhost:8080/libraries";

  private addToShelfURL = "http://localhost:8080/libraries/addShelf";

  constructor(private httpClient: HttpClient) { }

  createLibrary(library: Library): Observable<Object> {
    return this.httpClient.post(this.baseURL, library);
  }

  getLibraryList(): Observable<Library[]> {
    return this.httpClient.get<Library[]>(this.baseURL);
  }

  deleteLibrary(id: number): Observable<Object> {
    return this.httpClient.delete(`${this.deleteURL}/${id}`);
  }

  getLibraryById(id: number): Observable<Library> {
    return this.httpClient.get<Library>(`${this.baseURL}/${id}`);
  }
  
  updateLibrary(library: Library): Observable<Object> {
    return this.httpClient.put(this.baseURL, library);
  }

  addToShelf(id : number, shelf : Shelf): Observable<Object>{
    return this.httpClient.post(`${this.addToShelfURL}/${id}`,shelf);
  }

  removeToShelf(id : number, shelfIds : number[]): Observable<Object>{
    return this.httpClient.delete(`${this.addToShelfURL}/${id}/${shelfIds}`);
  }
}