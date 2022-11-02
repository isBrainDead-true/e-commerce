import { environment } from './../../../environments/environment';
import { Customer } from 'src/app/model/Customer';
import { endereco } from './../../model/endereco';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { Form, FormBuilder, FormControl, NgForm, ReactiveFormsModule, FormGroup } from '@angular/forms';
import { getLocaleDayPeriods, JsonPipe } from '@angular/common';
import { CustomerService } from 'src/app/services/customer.service';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private http: HttpClient, 
    private fb: FormBuilder,
    private customerService: CustomerService) { }

  formulario = this.fb.group({
    cep: [''],
    rua: "",
    bairro: [''],
    estado: [''],
    numero: ['']
  })

  customerInUse: Customer = {
    id: 0,
    name: "",
    username: "",
    password: "",
    address: {
      zipcode: "",
      street: "",
      number: "",
      neighbor: "",
      estate: ""
    }
  };

  consultaCEP(event: Event): void {

    let pattern = /^[0-9]{8}$/;
    let zipcode = (event.target as HTMLInputElement).value;
    console.log(zipcode);

    if (pattern.test(zipcode)) {
      this.http.get<endereco>(`https://viacep.com.br/ws/${zipcode}/json/`).subscribe(dados => this.fillform(dados));
    }

  }

  fillform(dados: endereco): void {
    setTimeout(() => {
      this.formulario.setValue({
        cep: dados.cep,
        bairro: dados.bairro,
        estado: dados.uf,
        rua: dados.logradouro,
        numero: ""
      });
    })
  }

  getCurrentUser() {
    let currentCustomer: Customer;
    let tkn = sessionStorage.getItem("token");
    this.http.get<Customer>(`http://localhost:8080/api/v1/customer/get/user/${tkn}`)
      .subscribe(response => {
        if (response.address != null) {
          this.customerInUse.id = response.id;
          this.customerInUse.name = response.name
          this.customerInUse.username = response.username;
          this.customerInUse.password = response.password;
          this.customerInUse.address.zipcode = response.address.zipcode;
          this.customerInUse.address.street = response.address.street;
          this.customerInUse.address.number = response.address.number;
          this.customerInUse.address.neighbor = response.address.neighbor;
          this.customerInUse.address.estate = response.address.estate;
          this.formulario.controls['cep'].setValue(response.address.zipcode);
          this.formulario.controls['rua'].setValue(response.address.street);
          this.formulario.controls['bairro'].setValue(response.address.neighbor);
          this.formulario.controls['estado'].setValue(response.address.estate);
          this.formulario.controls['numero'].setValue(response.address.number);
        }else{
          alert("Por gentileza complete o seu perfil")
        }
      }
      );
  }

  updateProfile(){
    this.customerInUse.address.zipcode = this.formulario.controls['cep'].value || "";
    this.customerInUse.address.street = this.formulario.controls['rua'].value || "";
    this.customerInUse.address.number = this.formulario.controls['numero'].value || "";
    this.customerInUse.address.estate = this.formulario.controls['estado'].value || "";
    this.customerInUse.address.neighbor = this.formulario.controls['bairro'].value || "";

    this.customerService.updateCustomerInfo(this.customerInUse).subscribe(
      (Response: Customer) => { alert("dados alterados com sucesso") },
      (error: HttpErrorResponse) => { }
    );
    console.log(this.customerInUse)
  }


  ngOnInit(): void {
    this.getCurrentUser();
  }

}