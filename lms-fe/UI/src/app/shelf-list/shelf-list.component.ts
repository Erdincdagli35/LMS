import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { Library } from '../models/library';
import { LibraryService } from '../library-service/library-service';
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

  constructor(private shelfService:ShelfService, 
              private router:Router){}

  ngOnInit(): void {
      //this.getAllLibrary();
      this.getShelf();
    }
  
    private getShelf(){
      this.shelfService.getShelfList().subscribe(data => {
        this.shelves = data; 
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
}