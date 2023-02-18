import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { Library } from '../models/library';
import { LibraryService } from '../library-service/library-service';
import { Shelf } from '../models/shelf';

@Component({
  selector: 'app-library-list',
  templateUrl: './library-list.component.html',
  styleUrls: ['./library-list.component.css']
})
export class LibraryListComponent {
    libraries : Library[] = [];
    library : Library = new Library();

    shelf : Shelf = new Shelf();
    shelves : Shelf[] = [];
    shelfIds : number[] = [];

    constructor(private libraryService:LibraryService, 
                private router:Router){}

    ngOnInit(): void {
      //this.getAllLibrary();
      this.getLibrary();
    }
  
    private getLibrary(){
      this.libraryService.getLibraryList().subscribe(data => {
        this.libraries = data;
        
        for(this.library of this.libraries){
            for(this.shelf of this.library.shelves){
              this.shelf.libraryId = this.library.id;
              this.shelf.libraryName = this.library.name;
              this.shelves.push(this.shelf);
            }    
        } 
      });
    }

    getAllLibrary(){
      this.router.navigate(['/library-list']);
    }

    saveLibrary(library: Library){
      this.router.navigate(['/library-create']);
    }

    updateLibrary(id: number){
      this.router.navigate(['/library-update',id]);
    }
  
    deleteLibrary(id: number){
      this.router.navigate(['/library-delete',id]);
    }

    detailsLibrary(id: number){
      this.router.navigate(['/library-details',id]);
    }

    addToShelf(id : number){
      this.router.navigate(['/library-add-to-shelf',id]);
    }

    removeToShelf(id : number, shelfIds: number[]){
      this.router.navigate(['/library-remove-to-shelf', id, shelfIds]);
    }
}
