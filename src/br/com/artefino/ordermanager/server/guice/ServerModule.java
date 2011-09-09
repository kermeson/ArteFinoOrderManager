package br.com.artefino.ordermanager.server.guice;

import br.com.artefino.ordermanager.server.handler.LoginActionHandler;
import br.com.artefino.ordermanager.server.handler.LogoutActionHandler;
import br.com.artefino.ordermanager.server.handler.RecuperarSituacoesPedidoActionHandler;
import br.com.artefino.ordermanager.server.handler.RecuperarUsuarioLogadoActionHandler;
import br.com.artefino.ordermanager.server.handler.clientes.AtualizarClienteActionHandler;
import br.com.artefino.ordermanager.server.handler.clientes.CadastrarClienteActionHandler;
import br.com.artefino.ordermanager.server.handler.clientes.PesquisarClientesActionHandler;
import br.com.artefino.ordermanager.server.handler.clientes.RecuperarClienteActionHandler;
import br.com.artefino.ordermanager.server.handler.clientes.RemoverClienteActionHandler;
import br.com.artefino.ordermanager.server.handler.despesas.AtualizarDespesaActionHandler;
import br.com.artefino.ordermanager.server.handler.despesas.CadastrarDespesaActionHandler;
import br.com.artefino.ordermanager.server.handler.despesas.PesquisarDespesasActionHandler;
import br.com.artefino.ordermanager.server.handler.despesas.RecuperarDespesaActionHandler;
import br.com.artefino.ordermanager.server.handler.despesas.RemoverDespesaActionHandler;
import br.com.artefino.ordermanager.server.handler.despesas.categorias.CadastrarCategoriaDespesaActionHandler;
import br.com.artefino.ordermanager.server.handler.despesas.categorias.PesquisarCategoriasDespesaActionHandler;
import br.com.artefino.ordermanager.server.handler.despesas.categorias.RemoverCategoriaDespesaActionHandler;
import br.com.artefino.ordermanager.server.handler.pedidos.AtualizarPedidoActionHandler;
import br.com.artefino.ordermanager.server.handler.pedidos.CadastrarPedidoActionHandler;
import br.com.artefino.ordermanager.server.handler.pedidos.PesquisarPedidosActionHandler;
import br.com.artefino.ordermanager.server.handler.pedidos.RecuperarPedidoActionHandler;
import br.com.artefino.ordermanager.server.handler.validator.LoggedInActionValidator;
import br.com.artefino.ordermanager.shared.action.LoginAction;
import br.com.artefino.ordermanager.shared.action.LogoutAction;
import br.com.artefino.ordermanager.shared.action.RecuperarSituacoesPedidoAction;
import br.com.artefino.ordermanager.shared.action.RecuperarUsuarioLogadoAction;
import br.com.artefino.ordermanager.shared.action.clientes.AtualizarClienteAction;
import br.com.artefino.ordermanager.shared.action.clientes.CadastrarClienteAction;
import br.com.artefino.ordermanager.shared.action.clientes.PesquisarClientesAction;
import br.com.artefino.ordermanager.shared.action.clientes.RecuperarClienteAction;
import br.com.artefino.ordermanager.shared.action.clientes.RemoverClienteAction;
import br.com.artefino.ordermanager.shared.action.despesas.AtualizarDespesaAction;
import br.com.artefino.ordermanager.shared.action.despesas.CadastrarDespesaAction;
import br.com.artefino.ordermanager.shared.action.despesas.PesquisarDespesasAction;
import br.com.artefino.ordermanager.shared.action.despesas.RecuperarDespesaAction;
import br.com.artefino.ordermanager.shared.action.despesas.RemoverDespesaAction;
import br.com.artefino.ordermanager.shared.action.despesas.categorias.CadastrarCategoriaDespesaAction;
import br.com.artefino.ordermanager.shared.action.despesas.categorias.PesquisarCategoriasDespesaAction;
import br.com.artefino.ordermanager.shared.action.despesas.categorias.RemoverCategoriaDespesaAction;
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

		// Despesas
		bindHandler(CadastrarDespesaAction.class,
				CadastrarDespesaActionHandler.class, LoggedInActionValidator.class);
		bindHandler(PesquisarDespesasAction.class,
				PesquisarDespesasActionHandler.class, LoggedInActionValidator.class);
		bindHandler(RemoverDespesaAction.class,
				RemoverDespesaActionHandler.class, LoggedInActionValidator.class);
		bindHandler(AtualizarDespesaAction.class,
				AtualizarDespesaActionHandler.class, LoggedInActionValidator.class);
		bindHandler(RecuperarDespesaAction.class,
				RecuperarDespesaActionHandler.class, LoggedInActionValidator.class);

		// Categorias de despesas
		bindHandler(CadastrarCategoriaDespesaAction.class,
				CadastrarCategoriaDespesaActionHandler.class, LoggedInActionValidator.class);

		bindHandler(PesquisarCategoriasDespesaAction.class,
				PesquisarCategoriasDespesaActionHandler.class, LoggedInActionValidator.class);
		bindHandler(RemoverCategoriaDespesaAction.class,
				RemoverCategoriaDespesaActionHandler.class, LoggedInActionValidator.class);




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
		
		bindHandler(RecuperarUsuarioLogadoAction.class,
				RecuperarUsuarioLogadoActionHandler.class);
	}
}
