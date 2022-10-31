import { environment } from '../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from '../model/Customer';


@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private apiURL: string = environment.url;

  constructor(private http: HttpClient) { }

  getCustomer(): Observable<Customer[]>{
    return this.http.get<Customer[]>(`${this.apiURL}users/all`)
  }

  AddCustomer(customer: Customer): Observable<Customer>{
    return this.http.post<Customer>(`${this.apiURL}customer/register`, customer)
  }

  updateCustomer(customer: Customer): Observable<Customer>{
    return this.http.put<Customer>(`${this.apiURL}customer/update`, customer)
  }

  deleteCustomer(id: number): Observable<void>{
    return this.http.delete<void>(`${this.apiURL}customer/delete/${id}`)
  }


}
