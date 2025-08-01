import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import { environment } from '../../../../environments/environment.development';
import { BaseDataService } from '../../../shared/services/data/base-data.service';
import { BookCreateBody, BookPageResponse, BookResponseItem } from '../models/book';

@Injectable({
  providedIn: 'root'
})
export class BookService extends BaseDataService<BookResponseItem> {
  // ENVIRONMENTS
  private API_URL = `${environment.libraryUrl.baseUrl}/books`;

  private _books = signal<BookResponseItem[]>([]);
  readonly books = this._books.asReadonly();

  private _mainBooks = signal<BookResponseItem[]>([]);
  readonly mainBooks = this._mainBooks.asReadonly();

  protected override updateData(data: BookResponseItem): void {
    this._books.update(books => [...books, data]);
  }

  protected override deleteData(dataId: number | string): void {
    this._books.update(books => books.filter(book => book.id !== dataId));
  }

  public getBooks(): void {
    this.initNewOperation();
    const url = `${this.API_URL}${this._paginationParameters()}`;

    this.http.get<BookPageResponse>(url, { observe: 'response' })
      .subscribe({
        next: (response: HttpResponse<BookPageResponse>) => {
          this.handleResponse<BookPageResponse>(response);
          this._books.set(this.responseBody().content);
          this.successOperation("Livros recuperados com sucesso");
        },
        error: (error: HttpErrorResponse) => this.errorOperation(error)
      });
  }

  public getMainBooks(pageNumber: number = 0, pageSize: number = 4): void {
    this.initNewOperation();
    const params = `?pageNumber=${pageNumber}&pageSize=${pageSize}`;
    const url = `${this.API_URL}${params}`;

    this.http.get<BookPageResponse>(url, { observe: 'response' })
      .subscribe({
        next: (response: HttpResponse<BookPageResponse>) => {
          this.handleResponse<BookPageResponse>(response);
          this._mainBooks.set(this.responseBody().content);
          this.successOperation("Livros em destaque recuperados com sucesso");
        },
        error: (error: HttpErrorResponse) => this.errorOperation(error)
      });
  }

  public getAllBooks(): void {
    this.initNewOperation();
    const url = `${this.API_URL}/all`;

    this.http.get<BookResponseItem[]>(url, { observe: 'response' })
      .subscribe({
        next: (response: HttpResponse<BookResponseItem[]>) => {
          this.handleResponse<BookResponseItem[]>(response);
          this._books.set(this.responseBody());
          this.successOperation("Livros recuperados com sucesso");
        },
        error: (error: HttpErrorResponse) => this.errorOperation(error)
      });
  }

  public createBook(newBook: BookCreateBody): void {
    this.initNewOperation();
    this.http.post<BookResponseItem>(this.API_URL, newBook, { observe: 'response' })
      .subscribe({
        next: (response: HttpResponse<BookResponseItem>) => {
          this.handleResponse<BookResponseItem>(response);
          this.updateData(this.responseBody());
          this.successOperation("Livro criado com sucesso");
        },
        error: (error: HttpErrorResponse) => this.errorOperation(error)
      });
  }
}
