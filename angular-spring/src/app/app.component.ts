import { AuthorService } from './features/author/services/author.service';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { CountryService } from './features/country/services/country.service';

@Component({
  selector: 'app-root',
  imports: [ReactiveFormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'angular-spring';

  countries: any[] = [];
  authors: any[] = [];

  private formBuilder = inject(FormBuilder);

  public authorForm = this.formBuilder.group({
    name: ['', Validators.required],
    birthday: ['', Validators.required],
    dayOfDeath: [''],
    countryId: ['', Validators.required]
  });

  public countryForm = this.formBuilder.group({
    name: ['', Validators.required],
    isoCode: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(3)]]
  })

  constructor(
    private authorService: AuthorService,
    private countryService: CountryService
  ) {

  }

  ngOnInit(): void {
    this.getAllCountries();
    this.getAllAuthors();
  }

  getAllCountries() {
    this.countryService.getAllCountries()
      .subscribe({
        next: (countries: any) => {
          console.log("Países:", countries);
          this.countries = countries;
        },
        error: (error) => {
          console.error(error);
        }
      })
  }

  submitCountry() {
    console.log("Valores enviados:", this.countryForm.value);
    this.countryService.createCountry(this.countryForm.value).subscribe({
      next: (response: any) => {
        console.log("Resposta: ", response);
        this.countries.push(response);
      },
      error: (error: HttpErrorResponse) => {
        console.error(error);
      }
    });
  }

  getAllAuthors() {
    this.authorService.getAllAuthors()
      .subscribe({
        next: (authors: any) => {
          console.log("Autores:", authors);
          this.authors = authors;
        },
        error: (error) => {
          console.error(error);
        }
      })
  }

  submitAuthor() {
    console.log("Valores enviados:", this.authorForm.value);
    this.authorService.createAuthor(this.authorForm.value).subscribe({
      next: (response: any) => {
        console.log("Resposta:", response);
        this.authors.push(response);
      },
      error: (error: HttpErrorResponse) => {
        console.error(error);
      }
    });
  }

  deleteAuthor(authorId: any) {
    this.authorService.deleteAuthor(authorId)
      .subscribe({
        next: (response: any) => {
          console.log(response);
        },
        error: (error: HttpErrorResponse) => {
          console.error(error);
        }
      })
  }
}
