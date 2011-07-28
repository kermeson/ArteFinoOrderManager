package br.com.artefino.ordermanager.client.gin;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.google.inject.Provider;
import br.com.artefino.ordermanager.client.ui.main.MainPagePresenter;
import br.com.artefino.ordermanager.client.ui.clients.ClientPresenter;
import br.com.artefino.ordermanager.client.ui.clients.ClientInformationPresenter;
import br.com.artefino.ordermanager.client.ui.pedidos.PedidosPresenter;

@GinModules({ DispatchAsyncModule.class, ClientModule.class })
public interface ClientGinjector extends Ginjector {

	EventBus getEventBus();

	PlaceManager getPlaceManager();

	Provider<MainPagePresenter> getMainPagePresenter();

	Provider<ClientPresenter> getClientPresenter();

	Provider<ClientInformationPresenter> getClientInformationPresenter();

	Provider<PedidosPresenter> getPedidosPresenter();
}
