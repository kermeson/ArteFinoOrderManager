package br.com.artefino.ordermanager.client.ui.pedidos;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.ui.pedidos.handlers.PedidosUIHandlers;
import br.com.artefino.ordermanager.client.ui.widgets.ToolBar;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class PedidosView extends ViewWithUiHandlers<PedidosUIHandlers> implements PedidosPresenter.MyView {

	private VLayout panel;
	private ToolBar toolBar;
	private String idPedido;

	@Inject
	public PedidosView(ToolBar toolBar) {
		panel = new VLayout();
		this.toolBar = toolBar;
		panel.addMember(this.toolBar);
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

		toolBar.addButton(ToolBar.ADD_CLIENT, ArteFinoOrderManager
				.getConstants().newButton(), new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (getUiHandlers() != null) {
					getUiHandlers().onButtonAdicionarPedidoClicked();
				}
			}
		});

	}
}
