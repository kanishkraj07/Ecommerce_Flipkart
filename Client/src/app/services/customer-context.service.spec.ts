import { TestBed } from '@angular/core/testing';

import { CustomerContextService } from './customer-context.service';

describe('CustomerContextService', () => {
  let service: CustomerContextService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CustomerContextService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
