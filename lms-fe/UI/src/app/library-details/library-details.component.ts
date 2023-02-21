import { Component } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';

import { Library } from '../models/library';
import { LibraryService } from '../library-service/library-service';

@Component({
  selector: 'app-library-details',
  templateUrl: './library-details.component.html',
  styleUrls: ['./library-details.component.css']
})
export class LibraryDetailsComponent {
  library : Library = new Library();

  constructor(private activetedRoute: ActivatedRoute, 
              private router: Router,
              private libraryService:LibraryService){}

  ngOnInit(): void {
    this.library.id = this.activetedRoute.snapshot.params['id'];

    this.libraryService.getLibraryById(this.library.id).subscribe(data => {
      this.library = data;
  });
  }

  onSubmit() : void {
    this.router.navigate(['library-list']);
  }
}
