package br.com.artefino.ordermanager.client.ui.clients;

import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.NameToken;
import br.com.artefino.ordermanager.client.place.NameTokens;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import br.com.artefino.ordermanager.client.ui.main.MainPagePresenter;

public class ClientPresenter extends
		Presenter<ClientPresenter.MyView, ClientPresenter.MyProxy> {

	public interface MyView extends View {
		// TODO Put your view methods here
	}

	@ProxyStandard
	@NameToken(NameTokens.client)
	public interface MyProxy extends ProxyPlace<ClientPresenter> {
	}

	@Inject
	public ClientPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy) {
		super(eventBus, view, proxy);
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
}
