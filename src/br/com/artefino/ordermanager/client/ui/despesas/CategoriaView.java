package br.com.artefino.ordermanager.client.ui.despesas;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.ui.despesas.handlers.CategoriaUIHandlers;
import br.com.artefino.ordermanager.shared.vo.ClienteVo;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CanvasItem;
import com.smartgwt.client.widgets.form.fields.PickerIcon;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.FormItemClickHandler;
import com.smartgwt.client.widgets.form.fields.events.FormItemIconClickEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class CategoriaView extends
		ViewWithUiHandlers<CategoriaUIHandlers> implements
		CategoriaPresenterWidget.MyView {

	private DynamicForm dynamicForm;
	private TextItem textItemNome;
	private Button btnExportar;
	private Button btnLimpar;
	private PickerIcon pickerIconPesquisarClientes;
	private VLayout panel;

	private ClienteVo clienteVo;
	private Button btnIncluir;

	@Inject
	public CategoriaView() {
		super();
		panel = new VLayout();
		dynamicForm = new DynamicForm();


		textItemNome = new TextItem();
		textItemNome.setTitle(ArteFinoOrderManager.getConstants().nome());
		textItemNome.setWidth(500);
		textItemNome.setLength(100);
		textItemNome.setRequired(true);
		textItemNome.setIcons(pickerIconPesquisarClientes);
		textItemNome.setDisabled(true);
		textItemNome.setShowDisabled(false);
		textItemNome.setColSpan(3);



		btnIncluir = new Button();
		btnIncluir.setIcon("icons/16/add.png");
		btnIncluir.setTitle(ArteFinoOrderManager.getConstants().incluir());


		HLayout hLayoutBotoes = new HLayout(5);
		hLayoutBotoes.setMembers(btnIncluir);
		hLayoutBotoes.setAutoHeight();

		CanvasItem canvasItem = new CanvasItem();
		canvasItem.setShowTitle(true);
		canvasItem.setTitle("");
		canvasItem.setCanvas(hLayoutBotoes);
		canvasItem.setWidth(150);

		dynamicForm.setTitleOrientation(TitleOrientation.TOP);
		dynamicForm.setFields(textItemNome, canvasItem);
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
				clienteVo = null;
			}
		});

		pickerIconPesquisarClientes
				.addFormItemClickHandler(new FormItemClickHandler() {
					@Override
					public void onFormItemClick(FormItemIconClickEvent event) {
						if (getUiHandlers() != null) {

						}
					}
				});

	}

	@Override
	public Widget asWidget() {
		return panel;
	}





}
