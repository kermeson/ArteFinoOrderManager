package br.com.artefino.ordermanager.client.ui.clientes;

import java.util.LinkedHashMap;
import java.util.List;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.ui.clientes.handlers.PesquisarClientesDialogUIHandlers;
import br.com.artefino.ordermanager.client.util.ValidadoresUtil;
import br.com.artefino.ordermanager.shared.vo.ClienteVo;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CanvasItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class PesquisarClientesDialogView extends
		ViewWithUiHandlers<PesquisarClientesDialogUIHandlers> implements
		PesquisarClientesDialogPresenterWidget.MyView {

	private Window panel;
	private TextItem textItemNome;
	private TextItem textItemCNPJF;
	private SelectItem selectItemTipoPessoa;
	private DynamicForm dynamicForm;
	private Button btnPesquisar;
	private ClientesSimplesListGrid listGridClientes;

	@Inject
	public PesquisarClientesDialogView() {
		super();

		panel = new Window();
		panel.setShowMinimizeButton(false);
		panel.setIsModal(true);
		panel.setShowModalMask(true);
		panel.setShowShadow(true);
		panel.setShadowDepth(10);
		panel.setSmoothFade(true);
		panel.setWidth(500);
		panel.setHeight(380);
		panel.setTitle(ArteFinoOrderManager.getConstants().pesquisarClientes());
		panel.setAutoCenter(true);

		textItemNome = new TextItem();
		textItemNome.setName(ArteFinoOrderManager.getConstants().nome());
		textItemNome.setWidth(360);
		textItemNome.setLength(200);
		textItemNome.setColSpan(3);

		textItemCNPJF = new TextItem();
		textItemCNPJF.setTitle("CPF/CNPJ");
		textItemCNPJF.setWidth(110);
		textItemCNPJF.setMask("###.###.###-##");
		textItemCNPJF.setValidators(ValidadoresUtil.getValidadorCpfCnpj());

		selectItemTipoPessoa = new SelectItem();
		selectItemTipoPessoa.setTitle(ArteFinoOrderManager.getConstants()
				.tipoPessoa());
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("1", ArteFinoOrderManager.getConstants().fisica());
		map.put("2", ArteFinoOrderManager.getConstants().juridica());
		selectItemTipoPessoa.setValueMap(map);
		selectItemTipoPessoa.setDefaultValue(1);
		selectItemTipoPessoa.setWidth(100);
		selectItemTipoPessoa.setWrapTitle(false);

		btnPesquisar = new Button();
		btnPesquisar.setIcon("icons/16/find.png");
		btnPesquisar.setTitle(ArteFinoOrderManager.getConstants().pesquisar());

		CanvasItem canvasItem = new CanvasItem();
		canvasItem.setShowTitle(false);
		canvasItem.setCanvas(btnPesquisar);
		canvasItem.setWidth(150);

		dynamicForm = new DynamicForm();
		dynamicForm.setTitleOrientation(TitleOrientation.TOP);

		dynamicForm.setWidth(360);

		dynamicForm.setFields(//
				textItemNome, //
				selectItemTipoPessoa, //
				textItemCNPJF, canvasItem);
		dynamicForm.setNumCols(3);

		VLayout vLayout = new VLayout();
		vLayout.setPadding(5);
		vLayout.setStyleName("containerPadrao");
		vLayout.addMember(dynamicForm);


		listGridClientes = new ClientesSimplesListGrid();
		vLayout.addMember(listGridClientes);

		panel.addItem(vLayout);

		bindCustomUiHandlers();
	}

	protected void bindCustomUiHandlers() {
		btnPesquisar.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (getUiHandlers() != null) {
					getUiHandlers().onButtonPesquisarClicked();
				}
			}
		});

		// register the ListGird handlers
		listGridClientes
				.addRecordDoubleClickHandler(new RecordDoubleClickHandler() {
					@Override
					public void onRecordDoubleClick(RecordDoubleClickEvent event) {
						if (getUiHandlers() != null) {
							getUiHandlers().onRecordDoubleClicked(event);
						}
					}
				});

		listGridClientes.addRecordSelecionarClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(RecordClickEvent event) {
				if (getUiHandlers() != null) {
					getUiHandlers().onRecordSelecionarClicked(event);
				}
			}
		});
	}


	@Override
	public Widget asWidget() {
		return panel;
	}

	@Override
	public void show() {
		panel.show();
	}

	@Override
	public void setResultSet(List<ClienteVo> clientes) {
		listGridClientes.setResultSet(clientes);
	}

	@Override
	public void hide() {
		panel.hide();

	}

	@Override
	public void destroy() {
		panel.destroy();

	}



}
