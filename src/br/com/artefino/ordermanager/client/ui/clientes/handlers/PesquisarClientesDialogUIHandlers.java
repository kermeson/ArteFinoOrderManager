package br.com.artefino.ordermanager.client.ui.clientes.handlers;

import com.gwtplatform.mvp.client.UiHandlers;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickEvent;

public interface PesquisarClientesDialogUIHandlers extends UiHandlers {

	void onButtonPesquisarClicked();

	void onRecordSelecionarClicked(RecordClickEvent event);

	void onRecordDoubleClicked(RecordDoubleClickEvent event);

}
