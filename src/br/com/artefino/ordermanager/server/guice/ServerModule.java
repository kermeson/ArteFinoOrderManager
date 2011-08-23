package br.com.artefino.ordermanager.server.guice;

import br.com.artefino.ordermanager.server.handler.LoginActionHandler;
import br.com.artefino.ordermanager.server.handler.LogoutActionHandler;
import br.com.artefino.ordermanager.server.handler.RecuperarSituacoesPedidoActionHandler;
import br.com.artefino.ordermanager.server.handler.clientes.AtualizarClienteActionHandler;
import br.com.artefino.ordermanager.server.handler.clientes.CadastrarClienteActionHandler;
import br.com.artefino.ordermanager.server.handler.clientes.PesquisarClientesActionHandler;
import br.com.artefino.ordermanager.server.handler.clientes.RecuperarClienteActionHandler;
import br.com.artefino.ordermanager.server.handler.clientes.RemoverClienteActionHandler;
import br.com.artefino.ordermanager.server.handler.pedidos.AtualizarPedidoActionHandler;
import br.com.artefino.ordermanager.server.handler.pedidos.CadastrarPedidoActionHandler;
import br.com.artefino.ordermanager.server.handler.pedidos.PesquisarPedidosActionHandler;
import br.com.artefino.ordermanager.server.handler.pedidos.RecuperarPedidoActionHandler;
import br.com.artefino.ordermanager.server.handler.validator.LoggedInActionValidator;
import br.com.artefino.ordermanager.shared.action.LoginAction;
import br.com.artefino.ordermanager.shared.action.LogoutAction;
import br.com.artefino.ordermanager.shared.action.RecuperarSituacoesPedidoAction;
import br.com.artefino.ordermanager.shared.action.clientes.AtualizarClienteAction;
import br.com.artefino.ordermanager.shared.action.clientes.CadastrarClienteAction;
import br.com.artefino.ordermanager.shared.action.clientes.PesquisarClientesAction;
import br.com.artefino.ordermanager.shared.action.clientes.RecuperarClienteAction;
import br.com.artefino.ordermanager.shared.action.clientes.RemoverClienteAction;
import br.com.artefino.ordermanager.shared.action.pedidos.AtualizarPedidoAction;
import br.com.artefino.ordermanager.shared.action.pedidos.CadastrarPedidoAction;
import br.com.artefino.ordermanager.shared.action.pedidos.PesquisarPedidosAction;
import br.com.artefino.ordermanager.shared.action.pedidos.RecuperarPedidoAction;

import com.gwtplatform.dispatch.server.guice.HandlerModule;

public class ServerModule extends HandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(RecuperarSituacoesPedidoAction.class,
				RecuperarSituacoesPedidoActionHandler.class);

		// Clientes
		bindHandler(CadastrarClienteAction.class,
				CadastrarClienteActionHandler.class, LoggedInActionValidator.class);
		bindHandler(PesquisarClientesAction.class,
				PesquisarClientesActionHandler.class, LoggedInActionValidator.class);
		bindHandler(RemoverClienteAction.class,
				RemoverClienteActionHandler.class, LoggedInActionValidator.class);
		bindHandler(AtualizarClienteAction.class,
				AtualizarClienteActionHandler.class, LoggedInActionValidator.class);
		bindHandler(RecuperarClienteAction.class,
				RecuperarClienteActionHandler.class, LoggedInActionValidator.class);

		// Pedidos
		bindHandler(CadastrarPedidoAction.class,
				CadastrarPedidoActionHandler.class, LoggedInActionValidator.class);
		bindHandler(PesquisarPedidosAction.class,
				PesquisarPedidosActionHandler.class, LoggedInActionValidator.class);
		bindHandler(AtualizarPedidoAction.class,
				AtualizarPedidoActionHandler.class, LoggedInActionValidator.class);
		bindHandler(RecuperarPedidoAction.class,
				RecuperarPedidoActionHandler.class, LoggedInActionValidator.class);

		// Login
		bindHandler(LoginAction.class, LoginActionHandler.class);
		bindHandler(LogoutAction.class, LogoutActionHandler.class);
	}
}
