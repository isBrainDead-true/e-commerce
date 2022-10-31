import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor() { }

  cep: string = "";

  consultaCEP(): any{
    console.log(this.cep);
  }

  ngOnInit(): void {
  }

}
