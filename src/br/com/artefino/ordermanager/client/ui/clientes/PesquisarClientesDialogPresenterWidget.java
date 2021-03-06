package br.com.artefino.ordermanager.client.ui.clientes;

import java.util.List;
import java.util.Map;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.ui.clientes.handlers.PesquisarClientesDialogUIHandlers;
import br.com.artefino.ordermanager.client.util.DefaultAsyncCallback;
import br.com.artefino.ordermanager.shared.action.clientes.PesquisarClientesAction;
import br.com.artefino.ordermanager.shared.action.clientes.PesquisarClientesResult;
import br.com.artefino.ordermanager.shared.vo.ClienteVo;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickEvent;

public class PesquisarClientesDialogPresenterWidget extends
		PresenterWidget<PesquisarClientesDialogPresenterWidget.MyView> implements PesquisarClientesDialogUIHandlers  {

	private DispatchAsync dispatcher;

	public interface MyView extends View, HasUiHandlers<PesquisarClientesDialogUIHandlers> {
		void exibirDialogo();

		void setResultSet(List<ClienteVo> clientes);

		void fecharDialogo();

		Map<String, Object> getParametrosPesquisa();
	}

	@Inject
	public PesquisarClientesDialogPresenterWidget(final EventBus eventBus,
			final MyView view, final
			DispatchAsync dispatcher) {
		super(eventBus, view);
		this.dispatcher = dispatcher;

		getView().setUiHandlers(this);
	}


	public void show() {
		getView().exibirDialogo();
	}

	@Override
	public void onButtonPesquisarClicked() {
		pesquisarClientes();
	}


	private void pesquisarClientes() {
		SC.showPrompt(ArteFinoOrderManager.getConstants().mensagemCarregando());
		dispatcher.execute(new PesquisarClientesAction(10, 1, getView()
				.getParametrosPesquisa()),
				new DefaultAsyncCallback<PesquisarClientesResult>() {
					@Override
					public void onSuccess(PesquisarClientesResult result) {
						super.onSuccess(result);
						getView().setResultSet(result.getClientes());
					}
				});
	}


	@Override
	public void onRecordSelecionarClicked(RecordClickEvent event) {
		// TODO Auto-generated method stub

	}


	@Override
	public void onRecordDoubleClicked(RecordDoubleClickEvent event) {
		// TODO Auto-generated method stub

	}

	public void fecharDialogo() {
		getView().fecharDialogo();
	}


}
