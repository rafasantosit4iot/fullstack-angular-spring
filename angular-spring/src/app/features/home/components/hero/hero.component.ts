import { Component, computed, inject, OnInit } from '@angular/core';
import { BookService } from '../../../book/services/book.service';
import { BookItemComponent } from "../../../../shared/components/book/book-item/book-item.component";

@Component({
  selector: 'app-hero',
  imports: [BookItemComponent],
  templateUrl: './hero.component.html',
  styleUrl: './hero.component.css'
})
export class HeroComponent implements OnInit {

  private bookService = inject(BookService);

  public success = computed(() => this.bookService.success());

  public mainBooks = computed(() => this.bookService.mainBooks());
  public books = computed(() => this.bookService.books());

  constructor() { }

  ngOnInit(): void {
    this.bookService.getBooks();
    this.bookService.getMainBooks();
  }
}
