package br.com.artefino.ordermanager.client.ui.pedidos;

import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.NameToken;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.place.NameTokens;

import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import br.com.artefino.ordermanager.client.ui.main.MainPagePresenter;
import br.com.artefino.ordermanager.client.ui.pedidos.handlers.PedidosUIHandlers;

public class PedidosPresenter extends
		Presenter<PedidosPresenter.MyView, PedidosPresenter.MyProxy> implements PedidosUIHandlers {

	private PlaceManager placeManager;
	
	public interface MyView extends View, HasUiHandlers<PedidosUIHandlers> {
		// TODO Put your view methods here
	}

	@ProxyStandard
	@NameToken(NameTokens.pedidos)
	public interface MyProxy extends ProxyPlace<PedidosPresenter> {
	}

	

	@Inject
	public PedidosPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, PlaceManager placeManager) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
		
		getView().setUiHandlers(this);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetContextAreaContent,
				this);
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
				ArteFinoOrderManager.getConstants().tituloPedidos());
	}

	@Override
	public void onButtonAdicionarPedidoClicked() {
		PlaceRequest placeRequest = new PlaceRequest(
				NameTokens.pedido).with("acao", "novo");
		placeManager.revealPlace(placeRequest);
		
	}
}
