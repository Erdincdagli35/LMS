import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Library } from '../models/library';
import { LibraryService } from '../library-service/library-service';
import { Shelf } from '../models/shelf';
import { ShelfService } from '../shelf-service/shelf-service';

@Component({
  selector: 'app-shelf-edit',
  templateUrl: './shelf-edit.component.html',
  styleUrls: ['./shelf-edit.component.css']
})
export class ShelfEditComponent {
    shelfTemp : Shelf = new Shelf();
    shelf : Shelf = new Shelf();

    library : Library = new Library();

    constructor(private shelfService : ShelfService, 
                private libraryService: LibraryService,
                private activetedRoute: ActivatedRoute, 
                private router: Router){}

    ngOnInit(): void {

      this.library.id = this.activetedRoute.snapshot.params['id'];

        this.libraryService.getLibraryById(this.library.id)
                           .subscribe(data => {  
          this.library = data;
        },
          error => console.log(error));

      this.shelf.id = this.activetedRoute.snapshot.params['id'];

      this.shelfService.getShelfById(this.shelf.id)
                         .subscribe(data => {  
        this.shelf = data;
      },
        error => console.log(error));
    }

    onSubmit(){
      
      this.shelfService.updateShelf(this.shelf)
                         .subscribe(data => {
        this.goToList();
      },
      error => console.log(error));
    }

    goToList() {
      this.router.navigate(['shelf-list']);
    }
}
