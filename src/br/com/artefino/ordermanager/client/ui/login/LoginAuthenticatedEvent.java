package br.com.artefino.ordermanager.client.ui.login;

import br.com.artefino.ordermanager.client.CurrentUser;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;

public class LoginAuthenticatedEvent extends GwtEvent<LoginAuthenticatedEventHandler> {

  private static final Type<LoginAuthenticatedEventHandler> TYPE = new Type<LoginAuthenticatedEventHandler>();

  public static Type<LoginAuthenticatedEventHandler> getType() {
    return TYPE;
  }

  public static void fire(EventBus eventBus, CurrentUser currentUser) {
    eventBus.fireEvent(new LoginAuthenticatedEvent(currentUser));
  }

  private final CurrentUser currentUser;

  public LoginAuthenticatedEvent(CurrentUser currentUser) {
    this.currentUser = currentUser;
  }

  public CurrentUser getCurrentUser() {
    return currentUser;
  }

  @Override
  protected void dispatch(LoginAuthenticatedEventHandler handler) {
    handler.onLogin(this);
  }

  @Override
  public Type<LoginAuthenticatedEventHandler> getAssociatedType() {
    return getType();
  }
}
