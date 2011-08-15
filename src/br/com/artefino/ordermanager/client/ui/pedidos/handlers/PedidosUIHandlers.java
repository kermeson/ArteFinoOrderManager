package br.com.artefino.ordermanager.client.ui.pedidos.handlers;

import com.gwtplatform.mvp.client.UiHandlers;

public interface PedidosUIHandlers extends UiHandlers {
	void onButtonAdicionarPedidoClicked();

	void onButtonImprimirPedido(String idPedido);

	void onEditarButtonClicked(String idPedido);
}
