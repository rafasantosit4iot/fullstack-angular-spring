import { AuthorService } from './features/author/services/author.service';
import { Component, inject, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-root',
  imports: [ReactiveFormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'angular-spring';

  authors: any[] = [];

  private formBuilder = inject(FormBuilder);
  public authorForm = this.formBuilder.group({
    name: ['', Validators.required],
    birthday: ['', Validators.required]
  });

  constructor(
    private authorService: AuthorService
  ) {

  }

  ngOnInit(): void {
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
    console.log(this.authorForm.value);
    this.authorService.createAuthor(this.authorForm.value).subscribe({
      next: (response: any) => {
        console.log(response);
      },
      error: (error: HttpErrorResponse) => {
        console.error(error.error.errors);
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
