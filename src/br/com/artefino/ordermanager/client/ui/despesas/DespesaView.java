package br.com.artefino.ordermanager.client.ui.despesas;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.ui.despesas.handlers.DespesaUIHandlers;
import br.com.artefino.ordermanager.client.ui.widgets.CurrencyItem;
import br.com.artefino.ordermanager.client.ui.widgets.ToolBar;
import br.com.artefino.ordermanager.shared.vo.CategoriaDespesaVo;
import br.com.artefino.ordermanager.shared.vo.DespesaVo;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.layout.VLayout;

public class DespesaView extends
		ViewWithUiHandlers<DespesaUIHandlers> implements
		DespesaPresenter.MyView {

	private DespesaVo despesa;
	private VLayout painel;

	private DynamicForm dynamicForm;

	private SelectItem selectItemCategoria;
	private CurrencyItem currencyItemValor;
	private TextAreaItem textAreaItemDescricao;
	private ToolBar toolBar;
	private DateItem dateItemData;

	@Inject
	public DespesaView() {
		despesa = null;

		painel = new VLayout(5);

		VLayout vLayoutContainer = new VLayout();
		vLayoutContainer.setStyleName("containerPadrao");

		selectItemCategoria = new SelectItem();
		selectItemCategoria.setTitle(ArteFinoOrderManager.getConstants()
				.tipoPessoa());
		selectItemCategoria.setWidth(90);

		currencyItemValor = new CurrencyItem();
		currencyItemValor.setTitle(ArteFinoOrderManager.getConstants().numeroEndereco());
		currencyItemValor.setLimit(5);
		currencyItemValor.setWidth(90);

		textAreaItemDescricao = new TextAreaItem();
		textAreaItemDescricao.setTitle(ArteFinoOrderManager.getConstants().descricao());

		dateItemData = new DateItem();
		dateItemData.setTitle(ArteFinoOrderManager.getConstants()
				.dataCadastro());
		dateItemData.setUseTextField(true);
		dateItemData.setUseMask(true);
		dateItemData
				.setDateFormatter(DateDisplayFormat.TOEUROPEANSHORTDATE);
		dateItemData.setWidth(90);

		dynamicForm = new DynamicForm();
		dynamicForm.setTitleOrientation(TitleOrientation.TOP);
		dynamicForm.setColWidths(110, 110, 380, 90, 200);
		dynamicForm.setNumCols(3);
		dynamicForm.setWidth(780);
		dynamicForm.setFields(selectItemCategoria, currencyItemValor,dateItemData, textAreaItemDescricao);
		vLayoutContainer.addMember(dynamicForm);

		toolBar = new ToolBar();
		painel.addMember(toolBar);
		painel.addMember(vLayoutContainer);

		bindCustomUiHandlers();
	}

	protected void bindCustomUiHandlers() {

		// initialise the ToolBar and register its handlers
		initToolBar();

	}

	@Override
	public Widget asWidget() {
		return painel;
	}

	protected void initToolBar() {

		Log.debug("initToolBar()");

		toolBar.addButton(ToolBar.BACK_BUTTON, ArteFinoOrderManager
				.getConstants().voltar(), new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (getUiHandlers() != null) {
					getUiHandlers().onButtonVoltarClicked();
				}
			}
		});

		toolBar.addButton(ToolBar.SAVE_BUTTON, ArteFinoOrderManager
				.getConstants().salvar(), new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (validarCadastro()) {
					if (getUiHandlers() != null) {
						getUiHandlers().onButtonSalvarClicked(getDespesa());
					}
				}
			}
		});

	}

	protected boolean validarCadastro() {
		if (dynamicForm.validate()) {
			return true;
		}
		SC.warn(ArteFinoOrderManager.getMessages().preenchaCamposObrigatorios());
		return false;
	}

	protected DespesaVo getDespesa() {
		if (despesa == null) {
			despesa = new DespesaVo();
		}
		despesa.setDescricao(textAreaItemDescricao.getValueAsString());
		CategoriaDespesaVo categoriaDespesaVo = new CategoriaDespesaVo();
		categoriaDespesaVo.setId((Long) textAreaItemDescricao.getValue());
		despesa.setCategoria(categoriaDespesaVo);
		despesa.setDataCadastro(dateItemData.getValueAsDate());
		despesa.setValor(currencyItemValor.getValor());

		return despesa;
	}

	@Override
	public void setDespesa(DespesaVo despesa) {
		if (despesa != null) {
			this.despesa = despesa;
			preencherFormulario(despesa);
		}
	}

	private void preencherFormulario(DespesaVo despesa) {
		selectItemCategoria.setValue(despesa.getCategoria().getId());
		textAreaItemDescricao.setValue(despesa.getDescricao());
		currencyItemValor.setValor(despesa.getValor());
		dateItemData.setValue(despesa.getDataCadastro());
	}

	@Override
	public void limparFormulario() {
		dynamicForm.clearValues();
		despesa = null;
	}

	@Override
	public void setIdCliente(Long id) {
		despesa.setId(id);
	}
}
