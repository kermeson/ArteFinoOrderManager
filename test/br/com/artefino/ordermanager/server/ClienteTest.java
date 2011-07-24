package br.com.artefino.ordermanager.server;

import org.junit.Test;

import br.com.artefino.ordermanager.server.entities.Cliente;
import br.com.artefino.ordermanager.server.util.JPAUtil;

import com.allen_sauer.gwt.log.client.Log;

public class ClienteTest {

	@Test
	public void cadastrarCliente() throws Exception {
		try {

			Cliente cliente = new Cliente();

			cliente.setNome("kermeson");
			JPAUtil.startTransaction();
			JPAUtil.persist(cliente);
			JPAUtil.endTransaction(true);

			Log.info("Cliente cadastrado" + cliente.getId());


		} catch (Exception e) {
			Log.error("Unable to create Account", e);

			throw e;
		}
	}
}
