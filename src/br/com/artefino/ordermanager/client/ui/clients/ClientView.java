package br.com.artefino.ordermanager.client.ui.clients;

import java.util.List;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.ui.clients.handlers.ClientUIHandlers;
import br.com.artefino.ordermanager.client.ui.widgets.ToolBar;
import br.com.artefino.ordermanager.shared.vo.ClienteVo;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class ClientView extends ViewWithUiHandlers<ClientUIHandlers> implements
		ClientPresenter.MyView {

	private VLayout panel;
	private ToolBar toolBar;
	private ClientesListGrid clientesListGrid;

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
				// if (validateTabs()) {
				// if (getUiHandlers() != null) {
				// Log.debug("onSaveAndCloseButtonClicked()");
				// getUiHandlers().onSaveAndCloseButtonClicked(
				// getFields());
				// }
				// }
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
}
