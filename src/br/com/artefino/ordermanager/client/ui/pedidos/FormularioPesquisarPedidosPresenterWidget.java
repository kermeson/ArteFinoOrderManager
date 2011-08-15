package br.com.artefino.ordermanager.client.ui.pedidos;

import java.util.List;
import java.util.Map;

import br.com.artefino.ordermanager.client.model.ClienteRecord;
import br.com.artefino.ordermanager.client.ui.clientes.PesquisarClientesDialogPresenterWidget;
import br.com.artefino.ordermanager.client.ui.clientes.PesquisarClientesDialogView;
import br.com.artefino.ordermanager.client.ui.pedidos.handlers.FormularioPesquisarPedidosUIHandlers;
import br.com.artefino.ordermanager.shared.action.RecuperarSituacoesPedidoAction;
import br.com.artefino.ordermanager.shared.action.RecuperarSituacoesPedidoResult;
import br.com.artefino.ordermanager.shared.vo.ClienteVo;
import br.com.artefino.ordermanager.shared.vo.SituacaoPedidoVo;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;

public class FormularioPesquisarPedidosPresenterWidget extends
		PresenterWidget<FormularioPesquisarPedidosPresenterWidget.MyView>
		implements FormularioPesquisarPedidosUIHandlers {

	private DispatchAsync dispatcher;
	private EventBus eventBus;

	public interface MyView extends View,
			HasUiHandlers<FormularioPesquisarPedidosUIHandlers> {
		void setCliente(ClienteVo clienteVo);

		void setItensSituacao(List<SituacaoPedidoVo> situacaoPedidoVos);

		void addButtonExportarClickHandler(ClickHandler clickHandler);

		Map<String, Object> getParametrosPesquisa();

		void exibirBotaoExportar(boolean b);

		void mostrarBotaoPesquisar(boolean b);

		void addButtonPesquisarClickHandler(ClickHandler clickHandler);
	}

	@Inject
	public FormularioPesquisarPedidosPresenterWidget(final EventBus eventBus,
			final MyView view, final DispatchAsync dispatcher) {
		super(eventBus, view);
		this.eventBus = eventBus;
		this.dispatcher = dispatcher;
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		dispatcher.execute(new RecuperarSituacoesPedidoAction(),
				new AsyncCallback<RecuperarSituacoesPedidoResult>() {

					@Override
					public void onFailure(Throwable caught) {
						SC.warn(caught.getMessage());
					}

					@Override
					public void onSuccess(RecuperarSituacoesPedidoResult result) {
						if (result.getSituacaoPedidoVos() != null) {
							getView().setItensSituacao(
									result.getSituacaoPedidoVos());
						}
					}
				});
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
					FormularioPesquisarPedidosPresenterWidget.this.getView()
							.setCliente(clienteVo);
				}
				fecharDialogo();
			}

		};
		dialogBox.show();
	}

	public void addButtonExportarClickHandler(ClickHandler clickHandler) {
		getView().addButtonExportarClickHandler(clickHandler);
	}

	public void addButtonPesquisarClickHandler(ClickHandler clickHandler) {
		getView().addButtonPesquisarClickHandler(clickHandler);
	}

	public Map<String, Object> getParametrosPesquisa() {
		return getView().getParametrosPesquisa();
	}

	public void exibirBotaoExportar(boolean b) {
		getView().exibirBotaoExportar(b);

	}

	public void mostrarBotaoPesquisar(boolean b) {
		getView().mostrarBotaoPesquisar(b);

	}

}
