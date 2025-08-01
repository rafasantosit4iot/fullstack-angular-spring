import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import { environment } from '../../../../environments/environment.development';
import { BaseDataService } from '../../../shared/services/data/base-data.service';
import { AuthorCreateBody, AuthorResponse as AuthorPageResponse, AuthorResponseItem } from '../models/author';

@Injectable({
  providedIn: 'root'
})
export class AuthorService extends BaseDataService<AuthorResponseItem> {
  // ENVIRONMENTS
  private API_URL = `${environment.libraryUrl.baseUrl}/authors`;

  private _authors = signal<AuthorResponseItem[]>([]);
  readonly authors = this._authors.asReadonly();

  protected override updateData(author: AuthorResponseItem): void {
    this._authors.update(authors => [...authors, author]);
  }

  protected override deleteData(dataId: number | string): void {
    this._authors.update(authors => authors.filter(author => author.id !== dataId));
  }

  public getAuthors(): void {
    this.initNewOperation();
    const url = `${this.API_URL}${this._paginationParameters()}`;

    this.http.get<AuthorPageResponse>(url, { observe: 'response' })
      .subscribe({
        next: (response: HttpResponse<AuthorPageResponse>) => {
          this.handleResponse<AuthorPageResponse>(response);
          this._authors.set(this._responseBody().content);
          this.successOperation("Autores recuperados com sucesso");
        },
        error: (error: HttpErrorResponse) => this.errorOperation(error)
      });
  }

  public getAllAuthors(): void {
    this.initNewOperation();
    const url = `${this.API_URL}/all`;

    this.http.get<AuthorResponseItem[]>(url, { observe: 'response' })
      .subscribe({
        next: (response: HttpResponse<AuthorResponseItem[]>) => {
          this.handleResponse<AuthorResponseItem[]>(response);
          this._authors.set(this.responseBody());
          this.successOperation("Autores recuperados com sucesso");
        },
        error: (error: HttpErrorResponse) => this.errorOperation(error)
      });
  }

  public createAuthor(newAuthor: AuthorCreateBody): void {
    this.initNewOperation();

    this.http.post<AuthorResponseItem>(this.API_URL, newAuthor, { observe: 'response' })
      .subscribe({
        next: (response: HttpResponse<AuthorResponseItem>) => {
          this.handleResponse<AuthorResponseItem>(response);
          this.updateData(this._responseBody());
          this.successOperation("Autor criado com sucesso");
        },
        error: (error: HttpErrorResponse) => this.errorOperation(error)
      })
  }
}
