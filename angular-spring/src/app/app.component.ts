import { AuthorService } from './features/author/services/author.service';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { CountryService } from './features/country/services/country.service';
import { BookService } from './features/book/services/book.service';
import { PublisherService } from './features/publisher/services/publisher.service';
import { HeadquarterService } from './features/headquarter/services/headquarter.service';
import { GenreFormComponent } from "./features/genre/components/genre-form/genre-form.component";
import { CountryFormComponent } from "./features/country/components/country-form/country-form.component";
import { PublisherFormComponent } from "./features/publisher/components/publisher-form/publisher-form.component";
import { AuthorFormComponent } from './features/author/components/author-form/author-form.component';
import { BookFormComponent } from "./features/book/components/book-form/book-form.component";

@Component({
  selector: 'app-root',
  imports: [ReactiveFormsModule, GenreFormComponent, CountryFormComponent, PublisherFormComponent, AuthorFormComponent, BookFormComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'angular-spring';

  // countries: any[] = [];
  // authors: any;
  // books: any[] = [];
  // publishers: any[] = [];
  // headquarters: any[] = [];

  // private formBuilder = inject(FormBuilder);



  // public publisherForm = this.formBuilder.group({
  //   name: ['', [Validators.required, Validators.maxLength(100)]],
  //   founder: ['', [Validators.required, Validators.maxLength(100)]],
  //   foundationYear: ['', Validators.required]
  // })

  // public headquarterForm = this.formBuilder.group({
  //   city: ['', Validators.required],
  //   state: [''],
  //   countryId: [0, Validators.required],
  //   street: [''],
  //   number: [0, Validators.max(99999)],
  //   zipCode: [''],
  //   publisherId: ['', Validators.required]
  // })



  // constructor(
  //   private authorService: AuthorService,
  //   private countryService: CountryService,
  //   private bookService: BookService,
  //   private publisherService: PublisherService,
  //   private headquarterService: HeadquarterService
  // ) {

  // }

  // ngOnInit(): void {
  //   this.getAllCountries();
  //   this.getAllAuthors();
  //   this.getAllBooks();
  //   this.getAllPublishers();
  //   this.getAllHeadquarters();
  // }

  // getAllCountries() {
  //   this.countryService.getCountries()
  //     .subscribe({
  //       next: (countries: any) => {
  //         console.log("Países:", countries);
  //         this.countries = countries;
  //       },
  //       error: (error) => {
  //         console.error(error);
  //       }
  //     })
  // }

  // submitCountry() {
  //   console.log("Valores enviados:", this.countryForm.value);
  //   this.countryService.createCountry(this.countryForm.value).subscribe({
  //     next: (response: any) => {
  //       console.log("Resposta: ", response);
  //       this.countries.push(response);
  //       this.countryForm.reset();
  //     },
  //     error: (error: HttpErrorResponse) => {
  //       console.error(error);
  //     }
  //   });
  // }

  // getAllAuthors() {
  //   this.authorService.getAllAuthors()
  //     .subscribe({
  //       next: (authors: any) => {
  //         console.log("Autores:", authors);
  //         this.authors = authors;
  //       },
  //       error: (error) => {
  //         console.error(error);
  //       }
  //     })
  // }

  // submitAuthor() {
  //   console.log("Valores enviados:", this.authorForm.value);
  //   this.authorService.createAuthor(this.authorForm.value).subscribe({
  //     next: (response: any) => {
  //       console.log("Resposta:", response);
  //       this.authors.content.push(response);
  //       this.authorForm.reset();
  //     },
  //     error: (error: HttpErrorResponse) => {
  //       console.error(error);
  //     }
  //   });
  // }

  // deleteAuthor(authorId: any) {
  //   this.authorService.deleteAuthor(authorId)
  //     .subscribe({
  //       next: (response: any) => {
  //         console.log(response);
  //       },
  //       error: (error: HttpErrorResponse) => {
  //         console.error(error);
  //       }
  //     })
  // }

  // getAllBooks() {
  //   this.bookService.getAllBooks()
  //     .subscribe({
  //       next: (response: any) => {
  //         console.log("Livros: ", response);
  //         this.books = response;
  //       },
  //       error: (error: HttpErrorResponse) => {
  //         console.error(error);
  //       }
  //     })
  // }

  // submitBook() {
  //   this.bookService.createBook(this.bookForm.value).subscribe({
  //     next: (response: any) => {
  //       console.log("Resposta: ", response);
  //       this.books.push(response);
  //       this.bookForm.reset();
  //     },
  //     error: (error: HttpErrorResponse) => {
  //       console.error(error);
  //     }
  //   })
  // }

  // getAllPublishers() {
  //   this.publisherService.getAllPublishers()
  //     .subscribe({
  //       next: (response: any) => {
  //         console.log("Editoras: ", response);
  //         this.publishers = response;
  //       },
  //       error: (error: HttpErrorResponse) => {
  //         console.error(error);
  //       }
  //     })
  // }

  // submitPublisher() {
  //   this.publisherService.createPublisher(this.publisherForm.value)
  //     .subscribe({
  //       next: (response: any) => {
  //         console.log(response);
  //         this.publishers.push(response);
  //         this.publisherForm.reset();
  //       },
  //       error: (error: HttpErrorResponse) => {
  //         console.error(error);
  //       }
  //     })
  // }

  // deletePublisher(publisherId: any) {
  //   this.publisherService.deletePublisher(publisherId)
  //     .subscribe({
  //       next: (response: any) => {
  //         console.log(response);
  //       },
  //       error: (error: HttpErrorResponse) => {
  //         console.error(error);
  //       }
  //     })
  // }

  // getAllHeadquarters() {
  //   this.headquarterService.getAllHeadquarters()
  //     .subscribe({
  //       next: (response: any) => {
  //         console.log("Sedes:", response);
  //         this.headquarters = response;
  //       },
  //       error: (error: HttpErrorResponse) => {
  //         console.error(error);
  //       }
  //     });
  // }

  // submitHeadquarter() {
  //   this.headquarterService.createHeadquarter(this.headquarterForm.value)
  //     .subscribe({
  //       next: (response: any) => {
  //         console.log(response);
  //         this.headquarters.push(response);
  //         this.headquarterForm.reset();
  //       },
  //       error: (error: HttpErrorResponse) => {
  //         console.error(error);
  //       }
  //     })
  // }
}
