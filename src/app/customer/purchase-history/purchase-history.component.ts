import { historico } from './../../model/historico';
import { PedidoService } from './../../services/pedido.service';
import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-purchase-history',
  templateUrl: './purchase-history.component.html',
  styleUrls: ['./purchase-history.component.css']
})
export class PurchaseHistoryComponent implements OnInit {

  constructor(private pedidoService: PedidoService) { }

  historico: historico[] = []

  ngOnInit(): void {
    this.pedidoService.getPedidos(sessionStorage.getItem('id') || '').subscribe(
      (response: historico[]) => {
        this.historico = response;
        console.log(this.historico)
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  };

}
