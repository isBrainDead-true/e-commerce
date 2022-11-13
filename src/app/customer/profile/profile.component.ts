import { environment } from './../../../environments/environment';
import { Customer } from 'src/app/model/Customer';
import { endereco } from './../../model/endereco';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { Form, FormBuilder, FormControl, NgForm, ReactiveFormsModule, FormGroup, Validators } from '@angular/forms';
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
    numero: ['', Validators.pattern(/^[0-9.]+$/)],
    email: [''],
    cpf: ['', [Validators.pattern(/^[0-9.]+$/), Validators.required, Validators.minLength(11), Validators.maxLength(11)] ],
    telefone: ['', Validators.pattern(/^[0-9.]+$/)]
  })

  customerInUse: Customer = {
    id: "",
    name: "",
    username: "",
    password: "",
    email: "",
    cpf: "",
    phone: "",
    address: {
      zipcode: "",
      street: "",
      number: "",
      neighbor: "",
      estate: ""
    }
  };

  invalidZipcode: boolean = false;

  consultaCEP(event: Event): void {

    let pattern = /^[0-9]{8}$/;
    let zipcode = (event.target as HTMLInputElement).value;
    zipcode = zipcode.replace(/\.|\-/g, '');
    console.log(zipcode);

    if (pattern.test(zipcode)) {
      this.http.get<endereco>(`https://viacep.com.br/ws/${zipcode}/json/`).subscribe(dados => this.fillform(dados));
      this.invalidZipcode = false;
    }else{
      this.invalidZipcode = true;
    }

  }

  fillform(dados: endereco): void {
    setTimeout(() => {
      this.formulario.patchValue({
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
    this.http.get<Customer>(`https://e-commerce-368017.rj.r.appspot.com/api/v1/customer/get/user/${tkn}`)
      .subscribe(response => {

        this.customerInUse.id = response.id;
        this.customerInUse.name = response.name
        this.customerInUse.username = response.username;
        this.customerInUse.password = response.password;

        if (response.cpf != null) {
          this.formulario.controls['cpf'].setValue(response.cpf);
          this.customerInUse.cpf = response.cpf;
        }

        if (response.email != null) {
          this.formulario.controls['email'].setValue(response.email);
          this.customerInUse.email = response.email;
        }

        if (response.phone != null) {
          this.customerInUse.phone = response.phone;
          this.formulario.controls['telefone'].setValue(response.phone);
        }

        if (response.address != null) {
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


        } else {
          let btn = document.getElementById('btn-warning');
          btn?.click();
        }
      }
      );
  }

  updateProfile() {
    this.customerInUse.address.zipcode = this.formulario.controls['cep'].value || "";
    this.customerInUse.address.street = this.formulario.controls['rua'].value || "";
    this.customerInUse.address.number = this.formulario.controls['numero'].value || "";
    this.customerInUse.address.estate = this.formulario.controls['estado'].value || "";
    this.customerInUse.address.neighbor = this.formulario.controls['bairro'].value || "";
    this.customerInUse.email = this.formulario.controls['email'].value || "";
    this.customerInUse.cpf = this.formulario.controls['cpf'].value?.toString() || "";
    this.customerInUse.phone = this.formulario.controls['telefone'].value || "";


    this.customerService.updateCustomerInfo(this.customerInUse.id, this.customerInUse).subscribe(
      (Response: Customer) => {

        let modalBody = document.getElementById('modalBody');
        modalBody!.innerHTML = "Dados atualizados com sucesso";
        let btn = document.getElementById('btn-warning');
        btn?.click();
      },
      (error: HttpErrorResponse) => { }
    );
    console.log(this.customerInUse)
  }


  ngOnInit(): void {
    this.getCurrentUser();
  }

}