import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Shelf } from '../models/shelf';
import { ShelfService } from '../shelf-service/shelf-service';

@Component({
  selector: 'app-shelf-delete',
  templateUrl: './shelf-delete.component.html',
  styleUrls: ['./shelf-delete.component.css']
})
export class ShelfDeleteComponent {

  shelf : Shelf = new Shelf();
  shelves : Shelf[] = [];

    constructor(private libraryService : ShelfService, 
                private activetedRoute: ActivatedRoute, 
                private router: Router){}

    ngOnInit(): void {
      this.shelf.id = this.activetedRoute.snapshot.params['id'];
      console.log("this.shelf.id" + this.shelf.id);
        this.libraryService.deleteShelf(this.shelf.id).subscribe( data => {
          this.getShelf();
          this.goToList();
        })
    }

    private getShelf(){
      this.libraryService.getShelfList().subscribe(data => {
        this.shelves = data;
      });
    }

    goToList() {
      this.router.navigate(['shelf-all']);
    }

}
