import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LibraryRemoveToShelfComponent } from './library-remove-to-shelf.component';

describe('LibraryRemoveToShelfComponent', () => {
  let component: LibraryRemoveToShelfComponent;
  let fixture: ComponentFixture<LibraryRemoveToShelfComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LibraryRemoveToShelfComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LibraryRemoveToShelfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
