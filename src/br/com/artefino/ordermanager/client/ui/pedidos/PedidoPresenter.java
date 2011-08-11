package br.com.artefino.ordermanager.client.ui.pedidos;

import java.util.List;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.model.ClienteRecord;
import br.com.artefino.ordermanager.client.place.NameTokens;
import br.com.artefino.ordermanager.client.ui.clientes.PesquisarClientesDialogPresenterWidget;
import br.com.artefino.ordermanager.client.ui.clientes.PesquisarClientesDialogView;
import br.com.artefino.ordermanager.client.ui.main.MainPagePresenter;
import br.com.artefino.ordermanager.client.ui.pedidos.handlers.PedidoUIHandlers;
import br.com.artefino.ordermanager.shared.action.RecuperarSituacoesPedidoAction;
import br.com.artefino.ordermanager.shared.action.RecuperarSituacoesPedidoResult;
import br.com.artefino.ordermanager.shared.action.pedidos.CadastrarPedidoAction;
import br.com.artefino.ordermanager.shared.action.pedidos.CadastrarPedidoResult;
import br.com.artefino.ordermanager.shared.vo.ClienteVo;
import br.com.artefino.ordermanager.shared.vo.PedidoVo;
import br.com.artefino.ordermanager.shared.vo.SituacaoPedidoVo;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
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
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;

public class PedidoPresenter extends
		Presenter<PedidoPresenter.MyView, PedidoPresenter.MyProxy> implements PedidoUIHandlers {

	private PlaceManager placeManager;
	private DispatchAsync dispatcher;
	private EventBus eventBus;

	private static final String ACAO = "acao";
	private static final String ID = "id";
	private static final String EDITAR = "editar";
	private static final String NOVO = "novo";
	private String idCliente;
	private String acao;

	public interface MyView extends View, HasUiHandlers<PedidoUIHandlers> {
		void setCliente(ClienteVo clienteVo);
		void limparTelaCadastro();
		void setSituacoes(List<SituacaoPedidoVo> situacaoPedidoVos);
		void setIdPedido(Long id);
	}

	@ProxyStandard
	@NameToken(NameTokens.pedido)
	public interface MyProxy extends ProxyPlace<PedidoPresenter> {
	}



	@Inject
	public PedidoPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final PlaceManager placeManager, final DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.eventBus = eventBus;
		this.placeManager = placeManager;
		this.dispatcher = dispatcher;

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

	private void recuperarSituacoesPedidos() {
		dispatcher.execute(new RecuperarSituacoesPedidoAction(), new AsyncCallback<RecuperarSituacoesPedidoResult>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(RecuperarSituacoesPedidoResult result) {
				getView().setSituacoes(result.getSituacaoPedidoVos());
			}
		});

	}

	@Override
	protected void onReveal() {
		super.onReveal();

		MainPagePresenter.getNavigationPaneHeader()
		.setContextAreaHeaderLabelContents(
				ArteFinoOrderManager.getConstants().tituloDetalhesPedido());


		recuperarSituacoesPedidos();

		PlaceRequest placeRequest = placeManager.getCurrentPlaceRequest();
		acao = placeRequest.getParameter(ACAO, NOVO);
		idCliente = placeRequest.getParameter(ID, null);
		if (EDITAR.equals(acao)) {
//			Long id = -1L;
//
//			try {
//				id = Long.valueOf(idCliente);
//			} catch (NumberFormatException nfe) {
//				Log
//						.debug("NumberFormatException: "
//								+ nfe.getLocalizedMessage());
//				return;
//			}
//			recuperarCliente(id);
		} else if (NOVO.equals(acao)) {
			getView().limparTelaCadastro();
		}
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
		PesquisarClientesDialogPresenterWidget dialogBox = new PesquisarClientesDialogPresenterWidget(eventBus, new PesquisarClientesDialogView(), dispatcher) {
			public void onRecordSelecionarClicked(RecordClickEvent event) {
				ClienteRecord clienteRecord = (ClienteRecord) event.getRecord();
				if (clienteRecord != null) {
					ClienteVo clienteVo = new ClienteVo();
					clienteVo.setId(Long.valueOf(clienteRecord.getId()));
					clienteVo.setNome(clienteRecord.getNome());
					PedidoPresenter.this.getView().setCliente(clienteVo);
				}
				fecharDialogo();
			}

		};
		dialogBox.show();
	}

	@Override
	public void onButtonSalvarPedido(PedidoVo pedido) {
		if (pedido.getId() != null) {
			atualizarPedido(pedido);
		} else {
			cadastrarPedido(pedido);
		}
	}

	private void cadastrarPedido(PedidoVo pedido) {
		SC.showPrompt(ArteFinoOrderManager.getConstants().mensagemAguarde());
		dispatcher.execute(new CadastrarPedidoAction(pedido),
				new AsyncCallback<CadastrarPedidoResult>() {
					@Override
					public void onFailure(Throwable caught) {
						SC.clearPrompt();
						SC.warn(caught.getMessage());
					}

					@Override
					public void onSuccess(CadastrarPedidoResult result) {
						SC.clearPrompt();
						SC.say(ArteFinoOrderManager.getMessages()
								.operacaoRealizadoComSucesso());
						getView().limparTelaCadastro();
					}
				});

	}

	private void atualizarPedido(PedidoVo pedido) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onButtonImprimirPedido(Long idPedido) {
		StringBuilder url = new StringBuilder();
	    url.append("/reports/?report=pedido&id=" + idPedido);
	    Window.open(url.toString(), "_blank", "");

	}
}
