import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Library } from '../models/library';
import { LibraryService } from '../library-service/library-service';

@Component({
  selector: 'app-library-edit',
  templateUrl: './library-edit.component.html',
  styleUrls: ['./library-edit.component.css']
})
export class LibraryEditComponent{
    libraryTemp : Library = new Library();
    library : Library = new Library();

    constructor(private libraryService : LibraryService, 
                private activetedRoute: ActivatedRoute, 
                private router: Router){}

    ngOnInit(): void {
      this.library.id = this.activetedRoute.snapshot.params['id'];
      console.log("library.id : " + this.library.id);
      this.libraryService.getLibraryById(this.library.id)
                         .subscribe(data => {  
        this.library = data;
        console.log("library : " + this.library.name);
      },
        error => console.log(error));
    }

    onSubmit(){
       console.log("onSubmit : library : " + this.library.name);
      this.libraryService.updateLibrary(this.library)
                         .subscribe(data => {
        console.log("updateLibrary");
        this.goToList();
      },
      error => console.log(error));
    }

    goToList() {
      this.router.navigate(['library-list']);
    }
}
