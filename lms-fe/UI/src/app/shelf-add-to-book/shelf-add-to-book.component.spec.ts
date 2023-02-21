import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShelfAddToBookComponent } from './shelf-add-to-book.component';

describe('ShelfAddToBookComponent', () => {
  let component: ShelfAddToBookComponent;
  let fixture: ComponentFixture<ShelfAddToBookComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShelfAddToBookComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShelfAddToBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
