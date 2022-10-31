import { RequestInterceptor } from './request.interceptor';

import { HttpClientModule, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Injectable, NgModule } from '@angular/core';
import { FormBuilder, FormsModule, NgModel, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './homepage/header/header.component';
import { SectionComponent } from './homepage/section/section.component';
import { LoginComponent } from './login/login/login.component';
import { HomeComponent } from './customer/home/home.component';
import { ProdutosComponent } from './customer/produtos/produtos.component';
import { CustomerService } from './services/customer.service';
import { ProfileComponent } from './customer/profile/profile.component';



@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SectionComponent,
    LoginComponent,
    HomeComponent,
    ProdutosComponent,
    ProfileComponent,
  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
  ],
  exports: [
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [CustomerService, NgModel, FormsModule, FormBuilder, {provide: HTTP_INTERCEPTORS, useClass: RequestInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }

