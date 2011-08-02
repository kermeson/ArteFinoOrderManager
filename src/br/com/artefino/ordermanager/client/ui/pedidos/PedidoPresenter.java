package br.com.artefino.ordermanager.client.ui.pedidos;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.model.ClienteRecord;
import br.com.artefino.ordermanager.client.place.NameTokens;
import br.com.artefino.ordermanager.client.ui.clientes.PesquisarClientesDialogPresenterWidget;
import br.com.artefino.ordermanager.client.ui.clientes.PesquisarClientesDialogView;
import br.com.artefino.ordermanager.client.ui.main.MainPagePresenter;
import br.com.artefino.ordermanager.client.ui.pedidos.handlers.PedidoUIHandlers;
import br.com.artefino.ordermanager.shared.vo.ClienteVo;

import com.google.gwt.event.shared.EventBus;
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
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;

public class PedidoPresenter extends
		Presenter<PedidoPresenter.MyView, PedidoPresenter.MyProxy> implements PedidoUIHandlers {

	private PlaceManager placeManager;
	private final PesquisarClientesDialogPresenterWidget dialogBox;
	private DispatchAsync dispatcher;

	public interface MyView extends View, HasUiHandlers<PedidoUIHandlers> {
		void setCliente(ClienteVo clienteVo);
	}

	@ProxyStandard
	@NameToken(NameTokens.pedido)
	public interface MyProxy extends ProxyPlace<PedidoPresenter> {
	}



	@Inject
	public PedidoPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final PlaceManager placeManager, final DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
		this.dispatcher = dispatcher;

		this.dialogBox = new PesquisarClientesDialogPresenterWidget(eventBus, new PesquisarClientesDialogView(), dispatcher) {
			public void onRecordSelecionarClicked(RecordClickEvent event) {
				ClienteRecord clienteRecord = (ClienteRecord) event.getRecord();
				if (clienteRecord != null) {
					ClienteVo clienteVo = new ClienteVo();
					clienteVo.setId(Long.valueOf(clienteRecord.getId()));
					clienteVo.setNome(clienteRecord.getNome());
					PedidoPresenter.this.getView().setCliente(clienteVo);
				}
				destroy();
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
