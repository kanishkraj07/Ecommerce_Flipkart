import { TestBed } from '@angular/core/testing';

import { PaymentHttpService } from './payment-http.service';

describe('PaymentHttpService', () => {
  let service: PaymentHttpService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PaymentHttpService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
