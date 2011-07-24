package br.com.artefino.ordermanager.client.ui.clients;

import br.com.artefino.ordermanager.client.place.NameTokens;
import br.com.artefino.ordermanager.client.ui.clients.handlers.ClientUIHandlers;
import br.com.artefino.ordermanager.client.ui.main.MainPagePresenter;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class ClientPresenter extends
		Presenter<ClientPresenter.MyView, ClientPresenter.MyProxy>  implements ClientUIHandlers {

	public interface MyView extends View, HasUiHandlers<ClientUIHandlers>  {
		// TODO Put your view methods here
	}

	@ProxyStandard
	@NameToken(NameTokens.client)
	public interface MyProxy extends ProxyPlace<ClientPresenter> {
	}

	private PlaceManager placeManager;

	@Inject
	public ClientPresenter(final EventBus eventBus, final MyView view,
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
	}

	@Override
	public void onButtonNewClientClicked() {
		PlaceRequest placeRequest = new PlaceRequest(NameTokens.clientinformation);
		placeManager.revealPlace(placeRequest);
	}
}
