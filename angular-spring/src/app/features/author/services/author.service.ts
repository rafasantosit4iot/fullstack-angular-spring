import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { effect, inject, Injectable, signal } from '@angular/core';
import { environment } from '../../../../environments/environment.development';
import { AuthorCreateBody, AuthorResponse, AuthorResponseItem } from '../models/author';

@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  // ENVIRONMENTS
  private API_URL = `${environment.libraryUrl.baseUrl}/authors`;

  // DEPENDÊNCIAS
  private http = inject(HttpClient);

  // GÊNEROS COMPLETOS
  private _authors = signal<AuthorResponseItem[]>([]);
  readonly authors = this._authors.asReadonly();

  // PAGINAÇÃO
  private _pageNumber = signal<number>(0);
  readonly pageNumber = this._pageNumber.asReadonly();

  private _pageSize = signal<number>(12);
  readonly pageSize = this._pageSize.asReadonly();

  // LOADING
  private _loading = signal<boolean>(false);
  readonly loading = this._loading.asReadonly();

  // SUCCESS
  private _success = signal<boolean>(false);
  readonly success = this._success.asReadonly();

  // ERROR
  private _error = signal<string | null>(null);
  readonly error = this._error.asReadonly();

  public setPageNumber(newPageNumber: number) {
    this._pageNumber.set(newPageNumber);
  }

  public getAuthors(): void {
    this._loading.set(true);
    this._error.set(null);

    const pageNumber = this._pageNumber();
    const pageSize = this._pageSize();

    this.http.get<AuthorResponse>(`${this.API_URL}?pageNumber=${pageNumber}&pageSize=${pageSize}`)
      .subscribe({
        next: (getResponse: AuthorResponse) => {
          const authorsResult: AuthorResponseItem[] = getResponse.content;
          this._authors.set(authorsResult);
          this._loading.set(false);
          console.log(authorsResult);
        },
        error: (error: HttpErrorResponse) => {
          console.error("Erro ao carregar os autores", error);
          this._error.set("Erro ao carregar os autores: " + error.message);
        }
      });
  }

  public createAuthor(newAuthor: AuthorCreateBody): void {
    this._loading.set(true);
    this._error.set(null);

    this.http.post<AuthorResponseItem>(this.API_URL, newAuthor)
      .subscribe({
        next: (createResponse: AuthorResponseItem) => {
          this._authors.update((authors: AuthorResponseItem[]) => [...authors, createResponse]);
          this._loading.set(false);
          this._success.set(true);
          console.log("Criado", createResponse);
        },
        error: (error: HttpErrorResponse) => {
          console.error("Erro ao criar autor", error);
          this._error.set("Erro ao criar autor: " + error.message);
        }
      })
  }
}
