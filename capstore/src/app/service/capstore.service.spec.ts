import { TestBed } from '@angular/core/testing';

import { CapstoreService } from './capstore.service';

describe('CapstoreService', () => {
  let service: CapstoreService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CapstoreService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
