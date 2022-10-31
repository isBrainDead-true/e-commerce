import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { produto } from 'src/app/model/produto';
import { ProdutoService } from 'src/app/services/produto.service';

@Component({
  selector: 'app-produtos',
  templateUrl: './produtos.component.html',
  styleUrls: ['./produtos.component.css']
})
export class ProdutosComponent implements OnInit {

  qtdeEscolhido: number = 1;
  produtos: produto[] = [];
  prod: produto | any;

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

}
