package br.com.artefino.ordermanager.client;

import br.com.artefino.ordermanager.client.gin.ClientGinjector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.DelayedBindRegistry;

public class ArteFinoOrderManager implements EntryPoint {

	private static ArteFinoOrderManagerConstants constants;

	private final ClientGinjector ginjector = GWT.create(ClientGinjector.class);

	private static ArteFinoOrderManagerMessages messages;

	@Override
	public void onModuleLoad() {
		// This is required for Gwt-Platform proxy's generator
		DelayedBindRegistry.bind(ginjector);

		// load constants
	    constants = (ArteFinoOrderManagerConstants) GWT.create(ArteFinoOrderManagerConstants.class);
	    messages = (ArteFinoOrderManagerMessages) GWT.create(ArteFinoOrderManagerMessages.class);

		ginjector.getPlaceManager().revealCurrentPlace();


	}

	public static ArteFinoOrderManagerConstants getConstants() {
		return constants;
	}

	public static ArteFinoOrderManagerMessages getMessages() {
		return messages;
	}
}
