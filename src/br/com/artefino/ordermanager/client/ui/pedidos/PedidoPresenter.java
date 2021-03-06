package br.com.artefino.ordermanager.client.ui.pedidos;

import java.util.List;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.LoggedInGatekeeper;
import br.com.artefino.ordermanager.client.model.ClienteRecord;
import br.com.artefino.ordermanager.client.place.NameTokens;
import br.com.artefino.ordermanager.client.ui.clientes.PesquisarClientesDialogPresenterWidget;
import br.com.artefino.ordermanager.client.ui.clientes.PesquisarClientesDialogView;
import br.com.artefino.ordermanager.client.ui.main.MainPagePresenter;
import br.com.artefino.ordermanager.client.ui.pedidos.handlers.PedidoUIHandlers;
import br.com.artefino.ordermanager.client.util.DefaultAsyncCallback;
import br.com.artefino.ordermanager.shared.action.RecuperarSituacoesPedidoAction;
import br.com.artefino.ordermanager.shared.action.RecuperarSituacoesPedidoResult;
import br.com.artefino.ordermanager.shared.action.pedidos.AtualizarPedidoAction;
import br.com.artefino.ordermanager.shared.action.pedidos.AtualizarPedidoResult;
import br.com.artefino.ordermanager.shared.action.pedidos.CadastrarPedidoAction;
import br.com.artefino.ordermanager.shared.action.pedidos.CadastrarPedidoResult;
import br.com.artefino.ordermanager.shared.action.pedidos.RecuperarPedidoAction;
import br.com.artefino.ordermanager.shared.action.pedidos.RecuperarPedidoResult;
import br.com.artefino.ordermanager.shared.vo.ClienteVo;
import br.com.artefino.ordermanager.shared.vo.PedidoVo;
import br.com.artefino.ordermanager.shared.vo.SituacaoPedidoVo;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
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
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;

public class PedidoPresenter extends
		Presenter<PedidoPresenter.MyView, PedidoPresenter.MyProxy> implements
		PedidoUIHandlers {

	private PlaceManager placeManager;
	private DispatchAsync dispatcher;
	private EventBus eventBus;

	private static final String ACAO = "acao";
	private static final String ID = "id";
	private static final String EDITAR = "editar";
	private static final String NOVO = "novo";
	private String idPedido;
	private String acao;

	public interface MyView extends View, HasUiHandlers<PedidoUIHandlers> {
		void setCliente(ClienteVo clienteVo);

		void limparTelaCadastro();

		void setSituacoes(List<SituacaoPedidoVo> situacaoPedidoVos);

		void setIdPedido(Long id);

		void setPedido(PedidoVo pedido);
	}

	@ProxyStandard
	@NameToken(NameTokens.pedido)
	@UseGatekeeper(LoggedInGatekeeper.class)
	public interface MyProxy extends ProxyPlace<PedidoPresenter> {
	}

	@Inject
	public PedidoPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final PlaceManager placeManager,
			final DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.eventBus = eventBus;
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

	private void recuperarSituacoesPedidos() {
		dispatcher.execute(new RecuperarSituacoesPedidoAction(),
				new DefaultAsyncCallback<RecuperarSituacoesPedidoResult>() {

					@Override
					public void onSuccess(RecuperarSituacoesPedidoResult result) {
						super.onSuccess(result);
						getView().setSituacoes(result.getSituacaoPedidoVos());
					}
				});

	}

	@Override
	protected void onReveal() {
		super.onReveal();

		MainPagePresenter.getNavigationPaneHeader()
				.setContextAreaHeaderLabelContents(
						ArteFinoOrderManager.getConstants()
								.tituloDetalhesPedido());

		recuperarSituacoesPedidos();

		PlaceRequest placeRequest = placeManager.getCurrentPlaceRequest();
		acao = placeRequest.getParameter(ACAO, NOVO);
		idPedido = placeRequest.getParameter(ID, null);
		if (EDITAR.equals(acao) && idPedido != null) {
			if (ArteFinoOrderManager.getCurrentUser().isAdministrator()) {
				Long id = -1L;
				try {
					id = Long.valueOf(idPedido);
				} catch (NumberFormatException nfe) {
					Log.debug("NumberFormatException: "
							+ nfe.getLocalizedMessage());
					return;
				}
				recuperarPedido(id);
			} else {
				SC.warn(ArteFinoOrderManager.getMessages()
						.usuariosemPermissao());
			}

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
		PesquisarClientesDialogPresenterWidget dialogBox = new PesquisarClientesDialogPresenterWidget(
				eventBus, new PesquisarClientesDialogView(), dispatcher) {
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
				new DefaultAsyncCallback<CadastrarPedidoResult>() {

					@Override
					public void onSuccess(CadastrarPedidoResult result) {
						super.onSuccess(result);
						SC.say(ArteFinoOrderManager.getMessages()
								.operacaoRealizadaComSucesso());
						getView().setIdPedido(result.getId());
					}
				});

	}

	private void atualizarPedido(PedidoVo pedido) {
		SC.showPrompt(ArteFinoOrderManager.getConstants().mensagemAguarde());
		dispatcher.execute(new AtualizarPedidoAction(pedido),
				new DefaultAsyncCallback<AtualizarPedidoResult>() {
					@Override
					public void onSuccess(AtualizarPedidoResult result) {
						super.onSuccess(result);
						SC.say(ArteFinoOrderManager.getMessages()
								.operacaoRealizadaComSucesso());
					}
				});

	}

	@Override
	public void onButtonImprimirPedido(Long idPedido) {
		StringBuilder url = new StringBuilder();
		url.append(GWT.getHostPageBaseURL() + "reports/?report=pedido&id=" + idPedido);
		Window.open(url.toString(), "_blank", "");

	}

	private void recuperarPedido(Long id) {
		SC.showPrompt(ArteFinoOrderManager.getConstants().mensagemAguarde());
		dispatcher.execute(new RecuperarPedidoAction(id),
				new DefaultAsyncCallback<RecuperarPedidoResult>() {

					@Override
					public void onSuccess(RecuperarPedidoResult result) {
						super.onSuccess(result);
						getView().setPedido(result.getPedido());
					}
				});

	}
}
