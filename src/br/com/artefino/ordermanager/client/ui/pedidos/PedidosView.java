package br.com.artefino.ordermanager.client.ui.pedidos;

import br.com.artefino.ordermanager.client.ui.widgets.ToolBar;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.smartgwt.client.widgets.layout.VLayout;

public class PedidosView extends ViewImpl implements PedidosPresenter.MyView {

	private VLayout panel;
	private ToolBar toolBar;
	private String idPedido;

	@Inject
	public PedidosView(ToolBar toolBar) {
		panel = new VLayout();
		panel.addMember(toolBar);
	}

	@Override
	public Widget asWidget() {
		return panel;
	}
}
