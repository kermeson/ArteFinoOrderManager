package br.com.artefino.ordermanager.client.ui.clients.handlers;

import com.gwtplatform.mvp.client.UiHandlers;

public interface ClientUIHandlers extends UiHandlers {
	void onButtonNewClientClicked();

	void onRecordDoubleClicked(String idCliente);

	void onDeleteButtonClicked(String idCliente);

	void onEditarButtonClicked(String idCliente);
}
