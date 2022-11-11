package com.ecommer.backend.service;

import com.ecommer.backend.model.Customer;
import com.ecommer.backend.model.Pedido;
import com.ecommer.backend.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoRepository Pedidorepository;
    private final CustomerService customerService;

    public Pedido realizarCompra(Pedido pedido){
        return this.Pedidorepository.save(pedido);
    }

    public List<Pedido> historico(Long idCliente) {
        Optional<Customer> tmp = customerService.read(idCliente);
        final List<Pedido> historico = tmp.get().getPedidos();
        return historico;
    }
}
