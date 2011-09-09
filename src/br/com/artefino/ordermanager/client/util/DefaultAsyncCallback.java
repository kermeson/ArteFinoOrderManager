package br.com.artefino.ordermanager.client.util;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.place.NameTokens;
import br.com.artefino.ordermanager.shared.exception.SessionTimeOutException;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;

public abstract class DefaultAsyncCallback<T> implements AsyncCallback<T> {

	@Override
	public void onFailure(Throwable caught) {
		SC.clearPrompt();

		if (caught instanceof SessionTimeOutException) {
			SC.confirm(ArteFinoOrderManager.getMessages().sessaoExpirou(),
					new BooleanCallback() {

						@Override
						public void execute(Boolean value) {
							if (value) {
								// Indica que o usuário não está logado
								ArteFinoOrderManager.getCurrentUser()
										.setLoggedIn(false);
								
								// Exibe a tela de login
								PlaceRequest placeRequest = new PlaceRequest(
										NameTokens.login);
								ArteFinoOrderManager.getPlaceManager()
										.revealPlace(placeRequest);
							}
						}
					});
		} else {
			if (caught.getMessage() != null) {
				SC.warn(caught.getMessage());
			}

		}
	}

	public void onSuccess(T result) {
		SC.clearPrompt();
	}

}
