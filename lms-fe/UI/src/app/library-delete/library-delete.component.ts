import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Library } from '../models/library';
import { LibraryService } from '../library-service/library-service';

@Component({
  selector: 'app-library-delete',
  templateUrl: './library-delete.component.html',
  styleUrls: ['./library-delete.component.css']
})
export class LibraryDeleteComponent {

  library : Library = new Library();
  libraries : Library[] = [];

    constructor(private libraryService : LibraryService, 
                private activetedRoute: ActivatedRoute, 
                private router: Router){}

    ngOnInit(): void {
      this.library.id = this.activetedRoute.snapshot.params['id'];
      console.log("this.library.id" + this.library.id);
        this.libraryService.deleteLibrary(this.library.id).subscribe( data => {
          this.goToList();
          this.getLibrary();
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
