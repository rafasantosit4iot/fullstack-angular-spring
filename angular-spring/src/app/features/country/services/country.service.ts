import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CountryService {
  API_URL: string = 'http://localhost:8080/countries'

  constructor(private http: HttpClient) { }

  public getAllCountries() {
    return this.http.get(this.API_URL);
  }

  public createCountry(country: any) {
    return this.http.post(this.API_URL, country);
  }
}
