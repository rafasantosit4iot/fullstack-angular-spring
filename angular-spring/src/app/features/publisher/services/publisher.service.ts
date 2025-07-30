import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { effect, inject, Injectable, signal } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment.development';
import { PublisherCreateBody, PublisherResponse, PublisherResponseItem } from '../models/publisher';

@Injectable({
  providedIn: 'root'
})
export class PublisherService {
  // ENVIRONMENTS
  private API_URL = `${environment.libraryUrl.baseUrl}/publishers`;

  // DEPENDÊNCIAS
  private http = inject(HttpClient);

  // GÊNEROS COMPLETOS
  private _publishers = signal<PublisherResponseItem[]>([]);
  readonly publishers = this._publishers.asReadonly();

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

  public setPageNumber(newPageNumber: number) {
    this._pageNumber.set(newPageNumber);
  }

  public getPublishers(): void {
    this._loading.set(true);
    this._error.set(null);

    const pageNumber = this._pageNumber();
    const pageSize = this._pageSize();

    this.http.get<PublisherResponse>(`${this.API_URL}?pageNumber=${pageNumber}&pageSize=${pageSize}`)
      .subscribe({
        next: (getResponse: PublisherResponse) => {
          const publishersResult: PublisherResponseItem[] = getResponse.content;
          this._publishers.set(publishersResult);
          this._loading.set(false);
          console.log(publishersResult);
        },
        error: (error: HttpErrorResponse) => {
          console.error("Erro ao carregar as editoras", error);
          this._error.set("Erro ao carregar as editoras: " + error.message);
        }
      });
  }

  public createPublisher(newPublisher: PublisherCreateBody): void {
    this._loading.set(true);
    this._error.set(null);

    this.http.post<PublisherResponseItem>(this.API_URL, newPublisher)
      .subscribe({
        next: (createResponse: PublisherResponseItem) => {
          this._publishers.update((publishers: PublisherResponseItem[]) => [...publishers, createResponse]);
          this._loading.set(false);
          console.log("Criado", createResponse);
        },
        error: (error: HttpErrorResponse) => {
          console.error("Erro ao criar editora", error);
          this._error.set("Erro ao criar editora: " + error.message);
        }
      })
  }

  public deletePublisher(publisherId: string): void {
    this._loading.set(true);
    this._error.set(null);

    this.http.delete<null>(`${this.API_URL}/${publisherId}`)
      .subscribe({
        next: (deleteResponse: null) => {
          this._loading.set(false);
          this._publishers.update((publishers: PublisherResponseItem[]) => publishers.filter((publisher: PublisherResponseItem) => {
            publisher.id !== publisherId;
          }));
        },
        error: (error: HttpErrorResponse) => {
          console.error("Erro ao excluir editora", error);
          this._error.set("Erro ao excluir editora: " + error.message);
        }
      });
  }
}
