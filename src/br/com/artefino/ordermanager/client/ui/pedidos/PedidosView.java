package br.com.artefino.ordermanager.client.ui.pedidos;

import java.util.List;
import java.util.Map;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.model.ClienteRecord;
import br.com.artefino.ordermanager.client.ui.pedidos.handlers.PedidosUIHandlers;
import br.com.artefino.ordermanager.client.ui.widgets.ToolBar;
import br.com.artefino.ordermanager.shared.vo.ClienteVo;
import br.com.artefino.ordermanager.shared.vo.PedidoVo;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.types.SelectionType;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.FormItemClickHandler;
import com.smartgwt.client.widgets.form.fields.events.FormItemIconClickEvent;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;

public class PedidosView extends ViewWithUiHandlers<PedidosUIHandlers>
		implements PedidosPresenter.MyView {

	private VLayout panel;
	private ToolBar toolBar;
	private String idPedido;
	private PedidosListGrid pedidosListGrid;
	private PesquisarPedidosDynamicForm form;

	@Inject
	public PedidosView(ToolBar toolBar, PedidosListGrid pedidosListGrid, PesquisarPedidosDynamicForm form) {
		panel = new VLayout(5);

		this.toolBar = toolBar;
		this.form = form;

		panel.addMember(this.toolBar);

		form.setVisible(false);
		panel.addMember(form);

		this.pedidosListGrid = pedidosListGrid;
		panel.addMember(pedidosListGrid);
		bindCustomUiHandlers();
	}

	protected void bindCustomUiHandlers() {

		form.addPesquisarClienteFormItemClickHandler(new FormItemClickHandler() {
			@Override
			public void onFormItemClick(FormItemIconClickEvent event) {
				if (getUiHandlers() != null) {
					getUiHandlers().onButtonPesquisarClientesClicked();
				}
			}
		});

		form.addButtonPesquisarClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (getUiHandlers() != null) {
					getUiHandlers().onButtonPesquisarClicked();
				}
			}
		});

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

		toolBar.addButton(ToolBar.PESQUISAR, ArteFinoOrderManager
				.getConstants().newButton(), SelectionType.CHECKBOX, new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (form.isVisible()) {
					form.setVisible(false);
				} else {
					form.setVisible(true);
				}
			}
		});
	}

	@Override
	public void setResultSet(List<PedidoVo> pedidosVo) {
		pedidosListGrid.setResultSet(pedidosVo);
	}

	@Override
	public Map<String, Object> getParametrosPesquisa() {
		return form.getParametrosPesquisa();
	}

	@Override
	public void setCliente(ClienteVo clienteVo) {
		form.setCliente(clienteVo);
	}
}
