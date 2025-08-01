import { Injectable, signal } from '@angular/core';
import { BaseDataService } from '../../../shared/services/data/base-data.service';
import { UserCreateBody, UserPageResponse, UserResponseItem } from '../models/user';
import { environment } from '../../../../environments/environment.development';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService extends BaseDataService<UserResponseItem> {
  // ENVIROMENTS
  private API_URL = `${environment.libraryUrl.baseUrl}/users`;

  private _users = signal<UserResponseItem[]>([]);
  readonly users = this._users.asReadonly();

  protected override updateData(data: UserResponseItem): void {
    this._users.update(users => [...users, data]);
  }

  protected override deleteData(dataId: number | string): void {
    this._users.update(users => users.filter(user => user.id !== dataId));
  }

  public getUsers(): void {
    this.initNewOperation();
    const url = `${this.API_URL}${this._paginationParameters()}`;
    this.http.get<UserPageResponse>(url, { observe: 'response' })
      .subscribe({
        next: (response: HttpResponse<UserPageResponse>) => {
          this.handleResponse<UserPageResponse>(response);
          this._users.set(this.responseBody().content);
          this.successOperation("Usuários recuperados com sucesso");
        },
        error: (error: HttpErrorResponse) => this.errorOperation(error)
      })
  }

  public createUser(newUser: UserCreateBody): void {
    this.initNewOperation();
    this.http.post<UserResponseItem>(this.API_URL, newUser, { observe: 'response' })
      .subscribe({
        next: (response: HttpResponse<UserResponseItem>) => {
          this.handleResponse<UserResponseItem>(response);
          this.updateData(this.responseBody());
          this.successOperation("Usuário criado com sucesso");
        },
        error: (error: HttpErrorResponse) => this.errorOperation(error)
      })
  }

}
