import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HeadquarterService {

  private API_URL = 'http://localhost:8080/headquarters';

  constructor(private http: HttpClient) { }

  public getAllHeadquarters() {
    return this.http.get(this.API_URL);
  }

  public createHeadquarter(headquarter: any) {
    return this.http.post(this.API_URL, headquarter);
  }
}
