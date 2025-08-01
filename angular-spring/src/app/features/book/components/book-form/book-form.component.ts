import { Component, computed, effect, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthorService } from '../../../author/services/author.service';
import { PublisherService } from '../../../publisher/services/publisher.service';
import { BookService } from '../../services/book.service';
import { BookCreateBody } from '../../models/book';
import { GenreService } from '../../../genre/services/genre.service';

@Component({
  selector: 'app-book-form',
  imports: [ReactiveFormsModule],
  templateUrl: './book-form.component.html',
  styleUrl: './book-form.component.css'
})
export class BookFormComponent {
  // INJEÇÃO DE DEPENDÊNCIAS
  private formBuilder = inject(FormBuilder);
  private bookService = inject(BookService);
  private authorService = inject(AuthorService);
  private publisherService = inject(PublisherService);
  private genreService = inject(GenreService);

  // SIGNALS
  public loading = computed(() => this.bookService.loading());
  public success = computed(() => this.bookService.success());
  public error = computed(() => this.bookService.error());
  public operationMessage = computed(() => this.bookService.operationMessage());

  public authors = computed(() => this.authorService.authors());
  public publishers = computed(() => this.publisherService.publishers());
  public genres = computed(() => this.genreService.genres());

  // FORMULÁRIO
  public bookForm = this.formBuilder.group({
    title: ['', [Validators.required, Validators.maxLength(150)]],
    editionNumber: [0, [Validators.required, Validators.min(1)]],
    synopsis: ['', Validators.required],
    isbnCode: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(13)]],
    yearOfRelease: [0, Validators.required],
    authorId: ['', Validators.required],
    publisherId: ['', Validators.required],
    genreId: [0, Validators.required]
  });


  constructor() {
    effect(() => {
      const messages = this.operationMessage();
      if (messages.length > 0) {
        console.log(messages);
      }
      if (this.success()) {
        this.bookForm.reset();
      }
    });
  }

  ngOnInit(): void {
    this.authorService.getAllAuthors();
    this.publisherService.getAllPublishers();
    this.genreService.getGenres();
  }

  public onPageChange(newPagenumber: number) {
    this.bookService.setPageNumber(newPagenumber);
  }

  public createBook() {
    this.bookService.createBook(this.bookForm.value as BookCreateBody);
  }
}
