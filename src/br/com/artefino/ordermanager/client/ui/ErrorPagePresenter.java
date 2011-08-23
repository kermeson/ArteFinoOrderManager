package br.com.artefino.ordermanager.client.ui;

import br.com.artefino.ordermanager.client.place.NameTokens;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class ErrorPagePresenter extends
    Presenter<ErrorPagePresenter.MyView, ErrorPagePresenter.MyProxy> implements
    ErrorPageUiHandlers {

  private final PlaceManager placeManager;

  //don't forget to update SerendipityGinjector & ClientModule
  @ProxyStandard
  @NameToken(NameTokens.errorpage)
  public interface MyProxy extends Proxy<ErrorPagePresenter>, Place {
  }

  public interface MyView extends View, HasUiHandlers<ErrorPageUiHandlers> {
  }

  @Inject
  public ErrorPagePresenter(EventBus eventBus, MyView view, MyProxy proxy,
      PlaceManager placeManager) {
    super(eventBus, view, proxy);

    getView().setUiHandlers(this);

    this.placeManager = placeManager;
  }

  @Override
  protected void onBind() {
    super.onBind();
  }

  @Override
  protected void revealInParent() {
    // RevealRootLayoutContentEvent.fire(this, this);
    RevealRootContentEvent.fire(this, this);
 }

  @Override
  public void onOkButtonClicked() {
    PlaceRequest placeRequest = new PlaceRequest(NameTokens.login);
    placeManager.revealPlace(placeRequest);
  }
}