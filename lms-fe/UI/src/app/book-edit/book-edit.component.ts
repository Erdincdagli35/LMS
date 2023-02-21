import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Book } from '../models/book';
import { BookService } from '../book-service/book-service'; 


@Component({
  selector: 'app-book-edit',
  templateUrl: './book-edit.component.html',
  styleUrls: ['./book-edit.component.css']
})
export class BookEditComponent {

  bookTemp : Book = new Book();
  book : Book = new Book();

  constructor(private bookService : BookService, 
              private activetedRoute: ActivatedRoute, 
              private router: Router){}

  ngOnInit(): void {
    this.book.id = this.activetedRoute.snapshot.params['id'];

    this.bookService.getBookById(this.book.id)
                       .subscribe(data => {  
      this.book = data;
    },
      error => console.log(error));
  }

  onSubmit(){
    this.bookService.updateBook(this.book)
                       .subscribe(data => {
      this.goToList();
    },
    error => console.log(error));
  }

  goToList() {
    this.router.navigate(['book-list']);
  }
}
