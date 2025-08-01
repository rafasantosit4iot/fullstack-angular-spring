import { BookResponseItem } from './../../../../features/book/models/book';
import { Component, input } from '@angular/core';

@Component({
  selector: 'app-book-item',
  imports: [],
  templateUrl: './book-item.component.html',
  styleUrl: './book-item.component.css'
})
export class BookItemComponent {
  public book = input.required<BookResponseItem>();
}
