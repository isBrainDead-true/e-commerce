import { endereco } from './../../model/endereco';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { Form, NgForm, ReactiveFormsModule } from '@angular/forms';
import { getLocaleDayPeriods, JsonPipe } from '@angular/common';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private http: HttpClient) { }

  consultaCEP(form: NgForm, event: Event): void {

    let pattern = /^[0-9]{8}$/;
    let zipcode = (event.target as HTMLInputElement).value;
    console.log(zipcode);

    if (pattern.test(zipcode)) {
      this.http.get<endereco>(`https://viacep.com.br/ws/${zipcode}/json/`).subscribe(dados => this.fillform(dados, form));
    }

  }

  fillform(dados: endereco, form: NgForm): void {
    setTimeout( () => {
      form.setValue({
        cep: dados.cep,
        bairro: dados.bairro,
        estado: dados.uf,
        rua: dados.logradouro,
        numero: ""
      });
    })

  }

  ngOnInit(): void {
  }

}