import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShelfDeleteComponent } from './shelf-delete.component';

describe('ShelfDeleteComponent', () => {
  let component: ShelfDeleteComponent;
  let fixture: ComponentFixture<ShelfDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShelfDeleteComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShelfDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
