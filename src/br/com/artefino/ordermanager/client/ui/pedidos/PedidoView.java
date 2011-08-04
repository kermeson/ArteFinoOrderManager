package br.com.artefino.ordermanager.client.ui.pedidos;

import java.util.LinkedHashMap;
import java.util.List;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.ui.pedidos.handlers.PedidoUIHandlers;
import br.com.artefino.ordermanager.client.ui.widgets.ToolBar;
import br.com.artefino.ordermanager.shared.vo.ClienteVo;
import br.com.artefino.ordermanager.shared.vo.PedidoVo;
import br.com.artefino.ordermanager.shared.vo.SituacaoPedidoVo;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.PickerIcon;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.FormItemClickHandler;
import com.smartgwt.client.widgets.form.fields.events.FormItemIconClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
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
	private PedidoVo pedidoVo;
	private SelectItem selectItemSituacao;

	@Inject
	public PedidoView(ToolBar toolBar, ItemPedidoListGrid listGridItens) {
		panel = new VLayout(5);

		this.toolBar = toolBar;

		panel.addMember(toolBar);

		VLayout vLayoutContainer = new VLayout(5);
		vLayoutContainer.setStyleName("containerPadrao");

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

		selectItemSituacao = new SelectItem();
		selectItemSituacao.setRequired(true);
		selectItemSituacao.setAllowEmptyValue(false);
		selectItemSituacao.setTitle(ArteFinoOrderManager.getConstants()
				.situacao());

		dynamicForm = new DynamicForm();
		dynamicForm.setTitleOrientation(TitleOrientation.TOP);
		dynamicForm.setNumCols(3);
		dynamicForm.setWidth(650);
		dynamicForm.setFields(textItemCliente, selectItemSituacao);
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

		buttonAdicionarItem.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				listGridItens.startEditingNew();
			}
		});

		listGridItens.addRecordExcluirClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(final RecordClickEvent event) {
				final Record record = event.getRecord();
				if (record == null) {
					return;
				}
				SC.confirm("Deseja remover o item?", new BooleanCallback() {
					public void execute(Boolean value) {
						if (value) {
							listGridItens.removeData(record);
						} else {
							listGridItens.discardEdits(event.getRecordNum(), 1);
						}

					}
				});
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
				if (validarCadastroPedido()) {
					if (getUiHandlers() != null) {
						getUiHandlers().onButtonSalvarPedido(getPedido());
					}
				}
			}
		});

	}

	protected PedidoVo getPedido() {
		if (pedidoVo == null) {
			pedidoVo = new PedidoVo();
		}
		pedidoVo.setCliente(clienteVo);
		SituacaoPedidoVo situacao = null;
		if (selectItemSituacao.getValue() != null) {
			situacao = new SituacaoPedidoVo();
			situacao.setId(Long.valueOf(selectItemSituacao.getValueAsString()));
		}
		pedidoVo.setSituacao(situacao);
		pedidoVo.setItens(listGridItens.getItens());

		return pedidoVo;
	}

	protected boolean validarCadastroPedido() {
		if (clienteVo == null) {
			SC.warn(ArteFinoOrderManager.getMessages().selecioneClientePedido());
			return false;
		}
		if (listGridItens.getRecords() == null
				|| listGridItens.getRecords().length < 1) {
			SC.warn(ArteFinoOrderManager.getMessages().preenchaItensPedido());
			return false;
		} else {
			if (listGridItens.hasErrors()) {
				SC.warn(ArteFinoOrderManager.getMessages()
						.preenchaCamposObrigatoriosItensPedido());
				return false;
			}
		}
		return true;
	}

	@Override
	public void setCliente(ClienteVo clienteVo) {
		this.clienteVo = clienteVo;
		preencherDadosCliente(clienteVo);

	}

	private void preencherDadosCliente(ClienteVo clienteVo) {
		textItemCliente.setValue(clienteVo.getNome());
	}

	@Override
	public void limparTelaCadastro() {
		dynamicForm.clearValues();
		listGridItens.removerItens();
		clienteVo = null;
		pedidoVo = null;
	}

	@Override
	public void setSituacoes(List<SituacaoPedidoVo> situacaoPedidoVos) {
		if (situacaoPedidoVos != null) {
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
			for (SituacaoPedidoVo situacaoPedidoVo : situacaoPedidoVos) {
				map.put(situacaoPedidoVo.getId().toString(),
						situacaoPedidoVo.getNome());
			}
			selectItemSituacao.setValueMap(map);
		}

	}
}
