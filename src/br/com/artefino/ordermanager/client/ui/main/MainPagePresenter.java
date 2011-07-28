package br.com.artefino.ordermanager.client.ui.main;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.place.NameTokens;
import br.com.artefino.ordermanager.client.ui.main.handlers.MainUIHandlers;
import br.com.artefino.ordermanager.client.ui.widgets.NavigationPane;
import br.com.artefino.ordermanager.client.ui.widgets.NavigationPaneHeader;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class MainPagePresenter extends
		Presenter<MainPagePresenter.MyView, MainPagePresenter.MyProxy>
		implements MainUIHandlers {
	private final PlaceManager placeManager;

	private static NavigationPaneHeader navigationPaneHeader;
	private static NavigationPane navigationPane;

	public interface MyView extends View, HasUiHandlers<MainUIHandlers> {

		NavigationPaneHeader getNavigationPaneHeader();
		NavigationPane getNavigationPane();
	}

	@ProxyStandard
	@NameToken(NameTokens.main)
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
			final MyProxy proxy, PlaceManager placeManager) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;

		getView().setUiHandlers(this);

		MainPagePresenter.navigationPaneHeader = getView()
				.getNavigationPaneHeader();
		MainPagePresenter.navigationPane = getView().getNavigationPane();
	}

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}

	@Override
	protected void onBind() {
		super.onBind();

		// expand the first Navigation Pane section
		getView().getNavigationPane().expandSection(
				ArteFinoOrderManager.getConstants()
						.menuPrincipalStackSectionName());

		// reveal the first nested Presenter
		PlaceRequest placRequest = new PlaceRequest(NameTokens.clientes);
		placeManager.revealPlace(placRequest);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
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

}
