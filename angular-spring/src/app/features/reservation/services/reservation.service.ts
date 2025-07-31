import { Injectable, signal } from '@angular/core';
import { BaseDataService } from '../../../shared/services/data/base-data.service';
import { ReservationCreateBody, ReservationPageResponse, ReservationResponseItem } from '../models/reservation';
import { environment } from '../../../../environments/environment.development';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ReservationService extends BaseDataService<ReservationResponseItem> {
  // ENVIROMENTS
  private API_URL = `${environment.libraryUrl.baseUrl}/reservations`;

  private _reservations = signal<ReservationResponseItem[]>([]);
  readonly reservations = this._reservations.asReadonly();

  protected override updateData(data: ReservationResponseItem): void {
    this._reservations.update(reservations => [...reservations, data]);
  }

  protected override deleteData(dataId: number | string): void {
    this._reservations.update(reservations => reservations.filter(reservations => reservations.id !== dataId));
  }

  public getReservations(): void {
    this.initNewOperation();
    const url = `${this.API_URL}${this._paginationParameters}`;
    this.http.get<ReservationPageResponse>(url, { observe: 'response' })
      .subscribe({
        next: (response: HttpResponse<ReservationPageResponse>) => {
          this.handleResponse<ReservationPageResponse>(response);
          this._reservations.set(this.responseBody().content);
          this.successOperation("Reservas recuperadas com sucesso");
        },
        error: (error: HttpErrorResponse) => this.errorOperation(error)
      })
  }

  public createReservation(newReservation: ReservationCreateBody): void {
    this.initNewOperation();
    this.http.post<ReservationResponseItem>(this.API_URL, newReservation, { observe: 'response' })
      .subscribe({
        next: (response: HttpResponse<ReservationResponseItem>) => {
          this.handleResponse<ReservationResponseItem>(response);
          this.updateData(this.responseBody());
          this.successOperation("Reserva criada com sucesso");
        },
        error: (error: HttpErrorResponse) => this.errorOperation(error)
      })
  }
}
