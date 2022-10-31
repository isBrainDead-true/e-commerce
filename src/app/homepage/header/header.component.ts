import { NavbarService } from './../../services/navbar.service';
import { LoginComponent } from './../../login/login/login.component';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})

export class HeaderComponent implements OnInit {
  router: any;
  display: boolean = true;

  constructor(private nav: NavbarService) { }

  ngOnInit(){
      this.nav.displayNavbar.subscribe(
        mostrar => this.display = mostrar
      )
  }

  hide(): void { 
    this.display = false;
  }

  show(): void {
    this.display = true;
  }

}
