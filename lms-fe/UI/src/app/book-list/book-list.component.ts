import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { Book } from '../models/book'; 
import { BookService } from '../book-service/book-service';
import { Shelf } from '../models/shelf';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent {
    books : Book[] = [];
    book : Book = new Book();
    bookIds : number[] = [];

    shelf : Shelf = new Shelf();
    shelves : Shelf[] = [];

    constructor(private bookService:BookService, 
                private router:Router){}

    ngOnInit(): void {
      //this.getAllLibrary();
      this.getBook();
    }
  
    private getBook(){
      this.bookService.getBookList().subscribe(data => {
        this.books = data;
        
        for(this.shelf of this.shelves){
            for(this.book of this.shelf.books){
              this.book.shelfId = this.shelf.id;
              this.book.shelfName = this.shelf.name
              this.books.push(this.book);
            }    
        } 
      });
    }

    getAllBook(){
      this.router.navigate(['/book-list']);
    }

    updateBook(id: number){
      this.router.navigate(['/book-update',id]);
    }
  
    deleteBook(id: number){
      this.router.navigate(['/book-delete',id]);
    }

    detailsBook(id: number){
      this.router.navigate(['/book-details',id]);
    }
}
