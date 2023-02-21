import { Component } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';

import { Shelf } from '../models/shelf';
import { ShelfService } from '../shelf-service/shelf-service';

@Component({
  selector: 'app-shelf-details',
  templateUrl: './shelf-details.component.html',
  styleUrls: ['./shelf-details.component.css']
})
export class ShelfDetailsComponent {

  shelf : Shelf = new Shelf();

  constructor(private activetedRoute: ActivatedRoute, 
              private router: Router,
              private shelfService:ShelfService){}

  ngOnInit(): void {
    this.shelf.id = this.activetedRoute.snapshot.params['id'];

    this.shelfService.getShelfById(this.shelf.id).subscribe(data => {
      this.shelf = data;
  });
  }

  onSubmit() : void {
    this.router.navigate(['shelf-list']);
  }
}
