package br.com.artefino.ordermanager.client.place;

import com.gwtplatform.mvp.client.proxy.PlaceManagerImpl;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import br.com.artefino.ordermanager.client.place.DefaultPlace;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;

public class ClientPlaceManager extends PlaceManagerImpl {

	private final PlaceRequest defaultPlaceRequest;

	@Inject
	public ClientPlaceManager(final EventBus eventBus,
			final TokenFormatter tokenFormatter,
			@DefaultPlace final String defaultPlaceNameToken) {
		super(eventBus, tokenFormatter);
		this.defaultPlaceRequest = new PlaceRequest(defaultPlaceNameToken);
	}

	@Override
	public void revealDefaultPlace() {
		revealPlace(defaultPlaceRequest, false);
	}

	@Override
	public void revealErrorPlace(String invalidHistoryToken) {
		PlaceRequest myRequest = new PlaceRequest(NameTokens.errorpage);
		revealPlace(myRequest);
	}

	@Override
	public void revealUnauthorizedPlace(String unauthorizedHistoryToken) {
		PlaceRequest placeRequest = new PlaceRequest(NameTokens.login);
		revealPlace(placeRequest);
	}
	
	
	@Override
	public void revealPlace(PlaceRequest request, boolean updateBrowserUrl) {
		// TODO Auto-generated method stub
		super.revealPlace(request, updateBrowserUrl);
	}
}
