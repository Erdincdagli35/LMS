import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Library } from '../models/library';
import { LibraryService } from '../library-service/library-service';

import { Shelf } from '../models/shelf';

@Component({
  selector: 'app-library-remove-all',
  templateUrl: './library-remove-all.component.html',
  styleUrls: ['./library-remove-all.component.css']
})
export class LibraryRemoveAllComponent {
  library : Library = new Library();
  libraries : Library[] = [];

  shelf : Shelf = new Shelf();

  constructor(private libraryService : LibraryService, 
                private activetedRoute: ActivatedRoute, 
                private router: Router){}

  ngOnInit(): void {
      this.library.id = this.activetedRoute.snapshot.params['id'];
      
      this.libraryService.removeAllShelves(this.library.id).subscribe( data => {
          this.getLibrary();
          this.goToList();
        })
    }

    private getLibrary(){
      this.libraryService.getLibraryList().subscribe(data => {
        this.libraries = data;
      });
    }


    goToList() {
      this.router.navigate(['library-list']);
    }
}
