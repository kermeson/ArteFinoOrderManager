package br.com.artefino.ordermanager.client.ui.main;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.CurrentUser;
import br.com.artefino.ordermanager.client.LoggedInGatekeeper;
import br.com.artefino.ordermanager.client.place.NameTokens;
import br.com.artefino.ordermanager.client.ui.RootPresenter;
import br.com.artefino.ordermanager.client.ui.main.handlers.MainUIHandlers;
import br.com.artefino.ordermanager.client.ui.widgets.NavigationPane;
import br.com.artefino.ordermanager.client.ui.widgets.NavigationPaneHeader;
import br.com.artefino.ordermanager.client.ui.widgets.NavigationPaneSectionListGrid;
import br.com.artefino.ordermanager.client.util.DefaultAsyncCallback;
import br.com.artefino.ordermanager.shared.action.LogoutAction;
import br.com.artefino.ordermanager.shared.action.LogoutResult;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.smartgwt.client.util.SC;

public class MainPagePresenter extends
		Presenter<MainPagePresenter.MyView, MainPagePresenter.MyProxy>
		implements MainUIHandlers {
	private final PlaceManager placeManager;

	private final DispatchAsync dispatcher;

	private final CurrentUser currentUser;

	private static NavigationPaneSectionListGrid menuPrincipalListGrid;

	private static NavigationPaneHeader navigationPaneHeader;
	private static NavigationPane navigationPane;

	public interface MyView extends View, HasUiHandlers<MainUIHandlers> {

		NavigationPaneHeader getNavigationPaneHeader();

		NavigationPaneSectionListGrid getMenuPricipalListGrid();

		void setNomeUsuario(String login);
	}

	@ProxyStandard
	@NameToken(NameTokens.main)
	@UseGatekeeper(LoggedInGatekeeper.class)
	public interface MyProxy extends ProxyPlace<MainPagePresenter> {
	}

	/**
	 * Use this in leaf presenters, inside their {@link #revealInParent} method.
	 */
	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetContextAreaContent = new Type<RevealContentHandler<?>>();

	private static final String TOKEN = "!";

	@Inject
	public MainPagePresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final PlaceManager placeManager,
			final DispatchAsync dispatcher, final CurrentUser currentUser) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
		this.dispatcher = dispatcher;
		this.currentUser = currentUser;

		getView().setUiHandlers(this);

		navigationPaneHeader = getView().getNavigationPaneHeader();

		menuPrincipalListGrid = getView().getMenuPricipalListGrid();
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, RootPresenter.TYPE_SetContextAreaContent,
				this);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	protected void onReveal() {
		super.onReveal();

		// reveal the first nested Presenter
		PlaceRequest placRequest = new PlaceRequest(NameTokens.clientes);
		placeManager.revealPlace(placRequest);

		// Seleciona o item cliente
		menuPrincipalListGrid.deselectAllRecords();
		menuPrincipalListGrid.selectRecord(0);
		
		getView().setNomeUsuario(ArteFinoOrderManager.getCurrentUser().getLogin());
	}

	public static NavigationPaneHeader getNavigationPaneHeader() {
		return navigationPaneHeader;
	}

	public static NavigationPane getNavigationPane() {
		return navigationPane;
	}

	@Override
	public void onNavigationPaneSectionClicked(String place) {
		if (place.length() != 0) {
			PlaceRequest placeRequest = new PlaceRequest(TOKEN + place);
			placeManager.revealPlace(placeRequest);
		}

	}

	@Override
	public void onLabelSairClicked() {
		SC.showPrompt(ArteFinoOrderManager.getConstants().mensagemAguarde());
		dispatcher.execute(new LogoutAction(),
				new DefaultAsyncCallback<LogoutResult>() {

					@Override
					public void onSuccess(LogoutResult result) {
						super.onSuccess(result);

						// Logout
						currentUser.setLoggedIn(false);
						currentUser.setAdministrator(false);

						PlaceRequest placeRequest = new PlaceRequest(
								NameTokens.login);
						placeManager.revealPlace(placeRequest);
					}
				});

	}

}
