package br.com.artefino.ordermanager.client.ui.despesas;

import java.util.List;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.LoggedInGatekeeper;
import br.com.artefino.ordermanager.client.place.NameTokens;
import br.com.artefino.ordermanager.client.ui.despesas.handlers.DespesaUIHandlers;
import br.com.artefino.ordermanager.client.ui.main.MainPagePresenter;
import br.com.artefino.ordermanager.client.util.DefaultAsyncCallback;
import br.com.artefino.ordermanager.shared.action.despesas.AtualizarDespesaAction;
import br.com.artefino.ordermanager.shared.action.despesas.AtualizarDespesaResult;
import br.com.artefino.ordermanager.shared.action.despesas.CadastrarDespesaAction;
import br.com.artefino.ordermanager.shared.action.despesas.CadastrarDespesaResult;
import br.com.artefino.ordermanager.shared.action.despesas.RecuperarDespesaAction;
import br.com.artefino.ordermanager.shared.action.despesas.RecuperarDespesaResult;
import br.com.artefino.ordermanager.shared.vo.CategoriaDespesaVo;
import br.com.artefino.ordermanager.shared.vo.DespesaVo;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.shared.EventBus;
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
	private final DispatchAsync dispatcher;
	private final PlaceManager placeManager;
	private String idDespesa;
	private String acao;
	private CategoriaDialogPresenterWidget categoriaDialog;

	public interface MyView extends View,
			HasUiHandlers<DespesaUIHandlers> {
		void setDespesa(DespesaVo despesa);

		void limparFormulario();

		void setIdCliente(Long id);

		void setCategorias(List<CategoriaDespesaVo> categorias);
	}

	@ProxyStandard
	@NameToken(NameTokens.despesa)
	@UseGatekeeper(LoggedInGatekeeper.class)
	public interface MyProxy extends ProxyPlace<DespesaPresenter> {
	}

	@Inject
	public DespesaPresenter(final EventBus eventBus,
			final MyView view, final MyProxy proxy, DispatchAsync dispatcher,
			PlaceManager placeManager, final CategoriaDialogPresenterWidget categoriaDialog) {
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
		this.placeManager = placeManager;
		this.categoriaDialog = categoriaDialog;

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

		getView().setCategorias(categoriaDialog.getCategorias());
		categoriaDialog.setPresenterParent(this);
	}

	@Override
	protected void onReveal() {
		super.onReveal();

		MainPagePresenter.getNavigationPaneHeader()
		.setContextAreaHeaderLabelContents(
				ArteFinoOrderManager.getConstants().tituloInformacoesDespesa());


		PlaceRequest placeRequest = placeManager.getCurrentPlaceRequest();
		acao = placeRequest.getParameter(ACAO, NOVO);
		idDespesa = placeRequest.getParameter(ID, null);
		if (EDITAR.equals(acao)) {
			Long id = -1L;

			try {
				id = Long.valueOf(idDespesa);
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
				new DefaultAsyncCallback<RecuperarDespesaResult>() {
					
					@Override
					public void onSuccess(RecuperarDespesaResult result) {
						super.onSuccess(result);
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
				new DefaultAsyncCallback<CadastrarDespesaResult>() {
					@Override
					public void onSuccess(CadastrarDespesaResult result) {
						super.onSuccess(result);
						SC.say(ArteFinoOrderManager.getMessages()
								.operacaoRealizadaComSucesso());
						getView().setIdCliente(result.getId());
					}
				});

	}

	private void atualizar(final DespesaVo despesa) {
		SC.showPrompt(ArteFinoOrderManager.getConstants().mensagemAguarde());
		dispatcher.execute(new AtualizarDespesaAction(despesa),
				new DefaultAsyncCallback<AtualizarDespesaResult>() {
					

					@Override
					public void onSuccess(AtualizarDespesaResult result) {
						super.onSuccess(result);
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

	@Override
	public void onButtonGerenciarCategoriasClicked() {
		categoriaDialog.show();

	}

	public void setCategorias(List<CategoriaDespesaVo> categorias) {
		getView().setCategorias(categorias);

	}

}
