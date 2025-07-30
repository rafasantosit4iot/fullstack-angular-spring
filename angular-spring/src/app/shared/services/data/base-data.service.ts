import { inject, Injectable, signal } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ErrorResponseHandlerService } from '../error/error-response-handler.service';
import { InterfaceMessage } from '../../models/response';

@Injectable({
  providedIn: 'root'
})
export abstract class BaseDataService<T> {
  // DEPENDÊNCIAS COMUNS
  protected http = inject(HttpClient);
  protected responseErrorHandler = inject(ErrorResponseHandlerService);

  // SIGNALS COMUNS  
  protected _responseBody = signal<any>(null);
  public responseBody = this._responseBody.asReadonly();

  protected _responseStatusCode = signal<number>(0);

  protected _loading = signal<boolean>(false);
  public loading = this._loading.asReadonly();

  protected _success = signal<boolean>(false);
  public success = this._success.asReadonly();

  protected _error = signal<boolean>(false);
  public error = this._error.asReadonly();

  protected _operationMessage = signal<InterfaceMessage[]>([]);
  public operationMessage = this._operationMessage.asReadonly();

  // PAGINAÇÃO (opcional)
  protected _pageNumber = signal<number>(0);
  public pageNumber = this._pageNumber.asReadonly();

  protected _pageSize = signal<number>(12);
  public pageSize = this._pageSize.asReadonly();

  constructor() { }

  // MÉTODOS COMUNS
  protected initNewOperation() {
    this._loading.set(true);
    this._error.set(false);
    this._success.set(false);
    this._operationMessage.set([]);
  }

  protected successOperation(message: string) {
    this._loading.set(false);
    this._success.set(true);
    const newMessage: InterfaceMessage = {
      message: message,
      status: this._responseStatusCode(),
      ok: true
    };
    this._operationMessage.update((messages: InterfaceMessage[]) => [...messages, newMessage]);
  }

  protected errorOperation(error: HttpErrorResponse) {
    this._loading.set(false);
    this._error.set(true);
    this._operationMessage.set(this.responseErrorHandler.handleError(error));
  }

  protected handleResponse<R>(response: HttpResponse<any>): void {
    this._responseStatusCode.set(response.status);
    this._responseBody.set(response.body);
  }

  protected getResponseData<R>(): R | null {
    return this._responseBody() as R;
  }

  public setPageNumber(newPageNumber: number): void {
    this._pageNumber.set(newPageNumber);
  }

  protected abstract updateData(data: T): void;

  protected abstract deleteData(dataId: number | string): void;
}
