import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import { environment } from '../../../../environments/environment.development';
import { BaseDataService } from '../../../shared/services/data/base-data.service';
import { PublisherCreateBody, PublisherPageResponse, PublisherResponseItem } from '../models/publisher';

@Injectable({
  providedIn: 'root'
})
export class PublisherService extends BaseDataService<PublisherResponseItem> {
  // ENVIRONMENTS
  private API_URL = `${environment.libraryUrl.baseUrl}/publishers`;

  private _publishers = signal<PublisherResponseItem[]>([]);
  readonly publishers = this._publishers.asReadonly();

  protected override updateData(publisher: PublisherResponseItem): void {
    this._publishers.update(publishers => [...publishers, publisher]);
  }

  protected override deleteData(dataId: number | string): void {
    this._publishers.update(publishers => publishers.filter(publisher => publisher.id !== dataId));
  }

  public getPublishers(): void {
    this.initNewOperation();
    const url = `${this.API_URL}${this._paginationParameters()}`;

    this.http.get<PublisherPageResponse>(url, { observe: 'response' })
      .subscribe({
        next: (response: HttpResponse<PublisherPageResponse>) => {
          this.handleResponse<PublisherPageResponse>(response);
          this._publishers.set(this._responseBody().content);
          this.successOperation("Editoras recuperadas com sucesso");
        },
        error: (error: HttpErrorResponse) => this.errorOperation(error)
      });
  }

  public getAllPublishers(): void {
    this.initNewOperation();
    const url = `${this.API_URL}/all`;

    this.http.get<PublisherResponseItem[]>(url, { observe: 'response' })
      .subscribe({
        next: (response: HttpResponse<PublisherResponseItem[]>) => {
          this.handleResponse<PublisherResponseItem[]>(response);
          this._publishers.set(this.responseBody());
          this.successOperation("Editoras recuperados com sucesso");
        },
        error: (error: HttpErrorResponse) => this.errorOperation(error)
      });
  }

  public createPublisher(newPublisher: PublisherCreateBody): void {
    this.initNewOperation();
    this.http.post<PublisherResponseItem>(this.API_URL, newPublisher, { observe: 'response' })
      .subscribe({
        next: (response: HttpResponse<PublisherResponseItem>) => {
          this.handleResponse<PublisherResponseItem>(response);
          this.updateData(this._responseBody());
          this.successOperation("Editora criada com sucesso");
        },
        error: (error: HttpErrorResponse) => this.errorOperation(error)
      })
  }

  public deletePublisher(publisherId: string): void {
    this.initNewOperation();
    this.http.delete<null>(`${this.API_URL}/${publisherId}`, { observe: 'response' })
      .subscribe({
        next: (response: HttpResponse<null>) => {
          this.handleResponse<null>(response);
          this.deleteData(publisherId);
          this.successOperation("Editora removida com sucesso");
        },
        error: (error: HttpErrorResponse) => this.errorOperation(error)
      });
  }
}
