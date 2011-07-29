package br.com.artefino.ordermanager.client.ui.pedidos;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.ui.pedidos.handlers.PedidoUIHandlers;
import br.com.artefino.ordermanager.client.ui.widgets.ToolBar;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class PedidoView extends ViewWithUiHandlers<PedidoUIHandlers> implements
		PedidoPresenter.MyView {

	private VLayout panel;
	private ToolBar toolBar;
	private String idPedido;

	@Inject
	public PedidoView(ToolBar toolBar) {
		panel = new VLayout();
		this.toolBar = toolBar;
		panel.addMember(toolBar);
		bindCustomUiHandlers();
	}

	protected void bindCustomUiHandlers() {
		// initialise the ToolBar and register its handlers
		initToolBar();
	}

	@Override
	public Widget asWidget() {
		return panel;
	}

	protected void initToolBar() {

		Log.debug("initToolBar()");

		toolBar.addButton(ToolBar.BACK_BUTTON, ArteFinoOrderManager
				.getConstants().voltar(), new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (getUiHandlers() != null) {
					getUiHandlers().onButtonVoltarClicked();
				}
			}
		});

		toolBar.addButton(ToolBar.SAVE_BUTTON, ArteFinoOrderManager
				.getConstants().salvar(), new ClickHandler() {
			public void onClick(ClickEvent event) {
				// if (validarCadastroCliente()) {
				if (getUiHandlers() != null) {
					// getUiHandlers().onButtonSalvarClicked(getCliente());
				}
				// }
			}
		});

	}
}
