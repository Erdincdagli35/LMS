import { Component } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';

import { Library } from '../models/library';
import { LibraryService } from '../library-service/library-service';
import { Shelf } from '../models/shelf';

@Component({
  selector: 'app-library-add-to-shelf',
  templateUrl: './library-add-to-shelf.component.html',
  styleUrls: ['./library-add-to-shelf.component.css']
})
export class LibraryAddToShelfComponent {
  library: Library = new Library();
  libraries: Library[] =[];

  shelf: Shelf = new Shelf();

  constructor(private libraryService: LibraryService, 
              private activetedRoute: ActivatedRoute, 
              private router: Router,){}

  ngOnInit(): void {
    this.library.id = this.activetedRoute.snapshot.params['id'];

    this.libraryService.getLibraryById(this.library.id).subscribe(data => {
      this.library = data;
    });
  }

  addToShelf(){
    this.libraryService.addToShelf(this.library.id,this.library).subscribe(data => {
      this.goToLibraryList();
    })
  }

  goToLibraryList() {
    this.router.navigate(['libraries-all']); 
  }
  
  onSubmit() {
    console.log(this.library);
    this.addToShelf();
  }
}
