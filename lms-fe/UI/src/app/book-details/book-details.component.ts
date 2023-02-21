import { Component } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';

import { Book } from '../models/book';
import { BookService } from '../book-service/book-service';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent {

  book : Book = new Book();

  constructor(private activetedRoute: ActivatedRoute, 
              private router: Router,
              private bookService:BookService){}

  ngOnInit(): void {
    this.book.id = this.activetedRoute.snapshot.params['id'];

    this.bookService.getBookById(this.book.id).subscribe(data => {
      this.book = data;
  });
  }

  onSubmit() : void {
    this.router.navigate(['book-list']);
  }

}
