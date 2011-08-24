package br.com.artefino.ordermanager.client.gin;

import br.com.artefino.ordermanager.client.CurrentUser;
import br.com.artefino.ordermanager.client.LoggedInGatekeeper;
import br.com.artefino.ordermanager.client.place.ClientPlaceManager;
import br.com.artefino.ordermanager.client.place.DefaultPlace;
import br.com.artefino.ordermanager.client.place.NameTokens;
import br.com.artefino.ordermanager.client.ui.ErrorPagePresenter;
import br.com.artefino.ordermanager.client.ui.ErrorPageView;
import br.com.artefino.ordermanager.client.ui.clientes.ClientePresenter;
import br.com.artefino.ordermanager.client.ui.clientes.ClienteView;
import br.com.artefino.ordermanager.client.ui.clientes.ClientesPresenter;
import br.com.artefino.ordermanager.client.ui.clientes.ClientesView;
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
import br.com.artefino.ordermanager.client.ui.clientes.PesquisarClientesDialogPresenterWidget;
import br.com.artefino.ordermanager.client.ui.clientes.PesquisarClientesDialogView;

public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {

		// Protect against XSRF attacks
		bindConstant().annotatedWith(SecurityCookie.class).to("gwtSessionId");

		install(new DefaultModule(ClientPlaceManager.class));



		bindPresenter(MainPagePresenter.class, MainPagePresenter.MyView.class,
				MainPageView.class, MainPagePresenter.MyProxy.class);

		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.login);

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

		bindSingletonPresenterWidget(
				PesquisarClientesDialogPresenterWidget.class,
				PesquisarClientesDialogPresenterWidget.MyView.class,
				PesquisarClientesDialogView.class);

		bindPresenter(RelatorioPedidosPresenter.class,
				RelatorioPedidosPresenter.MyView.class,
				RelatorioPedidosView.class,
				RelatorioPedidosPresenter.MyProxy.class);

		bindPresenter(ErrorPagePresenter.class,
				ErrorPagePresenter.MyView.class,
				ErrorPageView.class,
				ErrorPagePresenter.MyProxy.class);


		bindPresenterWidget(FormularioPesquisarPedidosPresenterWidget.class,
				FormularioPesquisarPedidosPresenterWidget.MyView.class,
				FormularioPesquisarPedidosView.class);

		bind(LoggedInGatekeeper.class).in(Singleton.class);

		bind(CurrentUser.class).in(Singleton.class);
	}
}
