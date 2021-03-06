package br.com.artefino.ordermanager.client.ui.clientes;

import java.util.List;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.LoggedInGatekeeper;
import br.com.artefino.ordermanager.client.place.NameTokens;
import br.com.artefino.ordermanager.client.ui.clientes.handlers.ClientesUIHandlers;
import br.com.artefino.ordermanager.client.ui.main.MainPagePresenter;
import br.com.artefino.ordermanager.client.util.DefaultAsyncCallback;
import br.com.artefino.ordermanager.shared.action.clientes.PesquisarClientesAction;
import br.com.artefino.ordermanager.shared.action.clientes.PesquisarClientesResult;
import br.com.artefino.ordermanager.shared.action.clientes.RemoverClienteAction;
import br.com.artefino.ordermanager.shared.action.clientes.RemoverClienteResult;
import br.com.artefino.ordermanager.shared.vo.ClienteVo;

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

public class ClientesPresenter extends
		Presenter<ClientesPresenter.MyView, ClientesPresenter.MyProxy>
		implements ClientesUIHandlers {

	public interface MyView extends View, HasUiHandlers<ClientesUIHandlers> {

		void setResultSet(List<ClienteVo> clientes);

		void removerClienteSelecionado();

	}

	@ProxyStandard
	@NameToken(NameTokens.clientes)
	@UseGatekeeper(LoggedInGatekeeper.class)
	public interface MyProxy extends ProxyPlace<ClientesPresenter> {
	}

	private static final String ACAO = "acao";

	private static final String EDITAR = "editar";

	private static final String ID = "id";

	private static final String NOVO = "novo";

	private PlaceManager placeManager;

	private DispatchAsync dispatcher;

	@Inject
	public ClientesPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, PlaceManager placeManager,
			DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
		this.dispatcher = dispatcher;

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
		pesquisarClientes();

		MainPagePresenter.getNavigationPaneHeader()
				.setContextAreaHeaderLabelContents(
						ArteFinoOrderManager.getConstants().tituloClientes());
	}

	@Override
	public void onButtonNewClientClicked() {
		PlaceRequest placeRequest = new PlaceRequest(
				NameTokens.clientinformation).with(ACAO, NOVO);
		placeManager.revealPlace(placeRequest);
	}

	private void pesquisarClientes() {
		SC.showPrompt(ArteFinoOrderManager.getConstants().mensagemCarregando());
		dispatcher.execute(new PesquisarClientesAction(10, 1, null),
				new DefaultAsyncCallback<PesquisarClientesResult>() {

					@Override
					public void onSuccess(PesquisarClientesResult result) {
						super.onSuccess(result);
						getView().setResultSet(result.getClientes());
					}
				});
	}

	@Override
	public void onRecordDoubleClicked(String idCliente) {
		PlaceRequest placeRequest = new PlaceRequest(
				NameTokens.clientinformation);
		placeRequest = placeRequest.with(ACAO, EDITAR).with(ID, idCliente);
		placeManager.revealPlace(placeRequest);
	}

	@Override
	public void onDeleteButtonClicked(String idCliente) {
		Long id = -1L;

		try {
			id = Long.valueOf(idCliente);
		} catch (NumberFormatException nfe) {
			Log.debug("NumberFormatException: " + nfe.getLocalizedMessage());
			return;
		}

		SC.showPrompt(ArteFinoOrderManager.getConstants().mensagemAguarde());
		dispatcher.execute(new RemoverClienteAction(id),
				new DefaultAsyncCallback<RemoverClienteResult>() {
					@Override
					public void onSuccess(RemoverClienteResult result) {
						super.onSuccess(result);
						getView().removerClienteSelecionado();
					}
				});

	}

	@Override
	public void onEditarButtonClicked(String idCliente) {
		PlaceRequest placeRequest = new PlaceRequest(
				NameTokens.clientinformation);
		placeRequest = placeRequest.with(ACAO, EDITAR).with(ID, idCliente);
		placeManager.revealPlace(placeRequest);
	}
}
