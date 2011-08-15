package br.com.artefino.ordermanager.client;

import br.com.artefino.ordermanager.client.ui.login.LoginAuthenticatedEvent;
import br.com.artefino.ordermanager.client.ui.login.LoginAuthenticatedEventHandler;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;

public class LoggedInGatekeeper implements Gatekeeper {

  private final EventBus eventBus;

  private CurrentUser currentUser;

  @Inject
  public LoggedInGatekeeper(final EventBus eventBus) {
    this.eventBus = eventBus;

    this.eventBus.addHandler(LoginAuthenticatedEvent.getType(), new LoginAuthenticatedEventHandler() {
      @Override
      public void onLogin(LoginAuthenticatedEvent event) {

        currentUser = event.getCurrentUser();

        Log.debug(currentUser.getLogin() + " credentials have been authenticated.");
      }
    });
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