package com.ecommer.backend.service;

import com.ecommer.backend.model.Pedido;
import com.ecommer.backend.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoRepository Pedidorepository;

    public Pedido realizarCompra(Pedido pedido){
        return this.Pedidorepository.save(pedido);
    }

}
