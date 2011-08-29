package br.com.artefino.ordermanager.client.ui.clientes.handlers;

import br.com.artefino.ordermanager.shared.vo.ClienteVo;

import com.gwtplatform.mvp.client.UiHandlers;

public interface ClienteUIHandlers extends UiHandlers {

	void onButtonSalvarClicked(ClienteVo cliente);

	void onButtonVoltarClicked();
}
