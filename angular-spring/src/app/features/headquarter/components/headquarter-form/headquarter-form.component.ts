import { Component, computed, effect, inject } from '@angular/core';
import { HeadquarterService } from '../../services/headquarter.service';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { HeadquarterCreateBody } from '../../models/headquarter';
import { CountryService } from '../../../country/services/country.service';
import { PublisherService } from '../../../publisher/services/publisher.service';

@Component({
  selector: 'app-headquarter-form',
  imports: [ReactiveFormsModule],
  templateUrl: './headquarter-form.component.html',
  styleUrl: './headquarter-form.component.css'
})
export class HeadquarterFormComponent {
  // INJEÇÃO DE DEPENDÊNCIAS
  private formBuilder = inject(FormBuilder);
  private headquarterService = inject(HeadquarterService);
  private countryService = inject(CountryService);
  private publisherService = inject(PublisherService);

  // SIGNALS
  public loading = computed(() => this.headquarterService.loading());
  public error = computed(() => this.headquarterService.error());
  public success = computed(() => this.headquarterService.success());
  public operationMessage = computed(() => this.headquarterService.operationMessage());

  public countries = computed(() => this.countryService.countries());
  public publishers = computed(() => this.publisherService.publishers());

  // FORMULÁRIO
  public headquarterForm = this.formBuilder.group({
    city: ['', Validators.required],
    state: [''],
    street: [''],
    number: [0, Validators.max(99999)],
    zipCode: [''],
    countryId: [0, Validators.required],
    publisherId: ['', Validators.required]
  })

  constructor() {
    effect(() => {
      const messages = this.operationMessage();
      if (messages.length > 0) {
        console.log(messages);
      }
      if (this.success()) {
        this.headquarterForm.reset();
      }
    });
  }

  onPageChange(newPagenumber: number) {
    this.headquarterService.setPageNumber(newPagenumber);
  }

  createHeadquarter() {
    this.headquarterService.createHeadquarter(this.headquarterForm.value as HeadquarterCreateBody);
  }
}
