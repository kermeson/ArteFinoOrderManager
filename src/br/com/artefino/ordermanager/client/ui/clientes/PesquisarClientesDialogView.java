package br.com.artefino.ordermanager.client.ui.clientes;

import com.gwtplatform.mvp.client.PopupViewImpl;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.VLayout;

public class PesquisarClientesDialogView extends PopupViewImpl implements
		PesquisarClientesDialogPresenterWidget.MyView {

	private Window panel;

	@Inject
	public PesquisarClientesDialogView(final EventBus eventBus) {
		super(eventBus);

		panel = new Window();

	}

	@Override
	public Widget asWidget() {
		return panel;
	}
}
