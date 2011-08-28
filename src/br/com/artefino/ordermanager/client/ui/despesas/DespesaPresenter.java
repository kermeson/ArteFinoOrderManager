package br.com.artefino.ordermanager.client.ui.despesas;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.LoggedInGatekeeper;
import br.com.artefino.ordermanager.client.place.NameTokens;
import br.com.artefino.ordermanager.client.ui.despesas.handlers.DespesaUIHandlers;
import br.com.artefino.ordermanager.client.ui.main.MainPagePresenter;
import br.com.artefino.ordermanager.shared.action.despesas.AtualizarDespesaAction;
import br.com.artefino.ordermanager.shared.action.despesas.AtualizarDespesaResult;
import br.com.artefino.ordermanager.shared.action.despesas.CadastrarDespesaAction;
import br.com.artefino.ordermanager.shared.action.despesas.CadastrarDespesaResult;
import br.com.artefino.ordermanager.shared.action.despesas.RecuperarDespesaAction;
import br.com.artefino.ordermanager.shared.action.despesas.RecuperarDespesaResult;
import br.com.artefino.ordermanager.shared.vo.DespesaVo;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.smartgwt.client.util.SC;

public class DespesaPresenter
		extends
		Presenter<DespesaPresenter.MyView, DespesaPresenter.MyProxy>
		implements DespesaUIHandlers {

	private static final String ACAO = "acao";
	private static final String ID = "id";
	private static final String EDITAR = "editar";
	private static final String NOVO = "novo";
	private DispatchAsync dispatcher;
	private PlaceManager placeManager;
	private String idCliente;
	private String acao;

	public interface MyView extends View,
			HasUiHandlers<DespesaUIHandlers> {
		void setDespesa(DespesaVo despesa);

		void limparFormulario();

		void setIdCliente(Long id);
	}

	@ProxyStandard
	@NameToken(NameTokens.clientinformation)
	@UseGatekeeper(LoggedInGatekeeper.class)
	public interface MyProxy extends ProxyPlace<DespesaPresenter> {
	}

	@Inject
	public DespesaPresenter(final EventBus eventBus,
			final MyView view, final MyProxy proxy, DispatchAsync dispatcher,
			PlaceManager placeManager) {
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
		this.placeManager = placeManager;

		getView().setUiHandlers(this);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this,
				MainPagePresenter.TYPE_SetContextAreaContent, this);
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
				ArteFinoOrderManager.getConstants().tituloInformacoesCliente());


		PlaceRequest placeRequest = placeManager.getCurrentPlaceRequest();
		acao = placeRequest.getParameter(ACAO, NOVO);
		idCliente = placeRequest.getParameter(ID, null);
		if (EDITAR.equals(acao)) {
			Long id = -1L;

			try {
				id = Long.valueOf(idCliente);
			} catch (NumberFormatException nfe) {
				Log
						.debug("NumberFormatException: "
								+ nfe.getLocalizedMessage());
				return;
			}
			recuperarDespesa(id);
		} else if (NOVO.equals(acao)) {
			getView().limparFormulario();
		}


	}

	private void recuperarDespesa(Long id) {
		SC.showPrompt(ArteFinoOrderManager.getConstants().mensagemAguarde());
		dispatcher.execute(new RecuperarDespesaAction(id),
				new AsyncCallback<RecuperarDespesaResult>() {
					@Override
					public void onFailure(Throwable caught) {
						SC.clearPrompt();
						Log.debug("onFailure() - "
								+ caught.getLocalizedMessage());
					}

					@Override
					public void onSuccess(RecuperarDespesaResult result) {
						SC.clearPrompt();
						Log.debug("onSuccess()");
						getView().setDespesa(result.getDespesa());
					}
				});

	}

	@Override
	public void onButtonSalvarClicked(DespesaVo despesa) {
		if (despesa.getId() != null) {
			atualizar(despesa);
		} else {
			cadastrar(despesa);
		}
	}

	private void cadastrar(DespesaVo despesa) {
		SC.showPrompt(ArteFinoOrderManager.getConstants().mensagemAguarde());
		dispatcher.execute(new CadastrarDespesaAction(despesa),
				new AsyncCallback<CadastrarDespesaResult>() {
					@Override
					public void onFailure(Throwable caught) {
						SC.clearPrompt();
						SC.warn(caught.getMessage());
					}

					@Override
					public void onSuccess(CadastrarDespesaResult result) {
						SC.clearPrompt();
						SC.say(ArteFinoOrderManager.getMessages()
								.operacaoRealizadaComSucesso());
						getView().setIdCliente(result.getId());
					}
				});

	}

	private void atualizar(final DespesaVo despesa) {
		SC.showPrompt(ArteFinoOrderManager.getConstants().mensagemAguarde());
		dispatcher.execute(new AtualizarDespesaAction(despesa),
				new AsyncCallback<AtualizarDespesaResult>() {
					@Override
					public void onFailure(Throwable caught) {
						SC.clearPrompt();
						SC.warn(caught.getMessage());
					}

					@Override
					public void onSuccess(AtualizarDespesaResult result) {
						SC.clearPrompt();
						SC.say(ArteFinoOrderManager.getMessages()
								.operacaoRealizadaComSucesso());
					}
				});

	}

	@Override
	public void onButtonVoltarClicked() {
		PlaceRequest placeRequest = new PlaceRequest(NameTokens.despesas);
		placeManager.revealPlace(placeRequest);
	}

}
