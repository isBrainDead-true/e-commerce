package com.ecommer.backend.controller;

import com.ecommer.backend.model.Customer;
import com.ecommer.backend.model.Item;
import com.ecommer.backend.model.Pedido;
import com.ecommer.backend.model.Produto;
import com.ecommer.backend.service.CustomerService;
import com.ecommer.backend.service.ItemService;
import com.ecommer.backend.service.PedidoService;
import com.ecommer.backend.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PedidoController {

    private final PedidoService pedidoService;
    private final CustomerService customerService;
    private final ProdutoService produtoService;

    private final ItemService itemService;

    @PostMapping("order/new/{clienteId}/{totalPedido}/{formaPagamento}")
    public ResponseEntity<Pedido> realizarCompra(@PathVariable String clienteId,
                                         @PathVariable String totalPedido,
                                         @PathVariable String formaPagamento,
                                         @RequestBody List<Produto> produtosCarrinho){


        Optional<Customer> cliente = customerService.read(Long.parseLong(clienteId));

        Pedido compra = new Pedido();
        compra.setCliente(cliente.get());
        compra.setTotalPedido(Float.parseFloat(totalPedido));
        compra.setFormaPagamento(Integer.parseInt(totalPedido));

        List<Produto> produtosPedido = new ArrayList<>();
        List<Pedido> ped = new ArrayList<>();


        for ( Produto p : produtosCarrinho ){

            Produto estoque = produtoService.getProdutoById(p.getId());
            estoque.setQuantidadeEstoque(estoque.getQuantidadeEstoque() - p.getQuantidade());
            produtosPedido.add(produtoService.create(estoque));

        }
        Item itensPedido = new Item();
        itemService.salvar(itensPedido);

        compra.setItensPedido(itensPedido);

        pedidoService.realizarCompra(compra);

        cliente.get().getPedidos().add(compra);
        customerService.create(cliente.get());

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
