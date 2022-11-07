import { Router } from '@angular/router';
import { CustomerService } from 'src/app/services/customer.service';
import { produto } from './../../model/produto';
import { NgForm, FormBuilder } from '@angular/forms';
import { HttpErrorResponse, HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ProdutoService } from 'src/app/services/produto.service';
import { Toast } from 'bootstrap'
import * as bootstrap from 'bootstrap';

@Component({
  selector: 'app-produtos',
  templateUrl: './produtos.component.html',
  styleUrls: ['./produtos.component.css']
})
export class ProdutosComponent implements OnInit {

  qtdeEscolhido: number = 1;
  produtos: produto[] = [];
  prod: produto | any;
  cart: produto[] = [];
  total: number = 0;

  pedido: any = {
    cliente: {},
    data: '',
    produtos: [],
    totalPedido: 0,
    formaPagamento: 0,
  }

  formulario = this.fb.group({
    id: [''],
    nome: [''],
    quantidade: [''],
    valor: [''],
    quantidadeEstoque: [''],
  })

  constructor(
    private service: ProdutoService,
    private fb: FormBuilder,
    private customerService: CustomerService,
    private router: Router) { }

  getProdutos(): void {
    this.service.getProdutos().subscribe(
      (response: produto[]) => {
        this.produtos = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  ngOnInit(): void {
    this.getProdutos();
    console.log(this.cart);
  }


  adicionarAoCarrinho(itemCarrinho: NgForm): void {
    var toastElList = [].slice.call(document.querySelectorAll('.toast'))
    var toastList = toastElList.map(function (toastEl) {
      return new bootstrap.Toast(toastEl).show();
    })

    let item: produto = {
      id: '',
      nome: '',
      image: '',
      descricao: '',
      quantidade: 0,
      quantidadeEstoque: 0,
      valor: 0
    }

    item = itemCarrinho.value;

    this.cart.push(item)
    console.log(item);
    this.total = this.total + (item.valor * item.quantidade);

  }

  removeFromCart(id: string) {
    for (let i = 0; i <= this.cart.length - 1; i++) {
      if (this.cart[i].id === id) {
        this.total = this.total - (this.cart[i].valor * this.cart[i].quantidade)
        this.cart.splice(i, 1);
        break;
      }
    }
  }

  purchase() {
    let id = sessionStorage.getItem('id') || '';
    this.pedido.produtos = this.cart; // body
    let totalPedido = "" + this.total;
    let forma_pagamento = (document.querySelector('input[name=form_pagamento]:checked') as HTMLInputElement).value
    let btnCloseCanvas = document.getElementById('close');
    let btnModal = document.getElementById('btn-warning');

    this.customerService.purchase(id, forma_pagamento, totalPedido, this.pedido.produtos)
      .subscribe(res => {
        console.log(res)
        this.cart = [];
        btnCloseCanvas?.click();
        btnModal?.click();
      })


  }


}




