package br.com.artefino.ordermanager.client.ui.despesas;

import java.util.List;
import java.util.Map;

import br.com.artefino.ordermanager.client.ui.despesas.handlers.FormularioPesquisarDespesasUIHandlers;
import br.com.artefino.ordermanager.shared.action.despesas.categorias.PesquisarCategoriasDespesaAction;
import br.com.artefino.ordermanager.shared.action.despesas.categorias.PesquisarCategoriasDespesaResult;
import br.com.artefino.ordermanager.shared.vo.CategoriaDespesaVo;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickHandler;

public class FormularioPesquisarDespesasPresenterWidget extends
		PresenterWidget<FormularioPesquisarDespesasPresenterWidget.MyView>
		implements FormularioPesquisarDespesasUIHandlers {

	private DispatchAsync dispatcher;

	public interface MyView extends View,
			HasUiHandlers<FormularioPesquisarDespesasUIHandlers> {		

		void addButtonExportarClickHandler(ClickHandler clickHandler);

		Map<String, Object> getParametrosPesquisa();		

		void addButtonPesquisarClickHandler(ClickHandler clickHandler);

		void setCategorias(List<CategoriaDespesaVo> categorias);
	}

	@Inject
	public FormularioPesquisarDespesasPresenterWidget(final EventBus eventBus,
			final MyView view, final DispatchAsync dispatcher) {
		super(eventBus, view);
		this.dispatcher = dispatcher;
		getView().setUiHandlers(this);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		
		dispatcher.execute(new PesquisarCategoriasDespesaAction(1, 10),
				new AsyncCallback<PesquisarCategoriasDespesaResult>() {

					@Override
					public void onFailure(Throwable caught) {
						SC.warn(caught.getMessage());
					}

					@Override
					public void onSuccess(PesquisarCategoriasDespesaResult result) {
						if (result.getCategorias() != null) {
							getView().setCategorias(
									result.getCategorias());
						}
					}
				});
	}

	public void addButtonExportarClickHandler(ClickHandler clickHandler) {
		getView().addButtonExportarClickHandler(clickHandler);
	}

	public void addButtonPesquisarClickHandler(ClickHandler clickHandler) {
		getView().addButtonPesquisarClickHandler(clickHandler);
	}

	public Map<String, Object> getParametrosPesquisa() {
		return getView().getParametrosPesquisa();
	}	

}
