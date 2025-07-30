import { Component, computed, effect, inject, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthorService } from '../../services/author.service';
import { AuthorCreateBody } from '../../models/author';
import { CountryService } from '../../../country/services/country.service';

@Component({
  selector: 'app-author-form',
  imports: [ReactiveFormsModule],
  templateUrl: './author-form.component.html',
  styleUrl: './author-form.component.css'
})
export class AuthorFormComponent implements OnInit {
  // INJEÇÃO DE DEPENDÊNCIAS
  private formBuilder = inject(FormBuilder);
  private authorService = inject(AuthorService);
  private countryService = inject(CountryService);

  // SIGNALS
  public loading = computed(() => this.authorService.loading());
  public success = computed(() => this.authorService.success());
  public error = computed(() => this.authorService.error());

  public authors = computed(() => this.authorService.authors());
  public countries = computed(() => this.countryService.countries());

  public pageNumber = computed(() => this.authorService.pageNumber());

  // FORMULÁRIO
  public authorForm = this.formBuilder.group({
    name: ['', Validators.required],
    birthday: ['', Validators.required],
    dayOfDeath: [''],
    countryId: [0, Validators.required]
  });

  constructor() {
    effect(() => {
      if (this.success()) {
        this.authorForm.reset();
      }
    });
  }

  ngOnInit(): void {
    this.countryService.getAllCountries();
  }

  onPageChange(newPagenumber: number) {
    this.authorService.setPageNumber(newPagenumber);
  }

  createAuthor() {
    this.authorService.createAuthor(this.authorForm.value as AuthorCreateBody);
  }
}
