package br.com.artefino.ordermanager.client.ui.login;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.CurrentUser;
import br.com.artefino.ordermanager.client.place.NameTokens;
import br.com.artefino.ordermanager.client.ui.RootPresenter;
import br.com.artefino.ordermanager.client.ui.login.handlers.SignInPageUiHandlers;
import br.com.artefino.ordermanager.client.util.DefaultAsyncCallback;
import br.com.artefino.ordermanager.shared.action.LoginAction;
import br.com.artefino.ordermanager.shared.action.LoginResult;
import br.com.artefino.ordermanager.shared.action.RecuperarUsuarioLogadoAction;
import br.com.artefino.ordermanager.shared.action.RecuperarUsuarioLogadoResult;
import br.com.artefino.ordermanager.shared.vo.UsuarioVo;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.smartgwt.client.util.SC;

public class LoginPresenter extends
		Presenter<LoginPresenter.MyView, LoginPresenter.MyProxy> implements
		SignInPageUiHandlers {

	private final DispatchAsync dispatcher;
	private final PlaceManager placeManager;
	private final CurrentUser currentUser;

	// don't forget to update SerendipityGinjector & ClientModule
	@ProxyStandard
	@NameToken(NameTokens.login)
	@NoGatekeeper
	public interface MyProxy extends ProxyPlace<LoginPresenter> {
	}

	public interface MyView extends View, HasUiHandlers<SignInPageUiHandlers> {
		String getUserName();

		String getPassword();

		void resetAndFocus();
	}

	@Inject
	public LoginPresenter(final EventBus eventBus, MyView view, MyProxy proxy,
			final DispatchAsync dispatcher, final PlaceManager placeManager,
			final CurrentUser currentUser) {
		super(eventBus, view, proxy);

		getView().setUiHandlers(this);
		this.dispatcher = dispatcher;
		this.placeManager = placeManager;
		this.currentUser = currentUser;
	}

	@Override
	protected void onReset() {
		super.onReset();
		getView().resetAndFocus();
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, RootPresenter.TYPE_SetContextAreaContent,
				this);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		
		dispatcher.execute(new RecuperarUsuarioLogadoAction(),
				new DefaultAsyncCallback<RecuperarUsuarioLogadoResult>() {

					@Override
					public void onSuccess(RecuperarUsuarioLogadoResult result) {
						super.onSuccess(result);

						if (result != null) {
							PlaceRequest placRequest = new PlaceRequest(
									NameTokens.main);
							placeManager.revealPlace(placRequest);
						}
					}
				});
	
	}

	@Override
	public void onOkButtonClicked() {
		sendCredentialsToServer();
	}

	private void sendCredentialsToServer() {
		String login = getView().getUserName();
		String password = getView().getPassword();

		SC.showPrompt(ArteFinoOrderManager.getConstants().mensagemAguarde());
		getDispatcher().execute(new LoginAction(login, password),
				new DefaultAsyncCallback<LoginResult>() {

					@Override
					public void onSuccess(LoginResult result) {
						super.onSuccess(result);

						UsuarioVo usuarioVo = result.getUsuario();
						currentUser.setLoggedIn(true);
						currentUser.setLogin(usuarioVo.getLogin());
						if (usuarioVo.getAdminstrador() != null
								&& usuarioVo.getAdminstrador()
										.equalsIgnoreCase("S")) {
							currentUser.setAdministrator(true);
						} else {
							currentUser.setAdministrator(false);
						}

						PlaceRequest placeRequest = new PlaceRequest(
								NameTokens.main);
						getPlaceManager().revealPlace(placeRequest);

						Log.debug("onSuccess() - " + result.getSessionKey());
					}
				});
	}

	private DispatchAsync getDispatcher() {
		return dispatcher;
	}

	private PlaceManager getPlaceManager() {
		return placeManager;
	}
}