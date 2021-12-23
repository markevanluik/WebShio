import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthData } from '../model/auth.interface';
import { LoginData } from '../model/login.interface';
import { Person } from '../model/person.model';
import { SignUpData } from '../model/signup.interface';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private url = 'http://localhost:8080/';

  constructor(private http: HttpClient) {}

  login(loginData: LoginData) {
    return this.http.post<AuthData>(this.url + 'login', loginData);
  }

  signup(signUpData: SignUpData) {
    return this.http.post<Person>(this.url + 'signup', signUpData);
  }

  addTokenToHeader() {
    let headers = new HttpHeaders();
    const token = this.getToken();
    if (token) {
      headers = headers.set('Authorization', 'Bearer ' + token);
    }
    return headers;
  }

  getToken(): string | null {
    const authData = sessionStorage.getItem('authData');
    if (authData) {
      const parsedAuthData = JSON.parse(authData);
      if (
        parsedAuthData.token &&
        new Date(parsedAuthData.expiration) > new Date()
      ) {
        return parsedAuthData.token;
      }
    }
    return null;
  }
}
