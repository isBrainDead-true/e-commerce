import { NavbarService } from './services/navbar.service';
import { LoginComponent } from './login/login/login.component';
import { ActivatedRoute, RouterModule, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Component, OnInit, NgModule } from '@angular/core';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'cupcake-store';

  display: boolean = false;

  constructor(private nav: NavbarService){

  }


}
