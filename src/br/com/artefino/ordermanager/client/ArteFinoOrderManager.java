package br.com.artefino.ordermanager.client;

import br.com.artefino.ordermanager.client.gin.ClientGinjector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.DelayedBindRegistry;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

public class ArteFinoOrderManager implements EntryPoint {

	private static ArteFinoOrderManagerConstants constants;

	private static final ClientGinjector ginjector = GWT
			.create(ClientGinjector.class);

	private static ArteFinoOrderManagerMessages messages;

	private static final PlaceManager placeManager = ginjector
			.getPlaceManager();

	private static final CurrentUser currentUser = ginjector.getCurrentUser();

	@Override
	public void onModuleLoad() {
		// This is required for Gwt-Platform proxy's generator
		DelayedBindRegistry.bind(ginjector);

		// load constants
		constants = (ArteFinoOrderManagerConstants) GWT
				.create(ArteFinoOrderManagerConstants.class);
		messages = (ArteFinoOrderManagerMessages) GWT
				.create(ArteFinoOrderManagerMessages.class);

		ginjector.getPlaceManager().revealDefaultPlace();

	}

	public static ArteFinoOrderManagerConstants getConstants() {
		return constants;
	}

	public static ArteFinoOrderManagerMessages getMessages() {
		return messages;
	}

	public static PlaceManager getPlaceManager() {
		return placeManager;
	}

	public static CurrentUser getCurrentUser() {
		return currentUser;
	}
}
