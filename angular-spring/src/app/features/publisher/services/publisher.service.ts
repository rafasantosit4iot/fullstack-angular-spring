import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PublisherService {
  API_URL = 'http://localhost:8080/publishers';

  constructor(private http: HttpClient) { }

  public getAllPublishers() {
    return this.http.get(this.API_URL);
  }

  public createPublisher(publisher: any): Observable<any> {
    return this.http.post(this.API_URL, publisher);
  }
}
