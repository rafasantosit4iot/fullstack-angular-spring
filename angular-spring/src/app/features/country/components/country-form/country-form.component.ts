import { Component, computed, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { CountryService } from '../../services/country.service';
import { CountryCreateBody } from '../../models/country';

@Component({
  selector: 'app-country-form',
  imports: [ReactiveFormsModule],
  templateUrl: './country-form.component.html',
  styleUrl: './country-form.component.css'
})
export class CountryFormComponent {
  // INJEÇÃO DE DEPENDÊNCIAS
  private formBuilder = inject(FormBuilder);
  private countryService = inject(CountryService);

  // SIGNALS
  public loading = computed(() => this.countryService.loading());
  public error = computed(() => this.countryService.error());

  public countries = computed(() => this.countryService.countries());
  public pageNumber = computed(() => this.countryService.pageNumber());

  // FORMULÁRIO
  public countryForm = this.formBuilder.group({
    name: ['', Validators.required],
    isoCode: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(3)]]
  });

  onPageChange(newPagenumber: number) {
    this.countryService.setPageNumber(newPagenumber);
  }

  createCountry() {
    this.countryService.createCountry(this.countryForm.value as CountryCreateBody);
  }
}
