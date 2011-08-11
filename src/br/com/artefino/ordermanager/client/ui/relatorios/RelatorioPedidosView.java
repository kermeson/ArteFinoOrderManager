package br.com.artefino.ordermanager.client.ui.relatorios;

import java.util.Map;

import br.com.artefino.ordermanager.client.ui.relatorios.handlers.RelatorioPedidosUIHandlers;
import br.com.artefino.ordermanager.client.ui.widgets.FormularioPesquisarPedidos;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class RelatorioPedidosView extends
		ViewWithUiHandlers<RelatorioPedidosUIHandlers> implements
		RelatorioPedidosPresenter.MyView {

	private VLayout panel;
	private FormularioPesquisarPedidos form;

	@Inject
	public RelatorioPedidosView(FormularioPesquisarPedidos form) {
		panel = new VLayout(5);

		this.form = form;
		form.setHeight100();
		panel.addMember(form);

		bindCustomUiHandlers();
	}

	protected void bindCustomUiHandlers() {

		form.addButtonPesquisarClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (getUiHandlers() != null) {
					getUiHandlers().onButtonExportarClicked();
				}
			}
		});

	}

	@Override
	public Widget asWidget() {
		return panel;
	}


	@Override
	public Map<String, Object> getParametrosPesquisa() {
		return form.getParametrosPesquisa();
	}


}
