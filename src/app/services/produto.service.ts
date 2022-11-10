import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { produto } from '../model/produto';

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {


  url_prod = environment.url;

  constructor(private http: HttpClient) { }

  getProdutos(): Observable<produto[]>{
    return this.http.get<produto[]>(`${this.url_prod}/produto/all`)
  }
}
