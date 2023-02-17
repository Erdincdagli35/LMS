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

@NgModule({
  declarations: [
    AppComponent,
    LibraryCreateComponent,
    LibraryListComponent,
    LibraryEditComponent,
    LibraryDeleteComponent,
    LibraryDetailsComponent
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
      {path: 'library-delete', component:LibraryDeleteComponent}
    ])
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
