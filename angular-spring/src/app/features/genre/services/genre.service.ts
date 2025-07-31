import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import { environment } from '../../../../environments/environment.development';
import { BaseDataService } from '../../../shared/services/data/base-data.service';
import { GenrePageResponse, GenreResponseItem } from '../models/genre';
import { GenreCreateBody } from './../models/genre';

@Injectable({
  providedIn: 'root'
})
export class GenreService extends BaseDataService<GenreResponseItem> {
  // ENVIRONMENTS
  private API_URL = `${environment.libraryUrl.baseUrl}/genres`;

  private _genres = signal<GenreResponseItem[]>([]);
  readonly genres = this._genres.asReadonly();

  protected override updateData(genre: GenreResponseItem): void {
    this._genres.update(genres => [...genres, genre]);
  }

  protected override deleteData(dataId: number | string): void {
    this._genres.update(genres => genres.filter(genre => genre.id !== dataId));
  }

  public getGenres(): void {
    this.initNewOperation();
    const url = `${this.API_URL}${this._paginationParameters}`;

    this.http.get<GenrePageResponse>(url, { observe: 'response' })
      .subscribe({
        next: (response: HttpResponse<GenrePageResponse>) => {
          this.handleResponse<GenrePageResponse>(response);
          this._genres.set(this._responseBody().content);
          this.successOperation("Gêneros literários recuperados com sucesso");
        },
        error: (error: HttpErrorResponse) => this.errorOperation(error)
      });
  }

  public createGenre(newGenre: GenreCreateBody): void {
    this.initNewOperation();

    this.http.post<GenreResponseItem>(this.API_URL, newGenre, { observe: 'response' })
      .subscribe({
        next: (response: HttpResponse<GenreResponseItem>) => {
          this.handleResponse<GenreResponseItem>(response);
          this.updateData(this._responseBody());
          this.successOperation("Gênero literário criado com sucesso");
        },
        error: (error: HttpErrorResponse) => this.errorOperation(error)
      })
  }
}
