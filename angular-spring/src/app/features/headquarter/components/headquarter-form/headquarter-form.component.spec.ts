import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeadquarterFormComponent } from './headquarter-form.component';

describe('HeadquarterFormComponent', () => {
  let component: HeadquarterFormComponent;
  let fixture: ComponentFixture<HeadquarterFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HeadquarterFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HeadquarterFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
