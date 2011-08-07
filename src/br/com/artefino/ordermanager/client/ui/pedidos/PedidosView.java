package br.com.artefino.ordermanager.client.ui.pedidos;

import java.util.List;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.model.ClienteRecord;
import br.com.artefino.ordermanager.client.ui.pedidos.handlers.PedidosUIHandlers;
import br.com.artefino.ordermanager.client.ui.widgets.ToolBar;
import br.com.artefino.ordermanager.shared.vo.PedidoVo;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;

public class PedidosView extends ViewWithUiHandlers<PedidosUIHandlers>
		implements PedidosPresenter.MyView {

	private VLayout panel;
	private ToolBar toolBar;
	private String idPedido;
	private PedidosListGrid pedidosListGrid;

	@Inject
	public PedidosView(ToolBar toolBar, PedidosListGrid pedidosListGrid) {
		panel = new VLayout(5);

		this.toolBar = toolBar;
		panel.addMember(this.toolBar);

		this.pedidosListGrid = pedidosListGrid;
		panel.addMember(pedidosListGrid);
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

		toolBar.addButton(ToolBar.ADD_ORDER, ArteFinoOrderManager
				.getConstants().newButton(), new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (getUiHandlers() != null) {
					getUiHandlers().onButtonAdicionarPedidoClicked();
				}
			}
		});

		toolBar.addButton(ToolBar.PRINT_PREVIEW_BUTTON, ArteFinoOrderManager
				.getConstants().imprimir(), new ClickHandler() {
			public void onClick(ClickEvent event) {

				if (getUiHandlers() != null) {
					ListGridRecord record = pedidosListGrid.getSelectedRecord();

					if (record != null) {
						idPedido = record
								.getAttributeAsString(ClienteRecord.ID);
						event.cancel();
						getUiHandlers().onButtonImprimirPedido(idPedido);
					} else {
						pedidosListGrid.deselectAllRecords();
					}
				}

			}
		});
	}

	@Override
	public void setResultSet(List<PedidoVo> pedidosVo) {
		pedidosListGrid.setResultSet(pedidosVo);
	}
}
