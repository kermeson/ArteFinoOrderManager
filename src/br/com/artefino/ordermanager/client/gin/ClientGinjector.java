package br.com.artefino.ordermanager.client.gin;

import br.com.artefino.ordermanager.client.ui.clientes.ClientePresenter;
import br.com.artefino.ordermanager.client.ui.clientes.ClientesPresenter;
import br.com.artefino.ordermanager.client.ui.main.MainPagePresenter;
import br.com.artefino.ordermanager.client.ui.pedidos.PedidoPresenter;
import br.com.artefino.ordermanager.client.ui.pedidos.PedidosPresenter;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

@GinModules({ DispatchAsyncModule.class, ClientModule.class })
public interface ClientGinjector extends Ginjector {

	EventBus getEventBus();

	PlaceManager getPlaceManager();

	Provider<MainPagePresenter> getMainPagePresenter();

	Provider<ClientesPresenter> getClientPresenter();

	Provider<ClientePresenter> getClientInformationPresenter();

	Provider<PedidosPresenter> getPedidosPresenter();
	
	Provider<PedidoPresenter> getPedidoPresenter();
}
