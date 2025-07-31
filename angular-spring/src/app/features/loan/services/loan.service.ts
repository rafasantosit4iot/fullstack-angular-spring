import { Injectable, signal } from '@angular/core';
import { BaseDataService } from '../../../shared/services/data/base-data.service';
import { LoanCreateBody, LoanPageResponse, LoanResponseItem } from '../models/loan';
import { environment } from '../../../../environments/environment.development';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoanService extends BaseDataService<LoanResponseItem> {
  // ENVIRONMENTS
  private API_URL = `${environment.libraryUrl.baseUrl}/loans`;

  private _loans = signal<LoanResponseItem[]>([]);
  readonly loans = this._loans.asReadonly();

  protected override updateData(data: LoanResponseItem): void {
    this._loans.update(loans => [...loans, data]);
  }

  protected override deleteData(dataId: number | string): void {
    this._loans.update(loans => loans.filter(loan => loan.id !== dataId));
  }

  public getLoans(): void {
    this.initNewOperation();
    const url = `${this.API_URL}${this._paginationParameters}`;

    this.http.get<LoanPageResponse>(url, { observe: 'response' })
      .subscribe({
        next: (response: HttpResponse<LoanPageResponse>) => {
          this.handleResponse<LoanPageResponse>(response);
          this._loans.set(this.responseBody().content);
          this.successOperation("Empréstimos recuperados com sucesso");
        },
        error: (error: HttpErrorResponse) => this.errorOperation(error)
      })
  }

  public createLoan(newLoan: LoanCreateBody): void {
    this.initNewOperation();
    this.http.post<LoanResponseItem>(this.API_URL, newLoan, { observe: 'response' })
      .subscribe({
        next: (response: HttpResponse<LoanResponseItem>) => {
          this.handleResponse<LoanResponseItem>(response);
          this.updateData(this.responseBody());
          this.successOperation("Empréstimo criado com sucesso");
        },
        error: (error: HttpErrorResponse) => this.errorOperation(error)
      })
  }
}
