import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AppService {

  baseUrl = "http://localhost:8080/api/home";
  constructor(private httpClient : HttpClient) { }

  hello():Observable<string>{
    const headers = new HttpHeaders().set('Content-type','text/plain;charset=utf-8');
    return this.httpClient.get(this.baseUrl,
      {headers,responseType:'text'});
  }
}
