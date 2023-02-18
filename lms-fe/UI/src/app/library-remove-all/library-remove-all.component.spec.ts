import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LibraryRemoveAllComponent } from './library-remove-all.component';

describe('LibraryRemoveAllComponent', () => {
  let component: LibraryRemoveAllComponent;
  let fixture: ComponentFixture<LibraryRemoveAllComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LibraryRemoveAllComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LibraryRemoveAllComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
