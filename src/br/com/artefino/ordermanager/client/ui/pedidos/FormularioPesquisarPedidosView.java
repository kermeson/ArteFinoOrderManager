package br.com.artefino.ordermanager.client.ui.pedidos;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.ui.pedidos.handlers.FormularioPesquisarPedidosUIHandlers;
import br.com.artefino.ordermanager.shared.vo.ClienteVo;
import br.com.artefino.ordermanager.shared.vo.SituacaoPedidoVo;

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
import com.smartgwt.client.widgets.form.fields.PickerIcon;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.FormItemClickHandler;
import com.smartgwt.client.widgets.form.fields.events.FormItemIconClickEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class FormularioPesquisarPedidosView extends
		ViewWithUiHandlers<FormularioPesquisarPedidosUIHandlers> implements
		FormularioPesquisarPedidosPresenterWidget.MyView {

	private DynamicForm dynamicForm;
	private TextItem textItemCliente;
	private SelectItem selectItemSituacao;
	private DateItem dateItemDataInicial;
	private DateItem dateItemDataFinal;
	private Button btnExportar;
	private Button btnLimpar;
	private PickerIcon pickerIconPesquisarClientes;
	private VLayout panel;

	private ClienteVo clienteVo;

	@Inject
	public FormularioPesquisarPedidosView() {
		super();
		panel = new VLayout();
		dynamicForm = new DynamicForm();
		pickerIconPesquisarClientes = new PickerIcon(PickerIcon.SEARCH);
		pickerIconPesquisarClientes.setNeverDisable(true);

		textItemCliente = new TextItem();
		textItemCliente.setTitle(ArteFinoOrderManager.getConstants().cliente());
		textItemCliente.setWidth(500);
		textItemCliente.setLength(100);
		textItemCliente.setRequired(true);
		textItemCliente.setIcons(pickerIconPesquisarClientes);
		textItemCliente.setDisabled(true);
		textItemCliente.setShowDisabled(false);
		textItemCliente.setColSpan(3);

		selectItemSituacao = new SelectItem();
		selectItemSituacao.setRequired(true);
		selectItemSituacao.setAllowEmptyValue(true);
		selectItemSituacao.setTitle(ArteFinoOrderManager.getConstants()
				.situacao());
		selectItemSituacao.setWidth(200);

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

		btnExportar = new Button();
		btnExportar.setIcon("icons/16/pdf.png");
		btnExportar.setTitle(ArteFinoOrderManager.getConstants().exportar());

		btnLimpar = new Button();
		btnLimpar.setIcon("icons/16/eraser.png");
		btnLimpar.setTitle(ArteFinoOrderManager.getConstants().limpar());

		HLayout hLayoutBotoes = new HLayout(5);
		hLayoutBotoes.setMembers(btnExportar, btnLimpar);
		hLayoutBotoes.setAutoHeight();

		CanvasItem canvasItem = new CanvasItem();
		canvasItem.setShowTitle(true);
		canvasItem.setTitle("");
		canvasItem.setCanvas(hLayoutBotoes);
		canvasItem.setWidth(150);

		dynamicForm.setTitleOrientation(TitleOrientation.TOP);
		dynamicForm.setFields(textItemCliente, selectItemSituacao,
				headerItemPeriodo, dateItemDataInicial, dateItemDataFinal,
				canvasItem);
		dynamicForm.setNumCols(4);
		dynamicForm.setWidth(700);
		dynamicForm.setColWidths(90, 120, "*");

		panel.addMember(dynamicForm);
		panel.setStyleName("containerPadrao");

		bindCustomUiHandlers();
	}

	protected void bindCustomUiHandlers() {
		btnLimpar.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				dynamicForm.clearValues();
				clienteVo = null;
			}
		});

		pickerIconPesquisarClientes
				.addFormItemClickHandler(new FormItemClickHandler() {
					@Override
					public void onFormItemClick(FormItemIconClickEvent event) {
						if (getUiHandlers() != null) {
							getUiHandlers().onButtonPesquisarClientesClicked();
						}
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
		if (clienteVo != null) {
			parametros.put("idCliente", clienteVo.getId());
		}
		if (dateItemDataInicial.getValue() != null) {
			parametros.put("dataInicial", dateItemDataInicial.getValueAsDate().getTime());
		}
		if (dateItemDataFinal.getValue() != null) {
			parametros.put("dataFinal", dateItemDataFinal.getValueAsDate().getTime());
		}
		parametros.put("situacao", selectItemSituacao.getValueAsString());
		return parametros;
	}

	@Override
	public void setCliente(ClienteVo clienteVo) {
		this.clienteVo = clienteVo;
		textItemCliente.setValue(clienteVo.getNome());
	}

	@Override
	public void setItensSituacao(List<SituacaoPedidoVo> situacaoPedidoVos) {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		for (SituacaoPedidoVo situacaoPedidoVo : situacaoPedidoVos) {
			map.put(situacaoPedidoVo.getId().toString(), situacaoPedidoVo
					.getNome());
		}
		selectItemSituacao.setValueMap(map);
	}

	@Override
	public void addButtonExportarClickHandler(ClickHandler clickHandler) {
		btnExportar.addClickHandler(clickHandler);

	}

}
