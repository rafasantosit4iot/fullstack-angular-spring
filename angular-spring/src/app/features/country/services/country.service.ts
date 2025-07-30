import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { effect, inject, Injectable, signal } from '@angular/core';
import { environment } from '../../../../environments/environment.development';
import { CountryCreateBody, CountryPageResponse, CountryResponseItem } from '../models/country';

@Injectable({
  providedIn: 'root'
})
export class CountryService {
  // ENVIRONMENTS
  private API_URL: string = `${environment.libraryUrl.baseUrl}/countries`;

  // DEPENDÊNCIAS
  private http = inject(HttpClient);

  // PAÍSES COMPLETOS
  private _countries = signal<CountryResponseItem[]>([]);
  readonly countries = this._countries.asReadonly();

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

  public setPageNumber(newPageNumber: number): void {
    this._pageNumber.set(newPageNumber);
  }

  public getCountries(): void {
    this._loading.set(true);
    this._error.set(null);

    const pageNumber = this._pageNumber();
    const pageSize = this._pageSize();

    this.http.get<CountryPageResponse>(`${this.API_URL}?pageNumber=${pageNumber}&pageSize=${pageSize}`)
      .subscribe({
        next: (getResponse: CountryPageResponse) => {
          const countriesResult: CountryResponseItem[] = getResponse.content;
          this._countries.set(countriesResult);
          this._loading.set(false);
          console.log(countriesResult);
        },
        error: (error: HttpErrorResponse) => {
          console.error("Erro ao carregar os países", error);
          this._error.set("Erro ao carregar os países: " + error.message);
        }
      });
  }

  public getAllCountries(): void {
    this._loading.set(true);
    this._error.set(null);

    this.http.get<CountryResponseItem[]>(`${this.API_URL}/all`)
      .subscribe({
        next: (getAllResponse: CountryResponseItem[]) => {
          this._countries.set(getAllResponse);
          this._loading.set(false);
          console.log(getAllResponse);
        },
        error: (error: HttpErrorResponse) => {
          console.error("Erro ao carregar os países", error);
          this._error.set("Erro ao carregar os países: " + error.message);
        }
      })
  }

  public createCountry(newCountry: CountryCreateBody): void {
    this._loading.set(true);
    this._error.set(null);

    this.http.post<CountryResponseItem>(this.API_URL, newCountry)
      .subscribe({
        next: (createResponse: CountryResponseItem) => {
          this._countries.update((countries: CountryResponseItem[]) => [...countries, createResponse]);
          this._loading.set(false);
          console.log("Criado", createResponse);
        },
        error: (error: HttpErrorResponse) => {
          console.error("Erro ao criar país", error);
          this._error.set("Erro ao criar país: " + error.message);
        }
      })
  }
}
