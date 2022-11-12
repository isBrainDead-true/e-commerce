package com.ecommer.backend;

import com.ecommer.backend.controller.AuthController;
import com.ecommer.backend.controller.CustomerController;
import com.ecommer.backend.controller.PedidoController;
import com.ecommer.backend.controller.ProdutoController;
import com.ecommer.backend.model.Pedido;
import com.ecommer.backend.model.Produto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class BackendApplicationTests {

	@Autowired
	private AuthController controller;


	@Autowired
	private CustomerController customerController;

	@Autowired
	private PedidoController pedidoController;


	@Autowired
	private ProdutoController produtoController;


	@Test
	public void shouldConfirmThatControllerAuthIsNotNull() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Test
	public void shouldConfirmThatCustomerControllerIsNotNull() throws Exception {
		assertThat(customerController).isNotNull();
	}

	@Test
	public void shouldConfirmThatPedidoControllerIsNotNull() throws Exception {
		assertThat(pedidoController).isNotNull();
	}

	@Test
	public void shouldConfirmThatProdutoControllerIsNotNull() throws Exception {
		assertThat(produtoController).isNotNull();
	}

	@Test
	public void checkCartValue(){
		Produto bolo = new Produto();
		Produto cupcake = new Produto();
		bolo.setValor(5.99f);
		bolo.setQuantidade(1);
		cupcake.setValor(3.99f);
		cupcake.setQuantidade(1);
		List<Produto> produtosPedido = new ArrayList<>();
		produtosPedido.add(bolo);
		produtosPedido.add(cupcake);

		float valorTotalPedido = 0;
		for (Produto produto : produtosPedido){
			valorTotalPedido = valorTotalPedido + (produto.getValor() * produto.getQuantidade());
		}

		assertThat(valorTotalPedido).isEqualTo(9.98f);

	}

}

