package br.com.artefino.ordermanager.client;

import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;

public class LoggedInGatekeeper implements Gatekeeper {

	private CurrentUser currentUser;

	@Inject
	public LoggedInGatekeeper(final CurrentUser currentUser) {
		this.currentUser = currentUser;
	}

	@Override
	public boolean canReveal() {
		boolean loggedIn = false;

		if (currentUser != null) {
			loggedIn = currentUser.isLoggedIn();
		}

		return loggedIn;
	}
}