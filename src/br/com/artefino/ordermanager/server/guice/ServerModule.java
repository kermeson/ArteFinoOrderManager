package br.com.artefino.ordermanager.server.guice;

import br.com.artefino.ordermanager.server.handler.CadastrarClienteActionHandler;
import br.com.artefino.ordermanager.server.handler.PesquisarClientesActionHandler;
import br.com.artefino.ordermanager.shared.action.clientes.CadastrarCliente;
import br.com.artefino.ordermanager.shared.action.clientes.PesquisarClientesAction;

import com.gwtplatform.dispatch.server.guice.HandlerModule;

public class ServerModule extends HandlerModule {

	@Override
	protected void configureHandlers() {

		bindHandler(CadastrarCliente.class, CadastrarClienteActionHandler.class);
		bindHandler(PesquisarClientesAction.class, PesquisarClientesActionHandler.class);
	}
}
