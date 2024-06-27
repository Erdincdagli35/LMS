import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { Library } from '../models/library';
import { Book } from '../models/book';

import { Shelf } from '../models/shelf';
import { ShelfService } from '../shelf-service/shelf-service';



@Component({
  selector: 'app-shelf-list',
  templateUrl: './shelf-list.component.html',
  styleUrls: ['./shelf-list.component.css']
})
export class ShelfListComponent {
  libraries : Library[] = [];
  library : Library = new Library();

  shelf : Shelf = new Shelf();
  shelves : Shelf[] = [];
  shelfIds : number[] = [];

  books : Book[] = [];
  book : Book = new Book();
  bookIds : number[] = [];
  bookId : number = 0;

  constructor(private shelfService:ShelfService, 
              private router:Router){}

  ngOnInit(): void {
      //this.getAllLibrary();
      this.getShelf();
    }
  

    private getShelf(){
      this.shelfService.getShelfList().subscribe(data => {
        this.shelves = data;
        
        for(this.shelf of this.shelves){
            for(this.book of this.shelf.books){
              this.book.shelfId = this.shelf.id;
              this.book.shelfName = this.shelf.name
              this.books.push(this.book);
            }    
        } 
      });
    }


    getAllShelf(){
      this.router.navigate(['/shelf-list']);
    }

    saveShelf(shelf: Shelf){
      this.router.navigate(['/shelf-create']);
    }

    updateShelf(id: number){
      this.router.navigate(['/shelf-update',id]);
    }
  
    deleteShelf(id: number){
      this.router.navigate(['/shelf-delete',id]);
    }

    detailsShelf(id: number){
      this.router.navigate(['/shelf-details',id]);
    }

    addToBook(id : number){
      this.router.navigate(['/shelf-add-to-book',id]);
    }
}
