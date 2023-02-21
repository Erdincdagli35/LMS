import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

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

    constructor(private shelfService : ShelfService, 
                private activetedRoute: ActivatedRoute, 
                private router: Router){}

    ngOnInit(): void {
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
