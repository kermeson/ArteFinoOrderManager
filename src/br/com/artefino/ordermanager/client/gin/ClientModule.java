package br.com.artefino.ordermanager.client.gin;

import br.com.artefino.ordermanager.client.place.ClientPlaceManager;
import br.com.artefino.ordermanager.client.place.DefaultPlace;
import br.com.artefino.ordermanager.client.place.NameTokens;
import br.com.artefino.ordermanager.client.ui.clientes.ClientePresenter;
import br.com.artefino.ordermanager.client.ui.clientes.ClienteView;
import br.com.artefino.ordermanager.client.ui.clientes.ClientesPresenter;
import br.com.artefino.ordermanager.client.ui.clientes.ClientesView;
import br.com.artefino.ordermanager.client.ui.main.MainPagePresenter;
import br.com.artefino.ordermanager.client.ui.main.MainPageView;
import br.com.artefino.ordermanager.client.ui.pedidos.PedidoPresenter;
import br.com.artefino.ordermanager.client.ui.pedidos.PedidoView;
import br.com.artefino.ordermanager.client.ui.pedidos.PedidosPresenter;
import br.com.artefino.ordermanager.client.ui.pedidos.PedidosView;
import br.com.artefino.ordermanager.client.ui.relatorios.RelatorioPedidosPresenter;
import br.com.artefino.ordermanager.client.ui.relatorios.RelatorioPedidosView;

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

		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.main);

		bindPresenter(ClientesPresenter.class, ClientesPresenter.MyView.class,
				ClientesView.class, ClientesPresenter.MyProxy.class);

		bindPresenter(ClientePresenter.class,
				ClientePresenter.MyView.class,
				ClienteView.class,
				ClientePresenter.MyProxy.class);

		bindPresenter(PedidosPresenter.class, PedidosPresenter.MyView.class,
				PedidosView.class, PedidosPresenter.MyProxy.class);

		bindPresenter(PedidoPresenter.class, PedidoPresenter.MyView.class,
				PedidoView.class, PedidoPresenter.MyProxy.class);


		bindSingletonPresenterWidget(PesquisarClientesDialogPresenterWidget.class,
				PesquisarClientesDialogPresenterWidget.MyView.class,
				PesquisarClientesDialogView.class);

		bindPresenter(RelatorioPedidosPresenter.class, RelatorioPedidosPresenter.MyView.class,
				RelatorioPedidosView.class, RelatorioPedidosPresenter.MyProxy.class);
	}
}
