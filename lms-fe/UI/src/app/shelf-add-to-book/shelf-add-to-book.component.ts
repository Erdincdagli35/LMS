import { Component } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';

import { Shelf } from '../models/shelf';
import { ShelfService } from '../shelf-service/shelf-service';

import { Book } from '../models/book';

@Component({
  selector: 'app-shelf-add-to-book',
  templateUrl: './shelf-add-to-book.component.html',
  styleUrls: ['./shelf-add-to-book.component.css']
})
export class ShelfAddToBookComponent {

  shelf: Shelf = new Shelf();
  shelves: Shelf[] =[];

  book: Book = new Book();
  
  constructor(private shelfService: ShelfService, 
              private activetedRoute: ActivatedRoute, 
              private router: Router){}

  ngOnInit(): void {
    this.shelf.id = this.activetedRoute.snapshot.params['id'];

    this.shelfService.getShelfById(this.shelf.id).subscribe(data => {
      this.shelf = data;
    });
  }

  addToBook(){
    this.shelfService.addToBook(this.shelf.id,this.book).subscribe(data => {
      this.goToShelfList();
    })
  }

  goToShelfList() {
    this.router.navigate(['shelf-list']); 
  }
  
  onSubmit() {
    console.log(this.shelf);
    this.addToBook();
  }
}
