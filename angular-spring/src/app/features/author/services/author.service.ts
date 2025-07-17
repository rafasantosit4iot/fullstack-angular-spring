import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  API_URL = "http://localhost:8080/authors"

  constructor(private http: HttpClient) { }

  public getAllAuthors() {
    return this.http.get(`${this.API_URL}`);
  }

  public createAuthor(newAuthorBody: any) {
    return this.http.post(`${this.API_URL}`, newAuthorBody);
  }

  public deleteAuthor(authorId: any) {
    return this.http.delete(`${this.API_URL}/${authorId}`);
  }
}
