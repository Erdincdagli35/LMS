import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Book } from '../models/book';
import { BookService } from '../book-service/book-service';

@Component({
  selector: 'app-book-delete',
  templateUrl: './book-delete.component.html',
  styleUrls: ['./book-delete.component.css']
})
export class BookDeleteComponent {

  book : Book = new Book();
  books : Book[] = [];

    constructor(private bookService : BookService, 
                private activetedRoute: ActivatedRoute, 
                private router: Router){}

    ngOnInit(): void {
      this.book.id = this.activetedRoute.snapshot.params['id'];

      this.bookService.deleteBook(this.book.id).subscribe( data => {
        this.goToList();  
        this.getBook();
        })
    }

    private getBook(){
      this.bookService.getBookList().subscribe(data => {
        this.books = data;
      });
    }

    goToList() {
      this.router.navigate(['book-list']);
    }
}
