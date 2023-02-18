import { Component } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';

import { Library } from '../models/library';
import { LibraryService } from '../library-service/library-service';
import { Shelf } from '../models/shelf';

@Component({
  selector: 'app-library-remove-to-shelf',
  templateUrl: './library-remove-to-shelf.component.html',
  styleUrls: ['./library-remove-to-shelf.component.css']
})
export class LibraryRemoveToShelfComponent {
  library: Library = new Library();
  libraries: Library[] =[];

  shelf: Shelf = new Shelf();
  shelfIds: number[] = [];

  constructor(private libraryService: LibraryService, 
              private activetedRoute: ActivatedRoute, 
              private router: Router,){}

  ngOnInit(): void {
    this.library.id = this.activetedRoute.snapshot.params['id'];

    this.libraryService.getLibraryById(this.library.id).subscribe(data => {
      this.library = data;
    });
  }

  removeToShelf(){
    this.libraryService.removeToShelf(this.library.id,this.shelfIds).subscribe(data => {
      this.goToLibraryList();
    })
  }

  goToLibraryList() {
    this.router.navigate(['libraries-all']); 
  }
  
  onSubmit() {
    console.log(this.library);
    this.removeToShelf();
  }
}
