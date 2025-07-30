import { TestBed } from '@angular/core/testing';

import { ErrorResponseHandlerService } from './error-response-handler.service';

describe('ErrorResponseHandlerService', () => {
  let service: ErrorResponseHandlerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ErrorResponseHandlerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
