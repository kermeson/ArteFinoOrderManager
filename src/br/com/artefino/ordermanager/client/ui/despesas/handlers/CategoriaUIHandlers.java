package br.com.artefino.ordermanager.client.ui.despesas.handlers;

import com.gwtplatform.mvp.client.UiHandlers;

public interface CategoriaUIHandlers extends UiHandlers {

	void onButtonIncluirClicked();

	void onRemoverCategoriaClicked(int id);

}
