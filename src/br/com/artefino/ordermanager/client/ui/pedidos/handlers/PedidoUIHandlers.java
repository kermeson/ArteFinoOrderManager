package br.com.artefino.ordermanager.client.ui.pedidos.handlers;

import br.com.artefino.ordermanager.shared.vo.PedidoVo;

import com.gwtplatform.mvp.client.UiHandlers;

public interface PedidoUIHandlers extends UiHandlers {
	void onButtonAdicionarPedidoClicked();

	void onButtonVoltarClicked();

	void onButtonPesquisarClientesClicked();

	void onButtonSalvarPedido(PedidoVo pedido);

	void onButtonImprimirPedido(Long idPedido);
}
