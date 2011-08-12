package br.com.artefino.ordermanager.client.ui.pedidos;

import java.util.List;
import java.util.Map;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.place.NameTokens;
import br.com.artefino.ordermanager.client.ui.main.MainPagePresenter;
import br.com.artefino.ordermanager.client.ui.pedidos.handlers.PedidosUIHandlers;
import br.com.artefino.ordermanager.shared.action.pedidos.PesquisarPedidosAction;
import br.com.artefino.ordermanager.shared.action.pedidos.PesquisarPedidosResult;
import br.com.artefino.ordermanager.shared.vo.PedidoVo;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

public class PedidosPresenter extends
		Presenter<PedidosPresenter.MyView, PedidosPresenter.MyProxy> implements
		PedidosUIHandlers {

	private PlaceManager placeManager;
	private DispatchAsync dispatcher;
	private EventBus eventBus;
	private FormularioPesquisarPedidosPresenterWidget form;

	public interface MyView extends View, HasUiHandlers<PedidosUIHandlers> {
		void setResultSet(List<PedidoVo> pedidosVo);
	}

	@ProxyStandard
	@NameToken(NameTokens.pedidos)
	public interface MyProxy extends ProxyPlace<PedidosPresenter> {
	}

	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetContextAreaContent = new Type<RevealContentHandler<?>>();

	@Inject
	public PedidosPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final PlaceManager placeManager,
			final DispatchAsync dispatcher,
			final FormularioPesquisarPedidosPresenterWidget form) {
		super(eventBus, view, proxy);
		this.eventBus = eventBus;
		this.placeManager = placeManager;
		this.dispatcher = dispatcher;
		this.form = form;

		getView().setUiHandlers(this);

		addToSlot(TYPE_SetContextAreaContent, form);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this,
				MainPagePresenter.TYPE_SetContextAreaContent, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		
		form.addButtonExportarClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				pesquisarPedidos();
			}
		});
	}

	@Override
	protected void onReveal() {
		super.onReveal();

		MainPagePresenter.getNavigationPaneHeader()
				.setContextAreaHeaderLabelContents(
						ArteFinoOrderManager.getConstants().tituloPedidos());

		pesquisarPedidos();
	}

	@Override
	public void onButtonAdicionarPedidoClicked() {
		PlaceRequest placeRequest = new PlaceRequest(NameTokens.pedido).with(
				"acao", "novo");
		placeManager.revealPlace(placeRequest);

	}

	private void pesquisarPedidos() {
		SC.showPrompt(ArteFinoOrderManager.getConstants().mensagemCarregando());

		dispatcher.execute(new PesquisarPedidosAction(10, 1, form
				.getParametrosPesquisa()),
				new AsyncCallback<PesquisarPedidosResult>() {
					@Override
					public void onFailure(Throwable caught) {
						SC.clearPrompt();
						Log.debug("onFailure() - "
								+ caught.getLocalizedMessage());
					}

					@Override
					public void onSuccess(PesquisarPedidosResult result) {
						SC.clearPrompt();
						getView().setResultSet(result.getPedidosVo());
					}
				});
	}

	@Override
	public void onButtonImprimirPedido(String idPedido) {
		StringBuilder url = new StringBuilder();
		url.append("/reports/?report=pedido&id=" + idPedido);
		Window.open(url.toString(), "_blank", "");
	}

	@Override
	public void onButtonPesquisarClicked() {
		//pesquisarPedidos();
	}
}
