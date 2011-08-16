package br.com.artefino.ordermanager.client.ui.login;


import br.com.artefino.ordermanager.client.CurrentUser;
import br.com.artefino.ordermanager.client.place.NameTokens;
import br.com.artefino.ordermanager.client.ui.login.handlers.SignInPageUiHandlers;
import br.com.artefino.ordermanager.shared.action.LoginAction;
import br.com.artefino.ordermanager.shared.action.LoginResult;
import br.com.artefino.ordermanager.shared.exception.LoginException;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;
import com.smartgwt.client.util.SC;

public class SignInPagePresenter extends
    Presenter<SignInPagePresenter.MyView, SignInPagePresenter.MyProxy> implements
        SignInPageUiHandlers {

  private final EventBus eventBus;
  private final DispatchAsync dispatcher;
  private final PlaceManager placeManager;

  // don't forget to update SerendipityGinjector & ClientModule
  @ProxyStandard
  @NameToken(NameTokens.login)
  @NoGatekeeper
  public interface MyProxy extends Proxy<SignInPagePresenter>, Place {
  }

  public interface MyView extends View, HasUiHandlers<SignInPageUiHandlers> {
    String getUserName();
    String getPassword();
    void resetAndFocus();
  }

  @Inject
  public SignInPagePresenter(final EventBus eventBus, MyView view, MyProxy proxy,
      final DispatchAsync dispatcher, final PlaceManager placeManager) {
    super(eventBus, view, proxy);

    getView().setUiHandlers(this);

    this.eventBus = eventBus;
    this.dispatcher = dispatcher;
    this.placeManager = placeManager;
  }

  @Override
  protected void onReset() {
    super.onReset();

    getView().resetAndFocus();
  }

  @Override
  protected void revealInParent() {
    RevealRootContentEvent.fire(this, this);
 }

  @Override
  public void onOkButtonClicked() {
    sendCredentialsToServer();
  }

  private void sendCredentialsToServer() {
    String login = getView().getUserName();
    String password = getView().getPassword();

    getDispatcher().execute(new LoginAction(login, password),
        new AsyncCallback<LoginResult>() {

      @Override
      public void onFailure(Throwable caught) {
        String message = "onFailure() - " + caught.getLocalizedMessage();
        SC.warn(message);
      }

      @Override
      public void onSuccess(LoginResult result) {
        CurrentUser currentUser = new CurrentUser(getView().getUserName());

        LoginAuthenticatedEvent.fire(eventBus, currentUser);

        PlaceRequest placeRequest = new PlaceRequest(NameTokens.main);
        getPlaceManager().revealPlace(placeRequest);

        Log.debug("onSuccess() - " + result.getSessionKey());
      }
    });
  }

  private DispatchAsync getDispatcher() {
    return dispatcher;
  }

  private PlaceManager getPlaceManager()  {
    return placeManager;
  }
}