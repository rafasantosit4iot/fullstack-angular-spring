import { HttpErrorResponse } from '@angular/common/http';
import { effect, Injectable, signal } from '@angular/core';
import { CustomErrorMessage} from '../../models/error/error-message';
import { InterfaceMessage } from '../../models/response';

@Injectable({
  providedIn: 'root'
})
export class ErrorResponseHandlerService {

  private _errors = signal<InterfaceMessage[]>([]);
  public errors = this._errors.asReadonly();

  public handleError(error: HttpErrorResponse): InterfaceMessage[] {
    this._errors.set([]);

    const errorMessages = this.extractErrorMessage(error);
    this._errors.set(errorMessages);

    return errorMessages;
  }

  private extractErrorMessage(error: HttpErrorResponse): InterfaceMessage[] {
    const requestErrorMessage = error.error;

    if (typeof requestErrorMessage === 'object') {
      return requestErrorMessage.errors.map((customError: CustomErrorMessage) => {
        let message: InterfaceMessage = {
          message: customError.defaultMessage,
          status: error.status,
          ok: false
        };
        return message;
      });
    }

    if (typeof requestErrorMessage === 'string') {
      const message: InterfaceMessage = {
        message: requestErrorMessage,
        status: error.status,
        ok: false
      };
      return [message];
    }
    return [{
      message: error.message || 'Erro na requisição',
      status: error.status,
      ok: false
    }];
  }
}
