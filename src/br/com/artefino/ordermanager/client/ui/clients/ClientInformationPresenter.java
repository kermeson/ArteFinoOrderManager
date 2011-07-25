package br.com.artefino.ordermanager.client.ui.clients;

import br.com.artefino.ordermanager.client.place.NameTokens;
import br.com.artefino.ordermanager.client.ui.clients.handlers.ClientInformationUIHandlers;
import br.com.artefino.ordermanager.client.ui.main.MainPagePresenter;
import br.com.artefino.ordermanager.shared.action.clientes.CadastrarCliente;
import br.com.artefino.ordermanager.shared.action.clientes.CadastrarClienteResult;
import br.com.artefino.ordermanager.shared.vo.ClienteVo;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.smartgwt.client.util.SC;

public class ClientInformationPresenter
		extends
		Presenter<ClientInformationPresenter.MyView, ClientInformationPresenter.MyProxy>
		implements ClientInformationUIHandlers {

	public interface MyView extends View,
			HasUiHandlers<ClientInformationUIHandlers> {
		// TODO Put your view methods here
	}

	@ProxyStandard
	@NameToken(NameTokens.clientinformation)
	public interface MyProxy extends ProxyPlace<ClientInformationPresenter> {
	}

	private DispatchAsync dispatcher;
	private PlaceManager placeManager;

	@Inject
	public ClientInformationPresenter(final EventBus eventBus,
			final MyView view, final MyProxy proxy, DispatchAsync dispatcher, PlaceManager placeManager) {
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
	public void onButtonSalvarClicked(ClienteVo cliente) {
		if (cliente.getId() != null) {
			atualizarCliente(cliente);
		} else {
			cadastrarCliente(cliente);
		}
	}

	private void cadastrarCliente(ClienteVo cliente) {
		dispatcher.execute(new CadastrarCliente(cliente),
				new AsyncCallback<CadastrarClienteResult>() {
					@Override
					public void onFailure(Throwable caught) {
						SC.warn(caught.getMessage());
					}

					@Override
					public void onSuccess(CadastrarClienteResult result) {
						SC.say("IdCliente" + result.getId());
					}
				});

	}

	private void atualizarCliente(ClienteVo cliente) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onButtonVoltarClicked() {
		PlaceRequest placeRequest = new PlaceRequest(NameTokens.clientes);
		placeManager.revealPlace(placeRequest);		
	}

}
