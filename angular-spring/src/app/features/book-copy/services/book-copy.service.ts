import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import { environment } from '../../../../environments/environment.development';
import { BaseDataService } from '../../../shared/services/data/base-data.service';
import { BookCopyCreateBody, BookCopyPageResponse, BookCopyResponseItem } from '../models/book-copy';

@Injectable({
  providedIn: 'root'
})
export class BookCopyService extends BaseDataService<BookCopyResponseItem> {
  // ENVIRONMENTS
  private API_URL = `${environment.libraryUrl.baseUrl}/book_copies`;

  private _book_copies = signal<BookCopyResponseItem[]>([]);
  readonly book_copies = this._book_copies.asReadonly();

  protected override updateData(data: BookCopyResponseItem): void {
    this._book_copies.update(copies => [...copies, data]);
  }

  protected override deleteData(dataId: number | string): void {
    this._book_copies.update(copies => copies.filter(copy => copy.id !== dataId));
  }

  public getBookCopies(): void {
    this.initNewOperation();
    const url = `${this.API_URL}${this._paginationParameters()}`;

    this.http.get<BookCopyPageResponse>(url, { observe: 'response' })
      .subscribe({
        next: (response: HttpResponse<BookCopyPageResponse>) => {
          this.handleResponse<BookCopyPageResponse>(response);
          this._book_copies.set(this.responseBody().content);
          this.successOperation("Cópias recuperadas com sucesso");
        },
        error: (error: HttpErrorResponse) => this.errorOperation(error)
      })
  }

  public createBookCopy(newBookCopy: BookCopyCreateBody): void {
    this.initNewOperation();
    this.http.post<BookCopyResponseItem>(this.API_URL, newBookCopy, { observe: 'response' })
      .subscribe({
        next: (response: HttpResponse<BookCopyResponseItem>) => {
          this.handleResponse<BookCopyResponseItem>(response);
          this.updateData(this.responseBody());
          this.successOperation("Cópia criada com sucesso");
        },
        error: (error: HttpErrorResponse) => this.errorOperation(error)
      })
  }
}
