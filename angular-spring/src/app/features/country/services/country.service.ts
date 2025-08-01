import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import { environment } from '../../../../environments/environment.development';
import { BaseDataService } from '../../../shared/services/data/base-data.service';
import { CountryCreateBody, CountryPageResponse, CountryResponseItem } from '../models/country';

@Injectable({
  providedIn: 'root'
})
export class CountryService extends BaseDataService<CountryResponseItem> {
  // ENVIRONMENTS
  private API_URL: string = `${environment.libraryUrl.baseUrl}/countries`;

  private _countries = signal<CountryResponseItem[]>([]);
  readonly countries = this._countries.asReadonly();

  protected override updateData(country: CountryResponseItem): void {
    this._countries.update(countries => [...countries, country]);
  }

  protected override deleteData(dataId: number | string): void {
    this._countries.update(countries => countries.filter(country => country.id !== dataId));
  }

  public getCountries(): void {
    this.initNewOperation();
    const url = `${this.API_URL}${this._paginationParameters()}`;

    this.http.get<CountryPageResponse>(url, { observe: 'response' })
      .subscribe({
        next: (response: HttpResponse<CountryPageResponse>) => {
          this.handleResponse<CountryPageResponse>(response);
          this._countries.set(this._responseBody().content);
          this.successOperation("Países recuperados com sucesso");
        },
        error: (error: HttpErrorResponse) => this.errorOperation(error)
      });
  }

  public getAllCountries(): void {
    this.initNewOperation();
    const url = `${this.API_URL}/all`;

    this.http.get<CountryResponseItem[]>(url, { observe: 'response' })
      .subscribe({
        next: (response: HttpResponse<CountryResponseItem[]>) => {
          this.handleResponse<CountryResponseItem[]>(response);
          this._countries.set(this._responseBody());
          this.successOperation("Países recuperados com sucesso");
        },
        error: (error: HttpErrorResponse) => this.errorOperation(error)
      })
  }

  public createCountry(newCountry: CountryCreateBody): void {
    this.initNewOperation();

    this.http.post<CountryResponseItem>(this.API_URL, newCountry, { observe: 'response' })
      .subscribe({
        next: (response: HttpResponse<CountryResponseItem>) => {
          this.handleResponse<CountryResponseItem>(response);
          this.updateData(this._responseBody());
          this.successOperation("País criado com sucesso");
        },
        error: (error: HttpErrorResponse) => this.errorOperation(error)
      })
  }
}
