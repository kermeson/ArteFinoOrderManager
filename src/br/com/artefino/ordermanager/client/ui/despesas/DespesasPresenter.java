package br.com.artefino.ordermanager.client.ui.despesas;

import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.LoggedInGatekeeper;
import br.com.artefino.ordermanager.client.place.NameTokens;
import br.com.artefino.ordermanager.client.ui.clientes.handlers.ClientesUIHandlers;
import br.com.artefino.ordermanager.client.ui.main.MainPagePresenter;
import br.com.artefino.ordermanager.client.util.DefaultAsyncCallback;
import br.com.artefino.ordermanager.shared.action.despesas.PesquisarDespesasAction;
import br.com.artefino.ordermanager.shared.action.despesas.PesquisarDespesasResult;
import br.com.artefino.ordermanager.shared.action.despesas.RemoverDespesaAction;
import br.com.artefino.ordermanager.shared.action.despesas.RemoverDespesaResult;
import br.com.artefino.ordermanager.shared.vo.DespesaVo;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.Window;
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
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

public class DespesasPresenter extends
		Presenter<DespesasPresenter.MyView, DespesasPresenter.MyProxy>
		implements ClientesUIHandlers {

	public interface MyView extends View, HasUiHandlers<ClientesUIHandlers> {

		void setResultSet(List<DespesaVo> despesas);

		void removerDespesaSelecionada();

	}

	@ProxyStandard
	@NameToken(NameTokens.despesas)
	@UseGatekeeper(LoggedInGatekeeper.class)
	public interface MyProxy extends ProxyPlace<DespesasPresenter> {
	}

	private static final String ACAO = "acao";

	private static final String EDITAR = "editar";

	private static final String ID = "id";

	private static final String NOVO = "novo";

	private final PlaceManager placeManager;

	private final DispatchAsync dispatcher;

	private final FormularioPesquisarDespesasPresenterWidget form;

	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetContextAreaContent = new Type<RevealContentHandler<?>>();

	@Inject
	public DespesasPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final PlaceManager placeManager,
			final DispatchAsync dispatcher,
			final FormularioPesquisarDespesasPresenterWidget form) {
		super(eventBus, view, proxy);
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

		form.addButtonPesquisarClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				pesquisarDespesas();
			}
		});

		form.addButtonExportarClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				StringBuilder url = new StringBuilder();
				url.append("/reports/?report=despesas&rnd="
						+ new Date().getTime());

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

		pesquisarDespesas();

		MainPagePresenter.getNavigationPaneHeader()
				.setContextAreaHeaderLabelContents(
						ArteFinoOrderManager.getConstants()
								.tituloPaginaDespesas());
	}

	@Override
	public void onButtonNewClientClicked() {
		PlaceRequest placeRequest = new PlaceRequest(NameTokens.despesa).with(
				ACAO, NOVO);
		placeManager.revealPlace(placeRequest);
	}

	private void pesquisarDespesas() {
		SC.showPrompt(ArteFinoOrderManager.getConstants().mensagemCarregando());
		dispatcher.execute(new PesquisarDespesasAction(10, 1, form
				.getParametrosPesquisa()),
				new DefaultAsyncCallback<PesquisarDespesasResult>() {

					@Override
					public void onSuccess(PesquisarDespesasResult result) {
						super.onSuccess(result);
						getView().setResultSet(result.getDespesas());
					}
				});
	}

	@Override
	public void onRecordDoubleClicked(String id) {
		PlaceRequest placeRequest = new PlaceRequest(NameTokens.despesa);
		placeRequest = placeRequest.with(ACAO, EDITAR).with(ID, id);
		placeManager.revealPlace(placeRequest);
	}

	@Override
	public void onDeleteButtonClicked(String idDespesa) {
		Long id = -1L;

		try {
			id = Long.valueOf(idDespesa);
		} catch (NumberFormatException nfe) {
			Log.debug("NumberFormatException: " + nfe.getLocalizedMessage());
			return;
		}

		SC.showPrompt(ArteFinoOrderManager.getConstants().mensagemAguarde());
		dispatcher.execute(new RemoverDespesaAction(id),
				new DefaultAsyncCallback<RemoverDespesaResult>() {

					@Override
					public void onSuccess(RemoverDespesaResult result) {
						super.onSuccess(result);
						getView().removerDespesaSelecionada();
					}
				});

	}

	@Override
	public void onEditarButtonClicked(String id) {
		PlaceRequest placeRequest = new PlaceRequest(NameTokens.despesa);
		placeRequest = placeRequest.with(ACAO, EDITAR).with(ID, id);
		placeManager.revealPlace(placeRequest);
	}
}
