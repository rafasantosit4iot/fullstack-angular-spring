import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http: HttpClient) { }

  public getAllBooks(): Observable<any> {
    return this.http.get<Observable<any>>("http://127.0.0.1:8080/books");
  }
}
