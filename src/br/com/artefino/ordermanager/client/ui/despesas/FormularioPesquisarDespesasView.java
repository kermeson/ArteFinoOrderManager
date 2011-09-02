package br.com.artefino.ordermanager.client.ui.despesas;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.ui.despesas.handlers.FormularioPesquisarDespesasUIHandlers;
import br.com.artefino.ordermanager.shared.vo.CategoriaDespesaVo;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CanvasItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class FormularioPesquisarDespesasView extends
		ViewWithUiHandlers<FormularioPesquisarDespesasUIHandlers> implements
		FormularioPesquisarDespesasPresenterWidget.MyView {

	private DynamicForm dynamicForm;
	private DateItem dateItemDataInicial;
	private DateItem dateItemDataFinal;
	private Button btnExportar;
	private Button btnLimpar;
	private VLayout panel;

	private Button btnPesquisar;
	private SelectItem selectItemCategoria;

	@Inject
	public FormularioPesquisarDespesasView() {
		super();
		panel = new VLayout();
		dynamicForm = new DynamicForm();

		selectItemCategoria = new SelectItem();
		selectItemCategoria.setTitle(ArteFinoOrderManager.getConstants()
				.categoria());
		selectItemCategoria.setWidth(200);
		selectItemCategoria.setAllowEmptyValue(true);

		HeaderItem headerItemPeriodo = new HeaderItem();
		headerItemPeriodo.setDefaultValue(ArteFinoOrderManager.getConstants()
				.periodoCadastro());
		headerItemPeriodo.setTextBoxStyle("subtitulo");
		headerItemPeriodo.setHeight(15);

		dateItemDataInicial = new DateItem();
		dateItemDataInicial.setTitle(ArteFinoOrderManager.getConstants()
				.dataInicial());
		dateItemDataInicial.setUseTextField(true);
		dateItemDataInicial.setUseMask(true);
		dateItemDataInicial
				.setDateFormatter(DateDisplayFormat.TOEUROPEANSHORTDATE);
		dateItemDataInicial.setWidth(90);

		dateItemDataFinal = new DateItem();
		dateItemDataFinal.setTitle(ArteFinoOrderManager.getConstants()
				.dataFinal());
		dateItemDataFinal.setUseTextField(true);
		dateItemDataFinal.setUseMask(true);
		dateItemDataFinal
				.setDateFormatter(DateDisplayFormat.TOEUROPEANSHORTDATE);
		dateItemDataFinal.setWidth(90);

		btnPesquisar = new Button();
		btnPesquisar.setIcon("icons/16/find.png");
		btnPesquisar.setTitle(ArteFinoOrderManager.getConstants().pesquisar());

		btnExportar = new Button();
		btnExportar.setIcon("icons/16/pdf.png");
		btnExportar.setTitle(ArteFinoOrderManager.getConstants().exportar());

		btnLimpar = new Button();
		btnLimpar.setIcon("icons/16/eraser.png");
		btnLimpar.setTitle(ArteFinoOrderManager.getConstants().limpar());

		HLayout hLayoutBotoes = new HLayout(5);
		hLayoutBotoes.setMembers(btnPesquisar, btnExportar, btnLimpar);
		hLayoutBotoes.setAutoHeight();

		CanvasItem canvasItem = new CanvasItem();
		canvasItem.setShowTitle(true);
		canvasItem.setTitle("");
		canvasItem.setCanvas(hLayoutBotoes);
		canvasItem.setWidth(150);

		dynamicForm.setTitleOrientation(TitleOrientation.TOP);
		dynamicForm.setFields(selectItemCategoria, headerItemPeriodo,
				dateItemDataInicial, dateItemDataFinal, canvasItem);
		dynamicForm.setNumCols(4);
		dynamicForm.setWidth(700);
		dynamicForm.setColWidths(90, 120, 190, 200);

		panel.addMember(dynamicForm);
		panel.setStyleName("containerPadrao");

		bindCustomUiHandlers();
	}

	protected void bindCustomUiHandlers() {
		btnLimpar.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				dynamicForm.clearValues();
			}
		});
	}

	@Override
	public Widget asWidget() {
		return panel;
	}

	@Override
	public Map<String, Object> getParametrosPesquisa() {
		Map<String, Object> parametros = new HashMap<String, Object>();
		if (selectItemCategoria.getValue() != null) {
			parametros.put("idCategoria", Long.valueOf(selectItemCategoria
					.getValueAsString()));
		}
		if (dateItemDataInicial.getValue() != null) {
			parametros.put("dataInicial", dateItemDataInicial.getValueAsDate()
					.getTime());
		}
		if (dateItemDataFinal.getValue() != null) {
			parametros.put("dataFinal", dateItemDataFinal.getValueAsDate()
					.getTime());
		}
		return parametros;
	}

	@Override
	public void setCategorias(List<CategoriaDespesaVo> categorias) {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		if (categorias != null) {
			for (CategoriaDespesaVo categoriaDespesaVo : categorias) {
				map.put(categoriaDespesaVo.getId().toString(),
						categoriaDespesaVo.getNome());
			}
		}
		selectItemCategoria.setValueMap(map);

	}

	@Override
	public void addButtonExportarClickHandler(ClickHandler clickHandler) {
		btnExportar.addClickHandler(clickHandler);
	}

	@Override
	public void addButtonPesquisarClickHandler(ClickHandler clickHandler) {
		btnPesquisar.addClickHandler(clickHandler);

	}

}
