import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { produto } from 'src/app/model/produto';
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
  bootstrap: any;

  constructor(private service: ProdutoService) { }

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
  }


  adicionarAoCarrinho(input: any): void {
    var toastElList = [].slice.call(document.querySelectorAll('.toast'))
    var toastList = toastElList.map(function (toastEl) {
      return new bootstrap.Toast(toastEl).show();
    })

    console.log(input)


  }


}




