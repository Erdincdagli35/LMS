import { TestBed } from '@angular/core/testing';

import { ShelfService } from './shelf-service';

describe('SingerService', () => {
  let service: ShelfService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShelfService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});