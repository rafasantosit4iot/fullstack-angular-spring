import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import { environment } from '../../../../environments/environment.development';
import { BaseDataService } from '../../../shared/services/data/base-data.service';
import { HeadquarterCreateBody, HeadquarterPageResponse, HeadquarterResponseItem } from './../models/headquarter';

@Injectable({
  providedIn: 'root'
})
export class HeadquarterService extends BaseDataService<HeadquarterResponseItem> {
  // ENVIRONMENTS
  private API_URL = `${environment.libraryUrl.baseUrl}/headquarters`;

  private _headquarters = signal<HeadquarterResponseItem[]>([]);
  readonly headquarters = this._headquarters.asReadonly();

  protected override updateData(data: HeadquarterResponseItem): void {
    this._headquarters.update(headquarters => [...headquarters, data]);
  }

  protected override deleteData(dataId: number | string): void {
    this._headquarters.update(headquarters => headquarters.filter(headquarter => headquarter.id !== dataId));
  }

  public getHeadquarters(): void {
    this.initNewOperation();
    const url = `${this.API_URL}${this._paginationParameters()}`;

    this.http.get<HeadquarterPageResponse>(url, { observe: 'response' })
      .subscribe({
        next: (response: HttpResponse<HeadquarterPageResponse>) => {
          this.handleResponse<HeadquarterPageResponse>(response);
          this._headquarters.set(this.responseBody().content);
          this.successOperation("Sedes recuperadas com sucesso");
        },
        error: (error: HttpErrorResponse) => this.errorOperation(error)
      });
  }

  public createHeadquarter(newHeadquarter: HeadquarterCreateBody): void {
    this.initNewOperation();
    this.http.post<HeadquarterResponseItem>(this.API_URL, newHeadquarter, { observe: 'response' })
      .subscribe({
        next: (response: HttpResponse<HeadquarterResponseItem>) => {
          this.handleResponse<HeadquarterResponseItem>(response);
          this.updateData(this.responseBody());
          this.successOperation("Sede criada com sucesso");
        },
        error: (error: HttpErrorResponse) => this.errorOperation(error)
      });
  }
}
