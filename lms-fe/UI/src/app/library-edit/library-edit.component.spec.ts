import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LibraryEditComponent } from './library-edit.component';

describe('LibraryEditComponent', () => {
  let component: LibraryEditComponent;
  let fixture: ComponentFixture<LibraryEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LibraryEditComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LibraryEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
