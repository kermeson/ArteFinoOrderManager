package br.com.artefino.ordermanager.client.ui.relatorios;

import br.com.artefino.ordermanager.client.ui.relatorios.handlers.RelatorioPedidosUIHandlers;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.widgets.layout.VLayout;

public class RelatorioPedidosView extends
		ViewWithUiHandlers<RelatorioPedidosUIHandlers> implements
		RelatorioPedidosPresenter.MyView {

	private VLayout panel;

	@Inject
	public RelatorioPedidosView() {
		panel = new VLayout(5);	
	}

	@Override
	public Widget asWidget() {
		return panel;
	}

	@Override
	public void addToSlot(Object slot, Widget content) {
		if (slot == RelatorioPedidosPresenter.TYPE_SetContextAreaContent) {
			if (content != null) {
				panel.setMembers((VLayout) content);
			}
		} else {
			super.setInSlot(slot, content);
		}
	}


}
