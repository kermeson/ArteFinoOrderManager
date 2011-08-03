package br.com.artefino.ordermanager.server;

import org.junit.Test;

import br.com.artefino.ordermanager.server.entities.Cliente;
import br.com.artefino.ordermanager.server.entities.SituacaoPedido;
import br.com.artefino.ordermanager.server.util.JPAUtil;

public class ClienteTest {

	@Test
	public void cadastrarCliente() {

		Cliente cliente = new Cliente();

		cliente.setNome("kermeson");

		JPAUtil.save(cliente);

	}

	@Test
	public void cadastrarSituacoes() {

		SituacaoPedido situacao1 = new SituacaoPedido();
		situacao1.setNome("Novo");

		SituacaoPedido situacao2 = new SituacaoPedido();
		situacao2.setNome("Aguardando Pagamento");

		SituacaoPedido situacao3 = new SituacaoPedido();
		situacao3.setNome("Cancelado");

		SituacaoPedido situacao4 = new SituacaoPedido();
		situacao4.setNome("Pago");


		JPAUtil.save(situacao2);
		JPAUtil.save(situacao3);
		JPAUtil.save(situacao4);

	}

}
