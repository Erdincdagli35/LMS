import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LibraryAddToShelfComponent } from './library-add-to-shelf.component';

describe('LibraryAddToShelfComponent', () => {
  let component: LibraryAddToShelfComponent;
  let fixture: ComponentFixture<LibraryAddToShelfComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LibraryAddToShelfComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LibraryAddToShelfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
