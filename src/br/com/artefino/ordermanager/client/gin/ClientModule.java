package br.com.artefino.ordermanager.client.gin;

import br.com.artefino.ordermanager.client.CurrentUser;
import br.com.artefino.ordermanager.client.LoggedInGatekeeper;
import br.com.artefino.ordermanager.client.place.ClientPlaceManager;
import br.com.artefino.ordermanager.client.place.DefaultPlace;
import br.com.artefino.ordermanager.client.place.NameTokens;
import br.com.artefino.ordermanager.client.ui.ErrorPagePresenter;
import br.com.artefino.ordermanager.client.ui.ErrorPageView;
import br.com.artefino.ordermanager.client.ui.RootPresenter;
import br.com.artefino.ordermanager.client.ui.RootView;
import br.com.artefino.ordermanager.client.ui.clientes.ClientePresenter;
import br.com.artefino.ordermanager.client.ui.clientes.ClienteView;
import br.com.artefino.ordermanager.client.ui.clientes.ClientesPresenter;
import br.com.artefino.ordermanager.client.ui.clientes.ClientesView;
import br.com.artefino.ordermanager.client.ui.clientes.PesquisarClientesDialogPresenterWidget;
import br.com.artefino.ordermanager.client.ui.clientes.PesquisarClientesDialogView;
import br.com.artefino.ordermanager.client.ui.despesas.CategoriaDialogPresenterWidget;
import br.com.artefino.ordermanager.client.ui.despesas.CategoriaDialogView;
import br.com.artefino.ordermanager.client.ui.despesas.DespesaPresenter;
import br.com.artefino.ordermanager.client.ui.despesas.DespesaView;
import br.com.artefino.ordermanager.client.ui.despesas.DespesasPresenter;
import br.com.artefino.ordermanager.client.ui.despesas.DespesasView;
import br.com.artefino.ordermanager.client.ui.despesas.FormularioPesquisarDespesasPresenterWidget;
import br.com.artefino.ordermanager.client.ui.despesas.FormularioPesquisarDespesasView;
import br.com.artefino.ordermanager.client.ui.login.LoginPresenter;
import br.com.artefino.ordermanager.client.ui.login.LoginView;
import br.com.artefino.ordermanager.client.ui.main.MainPagePresenter;
import br.com.artefino.ordermanager.client.ui.main.MainPageView;
import br.com.artefino.ordermanager.client.ui.pedidos.FormularioPesquisarPedidosPresenterWidget;
import br.com.artefino.ordermanager.client.ui.pedidos.FormularioPesquisarPedidosView;
import br.com.artefino.ordermanager.client.ui.pedidos.PedidoPresenter;
import br.com.artefino.ordermanager.client.ui.pedidos.PedidoView;
import br.com.artefino.ordermanager.client.ui.pedidos.PedidosPresenter;
import br.com.artefino.ordermanager.client.ui.pedidos.PedidosView;
import br.com.artefino.ordermanager.client.ui.relatorios.RelatorioPedidosPresenter;
import br.com.artefino.ordermanager.client.ui.relatorios.RelatorioPedidosView;

import com.google.inject.Singleton;
import com.gwtplatform.dispatch.shared.SecurityCookie;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {

		// Protect against XSRF attacks
		bindConstant().annotatedWith(SecurityCookie.class).to("gwtSessionId");
		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.root);

		install(new DefaultModule(ClientPlaceManager.class));

		bind(LoggedInGatekeeper.class).in(Singleton.class);

		bind(CurrentUser.class).in(Singleton.class);

		bindPresenter(RootPresenter.class, RootPresenter.MyView.class,
				RootView.class, RootPresenter.MyProxy.class);

		bindPresenter(MainPagePresenter.class, MainPagePresenter.MyView.class,
				MainPageView.class, MainPagePresenter.MyProxy.class);

		bindPresenter(ClientesPresenter.class, ClientesPresenter.MyView.class,
				ClientesView.class, ClientesPresenter.MyProxy.class);

		bindPresenter(ClientePresenter.class, ClientePresenter.MyView.class,
				ClienteView.class, ClientePresenter.MyProxy.class);

		bindPresenter(PedidosPresenter.class, PedidosPresenter.MyView.class,
				PedidosView.class, PedidosPresenter.MyProxy.class);

		bindPresenter(PedidoPresenter.class, PedidoPresenter.MyView.class,
				PedidoView.class, PedidoPresenter.MyProxy.class);

		bindPresenter(LoginPresenter.class, LoginPresenter.MyView.class,
				LoginView.class, LoginPresenter.MyProxy.class);

		bindPresenter(DespesasPresenter.class, DespesasPresenter.MyView.class,
				DespesasView.class, DespesasPresenter.MyProxy.class);

		bindPresenter(DespesaPresenter.class, DespesaPresenter.MyView.class,
				DespesaView.class, DespesaPresenter.MyProxy.class);

		bindPresenter(RelatorioPedidosPresenter.class,
				RelatorioPedidosPresenter.MyView.class,
				RelatorioPedidosView.class,
				RelatorioPedidosPresenter.MyProxy.class);

		bindPresenter(ErrorPagePresenter.class,
				ErrorPagePresenter.MyView.class, ErrorPageView.class,
				ErrorPagePresenter.MyProxy.class);

		bindPresenterWidget(FormularioPesquisarPedidosPresenterWidget.class,
				FormularioPesquisarPedidosPresenterWidget.MyView.class,
				FormularioPesquisarPedidosView.class);

		bindPresenterWidget(CategoriaDialogPresenterWidget.class,
				CategoriaDialogPresenterWidget.MyView.class,
				CategoriaDialogView.class);
		
		bindPresenterWidget(FormularioPesquisarDespesasPresenterWidget.class,
				FormularioPesquisarDespesasPresenterWidget.MyView.class,
				FormularioPesquisarDespesasView.class);

		bindSingletonPresenterWidget(
				PesquisarClientesDialogPresenterWidget.class,
				PesquisarClientesDialogPresenterWidget.MyView.class,
				PesquisarClientesDialogView.class);

	}
}
