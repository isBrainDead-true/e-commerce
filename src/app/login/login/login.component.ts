
import { CustomerService } from './../../services/customer.service';
import { NavbarService } from './../../services/navbar.service';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { HttpErrorResponse, HttpClient } from '@angular/common/http';
import { Customer } from 'src/app/model/Customer';
import { environment } from 'src/environments/environment';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private router: Router,
    private nav: NavbarService,
    private http: HttpClient,
    private customerService: CustomerService) {
    
  }

  public credentials = {username: "", password: ""};
  public sessionId: string = "";

  ngOnInit() {

  }


  return(): void {
    this.nav.displayNavbar.emit(true);
    this.router.navigate(["/"]);
  }


  addUsuario(addForm: NgForm): void {
    document.getElementById("close")?.click();
    this.customerService.AddCustomer(addForm.value).subscribe(
      (Response: Customer) => { alert("Conta Criada com sucesso") },
      (error: HttpErrorResponse) => { }
    );
  }

  
  login() {
      let url = environment.url + "login";
      this.http.post<any>(url, {
        username: this.credentials.username,
        password: this.credentials.password
      }).subscribe(res => {
        if(res){
          this.sessionId = res.sessionID;
          sessionStorage.setItem('token ', this.sessionId);
          this.router.navigate(['customerpage']);
        }else{
          alert("Falha ao realizar o login");
        }
      })
  }


}




