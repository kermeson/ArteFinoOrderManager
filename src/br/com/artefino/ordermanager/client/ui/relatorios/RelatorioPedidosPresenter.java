package br.com.artefino.ordermanager.client.ui.relatorios;

import java.util.Map;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.place.NameTokens;
import br.com.artefino.ordermanager.client.ui.main.MainPagePresenter;
import br.com.artefino.ordermanager.client.ui.relatorios.handlers.RelatorioPedidosUIHandlers;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class RelatorioPedidosPresenter extends
		Presenter<RelatorioPedidosPresenter.MyView, RelatorioPedidosPresenter.MyProxy> implements
		RelatorioPedidosUIHandlers {

	private PlaceManager placeManager;
	private DispatchAsync dispatcher;
	private EventBus eventBus;

	public interface MyView extends View, HasUiHandlers<RelatorioPedidosUIHandlers> {
		Map<String, Object> getParametrosPesquisa();
	}

	@ProxyStandard
	@NameToken(NameTokens.relatoriopedidos)
	public interface MyProxy extends ProxyPlace<RelatorioPedidosPresenter> {
	}

	@Inject
	public RelatorioPedidosPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final PlaceManager placeManager,
			final DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.eventBus = eventBus;
		this.placeManager = placeManager;
		this.dispatcher = dispatcher;

		getView().setUiHandlers(this);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this,
				MainPagePresenter.TYPE_SetContextAreaContent, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	protected void onReveal() {
		super.onReveal();

		MainPagePresenter.getNavigationPaneHeader()
				.setContextAreaHeaderLabelContents(
						ArteFinoOrderManager.getConstants().relatorioPedidos());
	}


	@Override
	public void onButtonExportarClicked() {
		StringBuilder url = new StringBuilder();
		url.append("/reports/?report=pedidos");
		Window.open(url.toString(), "_blank", "");

	}


}
