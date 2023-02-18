import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShelfDetailsComponent } from './shelf-details.component';

describe('ShelfDetailsComponent', () => {
  let component: ShelfDetailsComponent;
  let fixture: ComponentFixture<ShelfDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShelfDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShelfDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
