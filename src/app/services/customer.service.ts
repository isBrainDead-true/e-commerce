import { environment } from '../../environments/environment.prod';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from '../model/Customer';
import { produto } from '../model/produto';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private apiURL: string = environment.url; //https://e-commerce-368017.rj.r.appspot.com/api/v1

  constructor(private http: HttpClient) { }

  getCustomer(): Observable<Customer[]>{
    return this.http.get<Customer[]>(`${this.apiURL}/users/all`)
  }

  AddCustomer(customer: Customer): Observable<Customer>{
    return this.http.post<Customer>(`${this.apiURL}/customer/register`, customer)
  }

  updateCustomer(customer: Customer): Observable<Customer>{
    return this.http.put<Customer>(`${this.apiURL}/customer/update`, customer)
  }

  updateCustomerInfo(id: string, customer: Customer): Observable<Customer>{
    return this.http.put<Customer>(`${this.apiURL}/customer/update/profile/${id}`, customer) 
  }

  deleteCustomer(id: number): Observable<void>{
    return this.http.delete<void>(`${this.apiURL}/customer/delete/${id}`)
  }

  getCustomerBySessionId(sessionId: string): Observable<Customer>{
    return this.http.get<Customer>(`${this.apiURL}/customer/get/user/${sessionId}`)
  }

  purchase(clientId: string, forma_pagamento: string , totalPedido: string, produtos: produto[]): Observable<any>{
    return this.http.post(`${this.apiURL}/order/new/${clientId}/${forma_pagamento}/${totalPedido}`, produtos );
  }

}
