package br.com.artefino.ordermanager.client.ui.despesas;

import java.util.List;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.ui.despesas.handlers.CategoriaUIHandlers;
import br.com.artefino.ordermanager.shared.action.despesas.categorias.CadastrarCategoriaDespesaAction;
import br.com.artefino.ordermanager.shared.action.despesas.categorias.CadastrarCategoriaDespesaResult;
import br.com.artefino.ordermanager.shared.action.despesas.categorias.PesquisarCategoriasDespesaAction;
import br.com.artefino.ordermanager.shared.action.despesas.categorias.PesquisarCategoriasDespesaResult;
import br.com.artefino.ordermanager.shared.action.despesas.categorias.RemoverCategoriaDespesaAction;
import br.com.artefino.ordermanager.shared.action.despesas.categorias.RemoverCategoriaDespesaResult;
import br.com.artefino.ordermanager.shared.vo.CategoriaDespesaVo;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.smartgwt.client.util.SC;

public class CategoriaDialogPresenterWidget extends
		PresenterWidget<CategoriaDialogPresenterWidget.MyView> implements
		CategoriaUIHandlers {

	private DispatchAsync dispatcher;
	private List<CategoriaDespesaVo> categorias;
	private DespesaPresenter despesaPresenter;

	public interface MyView extends View, HasUiHandlers<CategoriaUIHandlers> {

		void fecharDialogo();

		void exibirDialogo();

		CategoriaDespesaVo getCategoria();

		void setResultSet(List<CategoriaDespesaVo> categorias);

		void limparFormulario();

	}

	@Inject
	public CategoriaDialogPresenterWidget(final EventBus eventBus,
			final MyView view, final DispatchAsync dispatcher) {
		super(eventBus, view);
		this.dispatcher = dispatcher;
		getView().setUiHandlers(this);
	}

	public void fecharDialogo() {
		getView().fecharDialogo();
	}

	public void show() {
		getView().exibirDialogo();
		listarCategorias();
	}

	@Override
	protected void onBind() {
		super.onBind();
		listarCategorias();
	}

	@Override
	public void onButtonIncluirClicked() {
		SC.showPrompt(ArteFinoOrderManager.getConstants().mensagemAguarde());
		dispatcher.execute(new CadastrarCategoriaDespesaAction(getView()
				.getCategoria()),
				new AsyncCallback<CadastrarCategoriaDespesaResult>() {
					@Override
					public void onFailure(Throwable caught) {
						SC.clearPrompt();
						SC.warn(caught.getMessage());
					}

					@Override
					public void onSuccess(CadastrarCategoriaDespesaResult result) {
						SC.clearPrompt();
						listarCategorias();
					}
				});
	}

	protected void listarCategorias() {
		SC.showPrompt(ArteFinoOrderManager.getConstants().mensagemCarregando());
		dispatcher.execute(new PesquisarCategoriasDespesaAction(10, 1),
				new AsyncCallback<PesquisarCategoriasDespesaResult>() {
					@Override
					public void onFailure(Throwable caught) {
						SC.clearPrompt();
						SC.warn(caught.getMessage());
					}

					@Override
					public void onSuccess(
							PesquisarCategoriasDespesaResult result) {
						SC.clearPrompt();
						getView().limparFormulario();
						setCategorias(result.getCategorias());
						getView().setResultSet(getCategorias());
						despesaPresenter.setCategorias(getCategorias());

					}
				});
	}

	protected void onListarCategorias() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRemoverCategoriaClicked(int idCategoria) {
		Long id = -1L;

		try {
			id = Long.valueOf(idCategoria);
		} catch (NumberFormatException nfe) {
			Log.debug("NumberFormatException: " + nfe.getLocalizedMessage());
			return;
		}

		SC.showPrompt(ArteFinoOrderManager.getConstants().mensagemAguarde());
		dispatcher.execute(new RemoverCategoriaDespesaAction(id),
				new AsyncCallback<RemoverCategoriaDespesaResult>() {
					@Override
					public void onFailure(Throwable caught) {
						SC.clearPrompt();
						SC.warn(caught.getMessage());
					}

					@Override
					public void onSuccess(RemoverCategoriaDespesaResult result) {
						SC.clearPrompt();
						listarCategorias();
					}
				});

	}

	public void setCategorias(List<CategoriaDespesaVo> categorias) {
		this.categorias = categorias;
	}

	public List<CategoriaDespesaVo> getCategorias() {
		return categorias;
	}

	public void setPresenterParent(DespesaPresenter despesaPresenter) {
		this.despesaPresenter = despesaPresenter;

	}

}
