package br.com.artefino.ordermanager.client.ui.despesas.handlers;

import com.gwtplatform.mvp.client.UiHandlers;

public interface DespesasUIHandlers extends UiHandlers {
	void onButtonNewClientClicked();

	void onRecordDoubleClicked(String idCliente);

	void onDeleteButtonClicked(String idCliente);

	void onEditarButtonClicked(String idCliente);

	void pesquisarDespesas();
}
