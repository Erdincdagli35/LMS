import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';

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

@NgModule({
  declarations: [
    AppComponent,
    LibraryCreateComponent,
    LibraryListComponent,
    LibraryEditComponent,
    LibraryDeleteComponent,
    LibraryDetailsComponent,
    LibraryAddToShelfComponent,
    LibraryRemoveToShelfComponent,
    LibraryRemoveAllComponent,
    ShelfListComponent,
    ShelfDeleteComponent,
    ShelfDetailsComponent,
    ShelfEditComponent
    ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    RouterModule.forRoot([
      {path: 'library-create', component:LibraryCreateComponent},
      {path: 'library-list', component:LibraryListComponent},
      {path: 'library-update', component:LibraryEditComponent},
      {path: 'library-delete', component:LibraryDeleteComponent},
      {path: 'library-details/:id', component: LibraryDetailsComponent},
      
      {path: 'library-add-to-shelf/:id', component: LibraryAddToShelfComponent},
      {path: 'library-remove-to-shelf/:id/:shelfIds', component: LibraryRemoveToShelfComponent},
      {path: 'library-remove-all-shelves', component: LibraryRemoveAllComponent},

      {path: 'shelf-all',component: ShelfListComponent},
      {path: 'shelf-delete/:id',component: ShelfDeleteComponent},
      {path: 'shelf-list',component: ShelfListComponent}
    ])
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
