package br.com.artefino.ordermanager.client.ui.clientes.handlers;

import com.gwtplatform.mvp.client.UiHandlers;

public interface ClientesUIHandlers extends UiHandlers {
	void onButtonNewClientClicked();

	void onRecordDoubleClicked(String idCliente);

	void onDeleteButtonClicked(String idCliente);

	void onEditarButtonClicked(String idCliente);
}
