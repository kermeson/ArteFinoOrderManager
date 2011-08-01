package br.com.artefino.ordermanager.client.ui.clientes;

import java.util.LinkedHashMap;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.ui.clientes.handlers.ClientInformationUIHandlers;
import br.com.artefino.ordermanager.client.ui.widgets.ToolBar;
import br.com.artefino.ordermanager.client.util.FormatadorUtil;
import br.com.artefino.ordermanager.shared.vo.ClienteVo;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class ClientInformationView extends
		ViewWithUiHandlers<ClientInformationUIHandlers> implements
		ClientInformationPresenter.MyView {

	private ClienteVo clienteVo;
	private VLayout painel;

	private DynamicForm dynamicForm;
	private TextItem textItemNome;
	private TextItem textItemEndereco;

	private ToolBar toolBar;
	private SelectItem selectItemTipoPessoa;
	private TextItem textItemCnpjf;
	private TextItem textItemNumero;
	private TextItem textItemBairro;

	@Inject
	public ClientInformationView() {
		clienteVo = null;

		painel = new VLayout(5);

		VLayout vLayoutContainer = new VLayout();
		vLayoutContainer.setStyleName("containerPadrao");

		textItemNome = new TextItem();
		textItemNome.setTitle(ArteFinoOrderManager.getConstants().nome());
		textItemNome.setWidth(500);
		textItemNome.setLength(100);
		textItemNome.setRequired(true);

		selectItemTipoPessoa = new SelectItem();
		selectItemTipoPessoa.setTitle(ArteFinoOrderManager.getConstants()
				.tipoPessoa());
		selectItemTipoPessoa.setWidth(100);
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("1", ArteFinoOrderManager.getConstants().fisica());
		map.put("2", ArteFinoOrderManager.getConstants().juridica());
		selectItemTipoPessoa.setValueMap(map);
		selectItemTipoPessoa.setDefaultValue(1);

		textItemCnpjf = new TextItem();
		textItemCnpjf.setTitle(ArteFinoOrderManager.getConstants().cnpjf());
		textItemCnpjf.setRequired(true);
		textItemCnpjf.setMask("###.###.###-##");

		textItemEndereco = new TextItem();
		textItemEndereco.setTitle(ArteFinoOrderManager.getConstants()
				.endereco());
		textItemEndereco.setWidth(500);

		textItemBairro = new TextItem();
		textItemBairro.setTitle(ArteFinoOrderManager.getConstants()
				.bairro());
		textItemBairro.setWidth(500);

		textItemNumero = new TextItem();
		textItemNumero.setTitle(ArteFinoOrderManager.getConstants()
				.numeroEndereco());
		textItemNumero.setLength(5);
		textItemNumero.setWidth(60);

		dynamicForm = new DynamicForm();
		dynamicForm.setTitleOrientation(TitleOrientation.TOP);
		dynamicForm.setNumCols(3);
		dynamicForm.setWidth(650);
		dynamicForm.setFields(textItemNome, selectItemTipoPessoa, textItemCnpjf,
				textItemEndereco, textItemNumero);
		vLayoutContainer.addMember(dynamicForm);

		toolBar = new ToolBar();
		painel.addMember(toolBar);
		painel.addMember(vLayoutContainer);

		bindCustomUiHandlers();
	}

	protected void bindCustomUiHandlers() {

		// initialise the ToolBar and register its handlers
		initToolBar();

		selectItemTipoPessoa.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				if (event.getValue().equals("2")) {
					textItemCnpjf.setMask("##.###.###/####-##");
				} else if (event.getValue().equals("1")) {
					textItemCnpjf.setMask("###.###.###-##");
				}
			}
		});
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
				if (validarCadastroCliente()) {
					if (getUiHandlers() != null) {
						getUiHandlers().onButtonSalvarClicked(getCliente());
					}
				}
			}
		});

	}

	protected boolean validarCadastroCliente() {
		if (dynamicForm.validate()) {
			return true;
		}
		SC.warn(ArteFinoOrderManager.getMessages().preenchaCamposObrigatorios());
		return false;
	}

	protected ClienteVo getCliente() {
		if (clienteVo == null) {
			clienteVo = new ClienteVo();
		}
		clienteVo.setNome(textItemNome.getValueAsString());
		clienteVo.setTipoPessoa(Integer.valueOf(selectItemTipoPessoa
				.getValueAsString()));
		clienteVo.setEndereco(textItemEndereco.getValueAsString());
		clienteVo.setCnpjf(Long.parseLong(textItemCnpjf.getValueAsString()));

		return clienteVo;
	}

	@Override
	public void setCliente(ClienteVo clienteVo) {
		if (clienteVo != null) {
			this.clienteVo = clienteVo;
			preencherFormularioCliente(clienteVo);
		}
	}

	private void preencherFormularioCliente(ClienteVo clienteVo) {
		textItemNome.setValue(clienteVo.getNome());
		selectItemTipoPessoa.setValue(clienteVo.getTipoPessoa());
		textItemEndereco.setValue(clienteVo.getEndereco());

		if (clienteVo.getTipoPessoa() == 1) {
			textItemCnpjf.setMask("###.###.###-##");
			textItemCnpjf.setValue(FormatadorUtil.formatarCPF(clienteVo.getCnpjf()));
		} else {
			textItemCnpjf.setMask("##.###.###/####-##");
			textItemCnpjf.setValue(FormatadorUtil.formatarCNPJ(clienteVo.getCnpjf()));
		}
	}

	@Override
	public void limparFormulario() {
		dynamicForm.clearValues();
		clienteVo = null;
	}
}
