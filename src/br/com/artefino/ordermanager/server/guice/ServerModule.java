package br.com.artefino.ordermanager.server.guice;

import br.com.artefino.ordermanager.server.handler.RecuperarSituacoesPedidoActionHandler;
import br.com.artefino.ordermanager.server.handler.clientes.AtualizarClienteActionHandler;
import br.com.artefino.ordermanager.server.handler.clientes.CadastrarClienteActionHandler;
import br.com.artefino.ordermanager.server.handler.clientes.PesquisarClientesActionHandler;
import br.com.artefino.ordermanager.server.handler.clientes.RecuperarClienteActionHandler;
import br.com.artefino.ordermanager.server.handler.clientes.RemoverClienteActionHandler;
import br.com.artefino.ordermanager.server.handler.pedidos.CadastrarPedidoActionHandler;
import br.com.artefino.ordermanager.server.handler.pedidos.PesquisarPedidosActionHandler;
import br.com.artefino.ordermanager.shared.action.RecuperarSituacoesPedidoAction;
import br.com.artefino.ordermanager.shared.action.clientes.AtualizarClienteAction;
import br.com.artefino.ordermanager.shared.action.clientes.CadastrarClienteAction;
import br.com.artefino.ordermanager.shared.action.clientes.PesquisarClientesAction;
import br.com.artefino.ordermanager.shared.action.clientes.RecuperarClienteAction;
import br.com.artefino.ordermanager.shared.action.clientes.RemoverClienteAction;
import br.com.artefino.ordermanager.shared.action.pedidos.CadastrarPedidoAction;
import br.com.artefino.ordermanager.shared.action.pedidos.PesquisarPedidosAction;

import com.gwtplatform.dispatch.server.guice.HandlerModule;

public class ServerModule extends HandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(RecuperarSituacoesPedidoAction.class,
				RecuperarSituacoesPedidoActionHandler.class);


		// Clientes
		bindHandler(CadastrarClienteAction.class,
				CadastrarClienteActionHandler.class);
		bindHandler(PesquisarClientesAction.class,
				PesquisarClientesActionHandler.class);
		bindHandler(RemoverClienteAction.class,
				RemoverClienteActionHandler.class);
		bindHandler(AtualizarClienteAction.class,
				AtualizarClienteActionHandler.class);
		bindHandler(RecuperarClienteAction.class,
				RecuperarClienteActionHandler.class);

		// Pedidos
		bindHandler(CadastrarPedidoAction.class,
				CadastrarPedidoActionHandler.class);
		bindHandler(PesquisarPedidosAction.class,
				PesquisarPedidosActionHandler.class);
	}
}
