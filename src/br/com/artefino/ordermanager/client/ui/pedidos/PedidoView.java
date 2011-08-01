package br.com.artefino.ordermanager.client.ui.pedidos;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.ui.pedidos.handlers.PedidoUIHandlers;
import br.com.artefino.ordermanager.client.ui.widgets.ToolBar;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.PickerIcon;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.FormItemClickHandler;
import com.smartgwt.client.widgets.form.fields.events.FormItemIconClickEvent;
import com.smartgwt.client.widgets.layout.VLayout;

public class PedidoView extends ViewWithUiHandlers<PedidoUIHandlers> implements
		PedidoPresenter.MyView {

	private VLayout panel;
	private ToolBar toolBar;
	private String idPedido;
	private DynamicForm dynamicForm;
	private TextItem textItemCliente;
	private SelectItem selectItemTipoPessoa;
	private PickerIcon pickerIconPesquisarClientes;

	@Inject
	public PedidoView(ToolBar toolBar) {
		panel = new VLayout(5);
		this.toolBar = toolBar;

		panel.addMember(toolBar);

		VLayout vLayoutContainer = new VLayout();
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

		panel.addMember(vLayoutContainer);

		bindCustomUiHandlers();
	}

	protected void bindCustomUiHandlers() {

		pickerIconPesquisarClientes.addFormItemClickHandler(new FormItemClickHandler() {
              @Override
              public void onFormItemClick(FormItemIconClickEvent event) {
            	  if (getUiHandlers() != null) {
  					getUiHandlers().onButtonPesquisarClientesClicked();
  				}
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
}
