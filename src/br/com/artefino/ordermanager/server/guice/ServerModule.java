package br.com.artefino.ordermanager.server.guice;

import com.gwtplatform.dispatch.server.guice.HandlerModule;
import br.com.artefino.ordermanager.shared.action.clientes.CadastrarCliente;
import br.com.artefino.ordermanager.server.handler.CadastrarClienteActionHandler;

public class ServerModule extends HandlerModule {

	@Override
	protected void configureHandlers() {

		bindHandler(CadastrarCliente.class, CadastrarClienteActionHandler.class);
	}
}
