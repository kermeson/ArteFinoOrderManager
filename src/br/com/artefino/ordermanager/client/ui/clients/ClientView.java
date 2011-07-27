package br.com.artefino.ordermanager.client.ui.clients;

import java.util.List;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.model.ClienteRecord;
import br.com.artefino.ordermanager.client.ui.clients.handlers.ClientUIHandlers;
import br.com.artefino.ordermanager.client.ui.widgets.ToolBar;
import br.com.artefino.ordermanager.shared.vo.ClienteVo;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class ClientView extends ViewWithUiHandlers<ClientUIHandlers> implements
		ClientPresenter.MyView {

	private VLayout panel;
	private ToolBar toolBar;
	private ClientesListGrid clientesListGrid;
	private String idCliente;

	@Inject
	public ClientView(ClientesListGrid clientesListGrid) {
		panel = new VLayout();

		toolBar = new ToolBar();
		panel.addMember(toolBar);

		//
		this.clientesListGrid = clientesListGrid;
		panel.addMember(clientesListGrid);

		bindCustomUiHandlers();
	}

	protected void bindCustomUiHandlers() {

		// initialise the ToolBar and register its handlers
		initToolBar();

		// register the ListGird handlers
	    clientesListGrid.addRecordDoubleClickHandler(new RecordDoubleClickHandler() {
	      @Override
	      public void onRecordDoubleClick(RecordDoubleClickEvent event) {
	        Record record = event.getRecord();
	        idCliente = record.getAttributeAsString(ClienteRecord.ID);

	        if (getUiHandlers() != null) {
	          getUiHandlers().onRecordDoubleClicked(idCliente);
	        }
	      }
	    });
	}

	@Override
	public Widget asWidget() {
		return panel;
	}

	protected void initToolBar() {

		Log.debug("initToolBar()");

		toolBar.addButton(ToolBar.NEW_BUTTON, ArteFinoOrderManager
				.getConstants().newButton(), new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (getUiHandlers() != null) {
					getUiHandlers().onButtonNewClientClicked();
				}
			}
		});

		toolBar.addButton(ToolBar.DELETE_BUTTON, ArteFinoOrderManager
				.getConstants().deleteButtonTooltip(), new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (getUiHandlers() != null) {

			          ListGridRecord record = clientesListGrid.getSelectedRecord();

			          if (record != null) {

			            idCliente = record.getAttributeAsString(ClienteRecord.ID);

			            event.cancel();
			            SC.ask("Deseja remover este cliente?", new BooleanCallback()
			            {
			              @Override
			              public void execute(Boolean value) {
			                if (value != null && value) { // Yes
			                  getUiHandlers().onDeleteButtonClicked(idCliente);
			                }
			              }
			            });
			          } else {
			        	  clientesListGrid.deselectAllRecords();
			          }
			            }

			}
		});

		toolBar.addButton(ToolBar.PRINT_PREVIEW_BUTTON, ArteFinoOrderManager
				.getConstants().printPreviewButtonTooltip(),
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						// if (getUiHandlers() != null) {
						// // getUiHandlers().onPrintPreviewClicked();
						// }
					}
				});

		toolBar.addSeparator();

		toolBar.addButton(ToolBar.EMAIL_BUTTON, ArteFinoOrderManager
				.getConstants().emailButtonTooltip(), new ClickHandler() {
			public void onClick(ClickEvent event) {
				// if (getUiHandlers() != null) {
				// // getUiHandlers().onEmailButtonClicked();
				// }
			}
		});

	}

	@Override
	public void setResultSet(List<ClienteVo> clientes) {
		if (clientes != null) {
		      clientesListGrid.setResultSet(clientes);
		    }
	}

	@Override
	public void removerClienteSelecionado() {
		clientesListGrid.removeSelectedData();
	}
}
