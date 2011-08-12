package br.com.artefino.ordermanager.client.ui.relatorios;

import java.util.List;
import java.util.Map;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.place.NameTokens;
import br.com.artefino.ordermanager.client.ui.main.MainPagePresenter;
import br.com.artefino.ordermanager.client.ui.pedidos.FormularioPesquisarPedidosPresenterWidget;
import br.com.artefino.ordermanager.client.ui.relatorios.handlers.RelatorioPedidosUIHandlers;
import br.com.artefino.ordermanager.shared.action.RecuperarSituacoesPedidoAction;
import br.com.artefino.ordermanager.shared.action.RecuperarSituacoesPedidoResult;
import br.com.artefino.ordermanager.shared.vo.SituacaoPedidoVo;

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
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

@SuppressWarnings("unused")
public class RelatorioPedidosPresenter
		extends
		Presenter<RelatorioPedidosPresenter.MyView, RelatorioPedidosPresenter.MyProxy>
		implements RelatorioPedidosUIHandlers {

	private PlaceManager placeManager;
	private DispatchAsync dispatcher;
	private EventBus eventBus;
	private FormularioPesquisarPedidosPresenterWidget form;

	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetContextAreaContent = new Type<RevealContentHandler<?>>();

	public interface MyView extends View,
			HasUiHandlers<RelatorioPedidosUIHandlers> {
	}

	@ProxyStandard
	@NameToken(NameTokens.relatoriopedidos)
	public interface MyProxy extends ProxyPlace<RelatorioPedidosPresenter> {
	}

	@Inject
	public RelatorioPedidosPresenter(final EventBus eventBus,
			final MyView view, final MyProxy proxy,
			final PlaceManager placeManager, final DispatchAsync dispatcher,
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
				StringBuilder url = new StringBuilder();
				url.append("/reports/?report=pedidos");

				Map<String, Object> parametros = form.getParametrosPesquisa();
				if (parametros != null) {
					for (String key : parametros.keySet()) {
						if (parametros.get(key) != null) {
							url.append("&" + key + "=" + parametros.get(key));
						}
					}
				}

				Window.open(url.toString(), "_blank", "");
			}
		});
	}

	@Override
	protected void onReveal() {
		super.onReveal();

		MainPagePresenter.getNavigationPaneHeader()
				.setContextAreaHeaderLabelContents(
						ArteFinoOrderManager.getConstants().relatorioPedidos());

	}
}
