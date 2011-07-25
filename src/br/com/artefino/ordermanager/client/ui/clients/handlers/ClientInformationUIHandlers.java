package br.com.artefino.ordermanager.client.ui.clients.handlers;

import br.com.artefino.ordermanager.shared.vo.ClienteVo;

import com.gwtplatform.mvp.client.UiHandlers;

public interface ClientInformationUIHandlers extends UiHandlers {

	void onButtonSalvarClicked(ClienteVo cliente);

	void onButtonVoltarClicked();
}
