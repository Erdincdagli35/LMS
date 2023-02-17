import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LibraryDeleteComponent } from './library-delete.component';

describe('LibraryDeleteComponent', () => {
  let component: LibraryDeleteComponent;
  let fixture: ComponentFixture<LibraryDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LibraryDeleteComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LibraryDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
