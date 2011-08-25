package br.com.artefino.ordermanager.client.ui;

import br.com.artefino.ordermanager.client.place.NameTokens;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class RootPresenter extends
		Presenter<RootPresenter.MyView, RootPresenter.MyProxy> {

	private final DispatchAsync dispatcher;
	private final PlaceManager placeManager;

	// don't forget to update SerendipityGinjector & ClientModule
	@ProxyStandard
	@NameToken(NameTokens.root)
	@NoGatekeeper
	public interface MyProxy extends ProxyPlace<RootPresenter> {
	}

	public interface MyView extends View {
	}

	/**
	 * Use this in leaf presenters, inside their {@link #revealInParent} method.
	 */
	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetContextAreaContent = new Type<RevealContentHandler<?>>();

	@Inject
	public RootPresenter(final EventBus eventBus, MyView view, MyProxy proxy,
			final DispatchAsync dispatcher, final PlaceManager placeManager) {
		super(eventBus, view, proxy);

		this.dispatcher = dispatcher;
		this.placeManager = placeManager;

	}

	@Override
	protected void onBind() {
		super.onBind();
		// reveal the first nested Presenter
		PlaceRequest placRequest = new PlaceRequest(NameTokens.login);
		placeManager.revealPlace(placRequest);
	}
	
	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}

	public DispatchAsync getDispatcher() {
		return dispatcher;
	}

}