import { environment } from 'src/environments/environment.prod';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {

  constructor(private http: HttpClient) { }

  api = environment.url

  getPedidos(clientId: string): Observable<any>{
    return this.http.get<any[]>(`${this.api}/order/historico/${clientId}`);
  }

}
