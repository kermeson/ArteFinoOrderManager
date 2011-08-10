package br.com.artefino.ordermanager.client.ui.pedidos;

import java.util.HashMap;
import java.util.Map;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.shared.vo.ClienteVo;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CanvasItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.smartgwt.client.widgets.form.fields.PickerIcon;
import com.smartgwt.client.widgets.form.fields.TextItem;

import com.smartgwt.client.widgets.form.fields.events.FormItemClickHandler;

public class PesquisarPedidosDynamicForm extends DynamicForm {
	private TextItem textItemCliente;
	private PickerIcon pickerIconPesquisarClientes;
	private DateItem dateItemDataInicial;
	private DateItem dateItemDataFinal;
	private Button btnPesquisar;
	private ClienteVo clienteVo;

	public PesquisarPedidosDynamicForm() {
		super();

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

		HeaderItem headerItemPeriodo = new HeaderItem();
		headerItemPeriodo.setDefaultValue(ArteFinoOrderManager.getConstants()
				.periodoCadastro());

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

		CanvasItem canvasItem = new CanvasItem();
		canvasItem.setShowTitle(true);
		canvasItem.setTitle("");
		canvasItem.setCanvas(btnPesquisar);
		canvasItem.setWidth(150);

		setTitleOrientation(TitleOrientation.TOP);
		setFields(textItemCliente, headerItemPeriodo, dateItemDataInicial,
				dateItemDataFinal, canvasItem);
		setNumCols(3);
	}

	public Map<String, Object> getParametrosPesquisa() {
		Map<String, Object> parametros = new HashMap<String, Object>();
		if (clienteVo != null) {
			parametros.put("idCliente", clienteVo.getId());
		}
		parametros.put("dataInicial", dateItemDataInicial.getValueAsDate());
		parametros.put("dataFinal", dateItemDataFinal.getValueAsDate());
		return parametros;
	}

	public void addPesquisarClienteFormItemClickHandler(
			FormItemClickHandler formItemClickHandler) {
		pickerIconPesquisarClientes
				.addFormItemClickHandler(formItemClickHandler);

	}

	public void addButtonPesquisarClickHandler(ClickHandler handler) {
		btnPesquisar.addClickHandler(handler);
	}


	public void setCliente(ClienteVo clienteVo) {
		this.clienteVo = clienteVo;
		textItemCliente.setValue(clienteVo.getNome());

	}

}
