package br.com.artefino.ordermanager.client.ui.clients;

import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.NameToken;
import br.com.artefino.ordermanager.client.place.NameTokens;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import br.com.artefino.ordermanager.client.ui.clients.handlers.ClientInformationUIHandlers;
import br.com.artefino.ordermanager.client.ui.main.MainPagePresenter;
import br.com.artefino.ordermanager.shared.vo.ClienteVo;

public class ClientInformationPresenter
		extends
		Presenter<ClientInformationPresenter.MyView, ClientInformationPresenter.MyProxy>
		implements ClientInformationUIHandlers {

	public interface MyView extends View {
		// TODO Put your view methods here
	}

	@ProxyStandard
	@NameToken(NameTokens.clientinformation)
	public interface MyProxy extends ProxyPlace<ClientInformationPresenter> {
	}

	@Inject
	public ClientInformationPresenter(final EventBus eventBus,
			final MyView view, final MyProxy proxy) {
		super(eventBus, view, proxy);
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
		// TODO Auto-generated method stub

	}

	private void atualizarCliente(ClienteVo cliente) {
		// TODO Auto-generated method stub

	}


}
