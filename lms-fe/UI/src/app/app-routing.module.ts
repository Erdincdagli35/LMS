import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


import { LibraryCreateComponent } from './library-create/library-create.component'; 
import { LibraryListComponent } from './library-list/library-list.component'; 
import { LibraryEditComponent } from './library-edit/library-edit.component'; 
import { LibraryDeleteComponent } from './library-delete/library-delete.component'; 
import { LibraryDetailsComponent } from './library-details/library-details.component'; 

import { LibraryAddToShelfComponent } from './library-add-to-shelf/library-add-to-shelf.component';
import { LibraryRemoveToShelfComponent } from './library-remove-to-shelf/library-remove-to-shelf.component';
import { LibraryRemoveAllComponent } from './library-remove-all/library-remove-all.component';

import { ShelfListComponent } from './shelf-list/shelf-list.component';
import { ShelfDeleteComponent } from './shelf-delete/shelf-delete.component';
import { ShelfDetailsComponent } from './shelf-details/shelf-details.component';
import { ShelfEditComponent } from './shelf-edit/shelf-edit.component';
import { ShelfAddToBookComponent } from './shelf-add-to-book/shelf-add-to-book.component';
import { BookListComponent } from './book-list/book-list.component';
import { BookDetailsComponent } from './book-details/book-details.component';
import { BookDeleteComponent } from './book-delete/book-delete.component';
import { BookEditComponent } from './book-edit/book-edit.component';

const routes: Routes = [
  { path: '', redirectTo: 'library', pathMatch: 'full' },

  { path: 'library-create', component: LibraryCreateComponent },
  { path: 'library-update/:id', component: LibraryEditComponent},
  { path: 'libraries-all', component: LibraryListComponent },
  { path: 'library-delete/:id', component: LibraryDeleteComponent},
  { path: 'library-details/:id', component: LibraryDetailsComponent},
  
  { path: 'library-add-to-shelf/:id', component: LibraryAddToShelfComponent},
  { path: 'library-remove-to-shelf/:id/:shelfIds', component: LibraryRemoveToShelfComponent},
  { path: 'library-remove-all-shelves/:id', component: LibraryRemoveAllComponent},
  
  { path: 'shelf-all',component: ShelfListComponent},
  { path: 'shelf-delete/:id',component: ShelfDeleteComponent},
  { path: 'shelf-details/:id', component: ShelfDetailsComponent},
  { path: 'shelf-update/:id', component: ShelfEditComponent},

  { path: 'shelf-add-to-book/:id', component: ShelfAddToBookComponent},

  { path: 'book-all', component: BookListComponent},
  { path: 'book-delete/:id', component: BookDeleteComponent},
  { path: 'book-details/:id', component: BookDetailsComponent},
  { path: 'book-update/:id', component:BookEditComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
