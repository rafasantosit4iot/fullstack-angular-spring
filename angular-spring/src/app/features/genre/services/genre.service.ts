import { GenreCreateBody } from './../models/genre';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { effect, inject, Injectable, signal } from '@angular/core';
import { environment } from '../../../../environments/environment.development';
import { GenreResponse, GenreResponseItem } from '../models/genre';

@Injectable({
  providedIn: 'root'
})
export class GenreService {
  // ENVIRONMENTS
  private API_URL = `${environment.libraryUrl.baseUrl}/genres`;

  // DEPENDÊNCIAS
  private http = inject(HttpClient);

  // GÊNEROS COMPLETOS
  private _genres = signal<GenreResponseItem[]>([]);
  readonly genres = this._genres.asReadonly();

  // PAGINAÇÃO
  private _pageNumber = signal<number>(0);
  readonly pageNumber = this._pageNumber.asReadonly();

  private _pageSize = signal<number>(12);
  readonly pageSize = this._pageSize.asReadonly();

  // LOADING
  private _loading = signal(false);
  readonly loading = this._loading.asReadonly();

  // ERROR
  private _error = signal<string | null>(null);
  readonly error = this._error.asReadonly();

  constructor() {
    effect(() => {
      this.getGenres();
    })
  }

  public setPageNumber(newPageNumber: number) {
    this._pageNumber.set(newPageNumber);
  }

  public getGenres(): void {
    this._loading.set(true);
    this._error.set(null);

    const pageNumber = this._pageNumber();
    const pageSize = this._pageSize();

    this.http.get<GenreResponse>(`${this.API_URL}?pageNumber=${pageNumber}&pageSize=${pageSize}`)
      .subscribe({
        next: (apiResponse: GenreResponse) => {
          const genresResult: GenreResponseItem[] = apiResponse.content;
          this._genres.set(genresResult);
          this._loading.set(false);
          console.log(genresResult);
        },
        error: (error: HttpErrorResponse) => {
          console.error("Erro ao carregar os gêneros", error);
          this._error.set("Erro ao carregar os gêneros: " + error.message);
        }
      });
  }

  public createGenre(newGenre: GenreCreateBody): void {
    this._loading.set(true);
    this._error.set(null);

    this.http.post<GenreResponseItem>(this.API_URL, newGenre)
      .subscribe({
        next: (newGenre: GenreResponseItem) => {
          this._genres.update((genres: GenreResponseItem[]) => [...genres, newGenre]);
          this._loading.set(false);
          console.log("Criado", newGenre);
        },
        error: (error: HttpErrorResponse) => {
          console.error("Erro ao criar gênero", error);
          this._error.set("Erro ao criar gênero: " + error.message);
        }
      })
  }
}
