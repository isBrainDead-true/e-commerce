import { Router } from '@angular/router';
import { Component, OnInit, EventEmitter } from '@angular/core';
import { NavbarService } from 'src/app/services/navbar.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private router: Router, private nav: NavbarService) { }

  ngOnInit(): void {
  }



  logout() : void {
    sessionStorage.clear();
    this.nav.displayNavbar.emit(true);
    this.router.navigate(['']);
  }

}
