package br.com.artefino.ordermanager.server;

import org.junit.Test;

import br.com.artefino.ordermanager.server.entities.Cliente;
import br.com.artefino.ordermanager.server.util.JPAUtil;

public class ClienteTest {

	@Test
	public void cadastrarCliente() {

		Cliente cliente = new Cliente();

		cliente.setNome("kermeson");

		JPAUtil.save(cliente);

	}

}
