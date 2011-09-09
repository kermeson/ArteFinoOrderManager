package br.com.artefino.ordermanager.client.gin;

import br.com.artefino.ordermanager.client.CurrentUser;
import br.com.artefino.ordermanager.client.LoggedInGatekeeper;
import br.com.artefino.ordermanager.client.ui.ErrorPagePresenter;
import br.com.artefino.ordermanager.client.ui.RootPresenter;
import br.com.artefino.ordermanager.client.ui.clientes.ClientePresenter;
import br.com.artefino.ordermanager.client.ui.clientes.ClientesPresenter;
import br.com.artefino.ordermanager.client.ui.despesas.DespesaPresenter;
import br.com.artefino.ordermanager.client.ui.despesas.DespesasPresenter;
import br.com.artefino.ordermanager.client.ui.login.LoginPresenter;
import br.com.artefino.ordermanager.client.ui.main.MainPagePresenter;
import br.com.artefino.ordermanager.client.ui.pedidos.PedidoPresenter;
import br.com.artefino.ordermanager.client.ui.pedidos.PedidosPresenter;
import br.com.artefino.ordermanager.client.ui.relatorios.RelatorioPedidosPresenter;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

@GinModules( { DispatchAsyncModule.class, ClientModule.class })
public interface ClientGinjector extends Ginjector {

	EventBus getEventBus();

	PlaceManager getPlaceManager();

	CurrentUser getCurrentUser();

	Provider<RootPresenter> getRootPresenter();

	Provider<MainPagePresenter> getMainPagePresenter();

	Provider<ClientesPresenter> getClientesPresenter();

	Provider<ClientePresenter> getClientePresenter();

	Provider<PedidosPresenter> getPedidosPresenter();

	Provider<PedidoPresenter> getPedidoPresenter();

	Provider<DespesasPresenter> getDespesasPresenter();

	Provider<DespesaPresenter> getDespesaPresenter();

	Provider<RelatorioPedidosPresenter> getRelatorioPedidosPresenter();

	Provider<ErrorPagePresenter> getErrorPagePresenter();

	LoggedInGatekeeper getLoggedInGatekeeper();

	// Sign In
	Provider<LoginPresenter> getLoginPresenter();
}
