package br.com.artefino.ordermanager.client.ui.pedidos;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.place.NameTokens;
import br.com.artefino.ordermanager.client.ui.clientes.PesquisarClientesDialogPresenterWidget;
import br.com.artefino.ordermanager.client.ui.clientes.PesquisarClientesDialogView;
import br.com.artefino.ordermanager.client.ui.main.MainPagePresenter;
import br.com.artefino.ordermanager.client.ui.pedidos.handlers.PedidoUIHandlers;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupViewCloseHandler;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.ClickEvent;

public class PedidoPresenter extends
		Presenter<PedidoPresenter.MyView, PedidoPresenter.MyProxy> implements PedidoUIHandlers {

	private PlaceManager placeManager;
	private final PesquisarClientesDialogPresenterWidget dialogBox;

	public interface MyView extends View, HasUiHandlers<PedidoUIHandlers> {
		// TODO Put your view methods here
	}

	@ProxyStandard
	@NameToken(NameTokens.pedido)
	public interface MyProxy extends ProxyPlace<PedidoPresenter> {
	}



	@Inject
	public PedidoPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final PlaceManager placeManager) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
		this.dialogBox = new PesquisarClientesDialogPresenterWidget(eventBus, new PesquisarClientesDialogView()) {
			@Override
			public void onTestButtonCliked(ClickEvent event) {		
				super.onTestButtonCliked(event);
				SC.say("teste");
			}			
		}; 
		
		
		
		getView().setUiHandlers(this);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetContextAreaContent,
				this);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	protected void onReveal() {
		super.onReveal();

		MainPagePresenter.getNavigationPaneHeader()
		.setContextAreaHeaderLabelContents(
				ArteFinoOrderManager.getConstants().tituloDetalhesPedido());
	}

	@Override
	public void onButtonAdicionarPedidoClicked() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onButtonVoltarClicked() {
		PlaceRequest placeRequest = new PlaceRequest(NameTokens.pedidos);
		placeManager.revealPlace(placeRequest);
	}

	@Override
	public void onButtonPesquisarClientesClicked() {		
		dialogBox.show();
	}
}
