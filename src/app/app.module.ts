import { RequestInterceptor } from './request.interceptor';

import { HttpClientModule, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Injectable, NgModule } from '@angular/core';
import { FormBuilder, FormsModule, NgModel, ReactiveFormsModule, NgForm } from '@angular/forms';
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
import { DEFAULT_CURRENCY_CODE, LOCALE_ID } from '@angular/core';
import ptBr from '@angular/common/locales/pt';
import { registerLocaleData } from '@angular/common';
import { PurchaseHistoryComponent } from './customer/purchase-history/purchase-history.component';
registerLocaleData(ptBr);



@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SectionComponent,
    LoginComponent,
    HomeComponent,
    ProdutosComponent,
    ProfileComponent,
    PurchaseHistoryComponent,
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
    ReactiveFormsModule,
  ],
  providers: [CustomerService, NgModel, NgForm, FormsModule, FormBuilder, { provide: HTTP_INTERCEPTORS, useClass: RequestInterceptor, multi: true }, { provide: LOCALE_ID, useValue: 'pt' }, {
    provide: DEFAULT_CURRENCY_CODE,
    useValue: 'BRL',
  },],
  bootstrap: [AppComponent]
})
export class AppModule { }

