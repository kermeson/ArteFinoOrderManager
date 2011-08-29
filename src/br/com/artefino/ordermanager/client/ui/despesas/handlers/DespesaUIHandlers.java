package br.com.artefino.ordermanager.client.ui.despesas.handlers;

import br.com.artefino.ordermanager.shared.vo.DespesaVo;

import com.gwtplatform.mvp.client.UiHandlers;

public interface DespesaUIHandlers extends UiHandlers {

	void onButtonVoltarClicked();

	void onButtonSalvarClicked(DespesaVo despesa);

	void onButtonGerenciarCategoriasClicked();

}
