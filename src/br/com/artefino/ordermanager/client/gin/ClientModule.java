package br.com.artefino.ordermanager.client.gin;

import br.com.artefino.ordermanager.client.place.ClientPlaceManager;

import com.gwtplatform.dispatch.shared.SecurityCookie;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import br.com.artefino.ordermanager.client.ui.main.MainPagePresenter;
import br.com.artefino.ordermanager.client.ui.main.MainPageView;
import br.com.artefino.ordermanager.client.place.DefaultPlace;
import br.com.artefino.ordermanager.client.place.NameTokens;
import br.com.artefino.ordermanager.client.ui.clients.ClientPresenter;
import br.com.artefino.ordermanager.client.ui.clients.ClientView;
import br.com.artefino.ordermanager.client.ui.clients.ClientInformationPresenter;
import br.com.artefino.ordermanager.client.ui.clients.ClientInformationView;
import br.com.artefino.ordermanager.client.ui.pedidos.PedidosPresenter;
import br.com.artefino.ordermanager.client.ui.pedidos.PedidosView;

public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {

		// Protect against XSRF attacks
		bindConstant().annotatedWith(SecurityCookie.class).to("gwtSessionId");

		install(new DefaultModule(ClientPlaceManager.class));

		bindPresenter(MainPagePresenter.class, MainPagePresenter.MyView.class,
				MainPageView.class, MainPagePresenter.MyProxy.class);

		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.main);

		bindPresenter(ClientPresenter.class, ClientPresenter.MyView.class,
				ClientView.class, ClientPresenter.MyProxy.class);

		bindPresenter(ClientInformationPresenter.class,
				ClientInformationPresenter.MyView.class,
				ClientInformationView.class,
				ClientInformationPresenter.MyProxy.class);

		bindPresenter(PedidosPresenter.class, PedidosPresenter.MyView.class,
				PedidosView.class, PedidosPresenter.MyProxy.class);
	}
}
