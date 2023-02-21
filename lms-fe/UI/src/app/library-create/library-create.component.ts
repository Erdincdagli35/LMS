import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { Library } from '../models/library';
import { LibraryService } from '../library-service/library-service';

@Component({
  selector: 'app-library-create',
  templateUrl: './library-create.component.html',
  styleUrls: ['./library-create.component.css']
})
export class LibraryCreateComponent {
  library: Library = new Library();

  constructor(private libraryService: LibraryService, private router: Router) { }

  ngOnInit(): void {
  }

  saveLibrary() {
    this.libraryService.createLibrary(this.library).subscribe(data => {
      console.log(data);
      this.goToLibraryList();
    },
      error => console.log(error));
  }

  goToLibraryList() {
    this.router.navigate(['library-list']); 
  }
  
  onSubmit() {
    console.log(this.library);
    this.saveLibrary();
  }
}
