package br.com.artefino.ordermanager.client.ui.clientes;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.LoggedInGatekeeper;
import br.com.artefino.ordermanager.client.place.NameTokens;
import br.com.artefino.ordermanager.client.ui.clientes.handlers.ClienteUIHandlers;
import br.com.artefino.ordermanager.client.ui.main.MainPagePresenter;
import br.com.artefino.ordermanager.shared.action.clientes.AtualizarClienteAction;
import br.com.artefino.ordermanager.shared.action.clientes.AtualizarClienteResult;
import br.com.artefino.ordermanager.shared.action.clientes.CadastrarClienteAction;
import br.com.artefino.ordermanager.shared.action.clientes.CadastrarClienteResult;
import br.com.artefino.ordermanager.shared.action.clientes.RecuperarClienteAction;
import br.com.artefino.ordermanager.shared.action.clientes.RecuperarClienteResult;
import br.com.artefino.ordermanager.shared.vo.ClienteVo;

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

public class ClientePresenter
		extends
		Presenter<ClientePresenter.MyView, ClientePresenter.MyProxy>
		implements ClienteUIHandlers {

	private static final String ACAO = "acao";
	private static final String ID = "id";
	private static final String EDITAR = "editar";
	private static final String NOVO = "novo";
	private DispatchAsync dispatcher;
	private PlaceManager placeManager;
	private String idCliente;
	private String acao;

	public interface MyView extends View,
			HasUiHandlers<ClienteUIHandlers> {
		void setCliente(ClienteVo clienteVo);

		void limparFormulario();

		void setIdCliente(Long id);
	}

	@ProxyStandard
	@NameToken(NameTokens.clientinformation)
	@UseGatekeeper(LoggedInGatekeeper.class)
	public interface MyProxy extends ProxyPlace<ClientePresenter> {
	}

	@Inject
	public ClientePresenter(final EventBus eventBus,
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
			recuperarCliente(id);
		} else if (NOVO.equals(acao)) {
			getView().limparFormulario();
		}


	}

	private void recuperarCliente(Long id) {
		SC.showPrompt(ArteFinoOrderManager.getConstants().mensagemAguarde());
		dispatcher.execute(new RecuperarClienteAction(id),
				new AsyncCallback<RecuperarClienteResult>() {
					@Override
					public void onFailure(Throwable caught) {
						SC.clearPrompt();
						Log.debug("onFailure() - "
								+ caught.getLocalizedMessage());
					}

					@Override
					public void onSuccess(RecuperarClienteResult result) {
						SC.clearPrompt();
						Log.debug("onSuccess()");
						getView().setCliente(result.getClienteVo());
					}
				});

	}

	@Override
	public void onButtonSalvarClicked(ClienteVo cliente) {
		if (cliente.getId() != null) {
			atualizarCliente(cliente);
		} else {
			cadastrarCliente(cliente);
		}
	}

	private void cadastrarCliente(ClienteVo cliente) {
		SC.showPrompt(ArteFinoOrderManager.getConstants().mensagemAguarde());
		dispatcher.execute(new CadastrarClienteAction(cliente),
				new AsyncCallback<CadastrarClienteResult>() {
					@Override
					public void onFailure(Throwable caught) {
						SC.clearPrompt();
						SC.warn(caught.getMessage());
					}

					@Override
					public void onSuccess(CadastrarClienteResult result) {
						SC.clearPrompt();
						SC.say(ArteFinoOrderManager.getMessages()
								.operacaoRealizadaComSucesso());
						getView().setIdCliente(result.getId());
					}
				});

	}

	private void atualizarCliente(final ClienteVo cliente) {
		SC.showPrompt(ArteFinoOrderManager.getConstants().mensagemAguarde());
		dispatcher.execute(new AtualizarClienteAction(cliente),
				new AsyncCallback<AtualizarClienteResult>() {
					@Override
					public void onFailure(Throwable caught) {
						SC.clearPrompt();
						SC.warn(caught.getMessage());
					}

					@Override
					public void onSuccess(AtualizarClienteResult result) {
						SC.clearPrompt();
						SC.say(ArteFinoOrderManager.getMessages()
								.clienteAtualizado(cliente.getNome()));
					}
				});

	}

	@Override
	public void onButtonVoltarClicked() {
		PlaceRequest placeRequest = new PlaceRequest(NameTokens.clientes);
		placeManager.revealPlace(placeRequest);
	}

}
