import { Injectable, EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NavbarService {

  displayNavbar = new EventEmitter<boolean>();


  constructor() { }
}
