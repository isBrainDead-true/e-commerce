import { PurchaseHistoryComponent } from './customer/purchase-history/purchase-history.component';
import { ProdutosComponent } from './customer/produtos/produtos.component';
import { ProfileComponent } from './customer/profile/profile.component';
import { SectionComponent } from './homepage/section/section.component';
import { LoginComponent } from './login/login/login.component';
import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './customer/home/home.component';

const routes: Routes = [
  { path: '', component: SectionComponent },
  { path: 'login', component: LoginComponent },
  { path: 'customerpage', component: HomeComponent, children: [
    { path: 'c-profile', component:  ProfileComponent },
    { path: 'produtos', component: ProdutosComponent },
    { path: 'historico', component: PurchaseHistoryComponent }
    //{ path: 'componentC', component: CComponent } },
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
