import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { produto } from '../model/produto';

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {

  private apiURL: string = environment.url;

  constructor(private http: HttpClient) { }

  getProdutos(): Observable<produto[]>{
    return this.http.get<produto[]>(`${this.apiURL}produto/all`)
  }
}
