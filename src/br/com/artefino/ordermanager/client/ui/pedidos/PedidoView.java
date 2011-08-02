package br.com.artefino.ordermanager.client.ui.pedidos;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.ui.pedidos.handlers.PedidoUIHandlers;
import br.com.artefino.ordermanager.client.ui.widgets.ToolBar;
import br.com.artefino.ordermanager.shared.vo.ClienteVo;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.PickerIcon;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.FormItemClickHandler;
import com.smartgwt.client.widgets.form.fields.events.FormItemIconClickEvent;
import com.smartgwt.client.widgets.layout.VLayout;

public class PedidoView extends ViewWithUiHandlers<PedidoUIHandlers> implements
		PedidoPresenter.MyView {

	private VLayout panel;
	private ToolBar toolBar;
	private DynamicForm dynamicForm;
	private TextItem textItemCliente;
	private PickerIcon pickerIconPesquisarClientes;
	private ClienteVo clienteVo;
	private ItemPedidoListGrid listGridItens;
	private Button buttonAdicionarItem;
	private Label labelItens;

	@Inject
	public PedidoView(ToolBar toolBar, ItemPedidoListGrid listGridItens) {
		panel = new VLayout(5);

		this.toolBar = toolBar;

		panel.addMember(toolBar);

		VLayout vLayoutContainer = new VLayout(5);
		vLayoutContainer.setStyleName("containerPadrao");

		pickerIconPesquisarClientes = new PickerIcon(PickerIcon.SEARCH);

		textItemCliente = new TextItem();
		textItemCliente.setTitle(ArteFinoOrderManager.getConstants().cliente());
		textItemCliente.setWidth(500);
		textItemCliente.setLength(100);
		textItemCliente.setRequired(true);
		textItemCliente.setIcons(pickerIconPesquisarClientes);

		dynamicForm = new DynamicForm();
		dynamicForm.setTitleOrientation(TitleOrientation.TOP);
		dynamicForm.setNumCols(3);
		dynamicForm.setWidth(650);
		dynamicForm.setFields(textItemCliente);
		vLayoutContainer.addMember(dynamicForm);

		VLayout vLayoutItens = new VLayout(5);
		labelItens = new Label(ArteFinoOrderManager.getConstants()
				.tituloItensPedido());
		labelItens.setAutoHeight();
		vLayoutItens.addMember(labelItens);

		this.listGridItens = listGridItens;
		vLayoutItens.addMember(listGridItens);

		buttonAdicionarItem = new Button();
		buttonAdicionarItem.setTitle(ArteFinoOrderManager.getConstants()
				.adicionarItem());
		vLayoutItens.addMember(buttonAdicionarItem);

		vLayoutContainer.addMember(vLayoutItens);

		panel.addMember(vLayoutContainer);

		bindCustomUiHandlers();
	}

	protected void bindCustomUiHandlers() {

		pickerIconPesquisarClientes
				.addFormItemClickHandler(new FormItemClickHandler() {
					@Override
					public void onFormItemClick(FormItemIconClickEvent event) {
						if (getUiHandlers() != null) {
							getUiHandlers().onButtonPesquisarClientesClicked();
						}
					}
				});

		buttonAdicionarItem
				.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						listGridItens.startEditingNew();
					}
				});

		// initialise the ToolBar and register its handlers
		initToolBar();
	}

	@Override
	public Widget asWidget() {
		return panel;
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
				// if (validarCadastroCliente()) {
				if (getUiHandlers() != null) {
					// getUiHandlers().onButtonSalvarClicked(getCliente());
				}
				// }
			}
		});

	}

	@Override
	public void setCliente(ClienteVo clienteVo) {
		this.clienteVo = clienteVo;
		preencherDadosCliente(clienteVo);

	}

	private void preencherDadosCliente(ClienteVo clienteVo) {
		textItemCliente.setValue(clienteVo.getNome());
	}
}