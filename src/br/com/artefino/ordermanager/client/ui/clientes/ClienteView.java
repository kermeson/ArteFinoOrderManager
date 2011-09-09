package br.com.artefino.ordermanager.client.ui.clientes;

import java.util.LinkedHashMap;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.ui.clientes.handlers.ClienteUIHandlers;
import br.com.artefino.ordermanager.client.ui.widgets.IntegerTextItem;
import br.com.artefino.ordermanager.client.ui.widgets.ToolBar;
import br.com.artefino.ordermanager.client.util.FormatadorUtil;
import br.com.artefino.ordermanager.client.util.ValidadoresUtil;
import br.com.artefino.ordermanager.shared.vo.ClienteVo;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.types.CharacterCasing;
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

public class ClienteView extends
		ViewWithUiHandlers<ClienteUIHandlers> implements
		ClientePresenter.MyView {

	private ClienteVo clienteVo;
	private VLayout painel;

	private DynamicForm dynamicForm;
	private TextItem textItemNome;
	private TextItem textItemEndereco;

	private ToolBar toolBar;
	private SelectItem selectItemTipoPessoa;
	private TextItem textItemCnpjf;
	private IntegerTextItem textItemNumero;
	private TextItem textItemBairro;
	private TextItem textItemTelFixo;
	private TextItem textItemTelCel;
	private SelectItem selectItemUF;
	private TextItem textItemCidade;

	@Inject
	public ClienteView() {
		clienteVo = null;

		painel = new VLayout(5);

		VLayout vLayoutContainer = new VLayout();
		vLayoutContainer.setStyleName("containerPadrao");

		textItemNome = new TextItem();
		textItemNome.setTitle(ArteFinoOrderManager.getConstants().nome());
		textItemNome.setWidth(500);
		textItemNome.setLength(100);
		textItemNome.setRequired(true);
		textItemNome.setCharacterCasing(CharacterCasing.UPPER);
		textItemNome.setColSpan(3);

		selectItemTipoPessoa = new SelectItem();
		selectItemTipoPessoa.setTitle(ArteFinoOrderManager.getConstants()
				.tipoPessoa());
		selectItemTipoPessoa.setWidth(90);
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("1", ArteFinoOrderManager.getConstants().fisica());
		map.put("2", ArteFinoOrderManager.getConstants().juridica());
		selectItemTipoPessoa.setValueMap(map);
		selectItemTipoPessoa.setDefaultValue(1);

		textItemCnpjf = new TextItem();
		textItemCnpjf.setTitle(ArteFinoOrderManager.getConstants().cnpjf());
		textItemCnpjf.setRequired(true);
		textItemCnpjf.setMask("###.###.###-##");
		textItemCnpjf.setWidth(200);
		textItemCnpjf.setValidators(ValidadoresUtil.getValidadorCpfCnpj());

		textItemEndereco = new TextItem();
		textItemEndereco.setTitle(ArteFinoOrderManager.getConstants()
				.endereco());
		textItemEndereco.setWidth(500);
		textItemEndereco.setColSpan(3);
		textItemEndereco.setCharacterCasing(CharacterCasing.UPPER);

		textItemNumero = new IntegerTextItem();
		textItemNumero.setTitle(ArteFinoOrderManager.getConstants()
				.numeroEndereco());
		textItemNumero.setLimit(5);
		textItemNumero.setWidth(90);
		textItemEndereco.setCharacterCasing(CharacterCasing.UPPER);

		selectItemUF = new SelectItem();
		selectItemUF.setWidth(90);
		selectItemUF.setTitle(ArteFinoOrderManager.getConstants().uf());
		selectItemUF.setDefaultValue("CE");
		selectItemUF.setRequired(true);
		LinkedHashMap<String, String> mapUf = new LinkedHashMap<String, String>();
		map.put("AC", "AC");
		map.put("AL", "AL");
		map.put("AM", "AM");
		map.put("AP", "AP");
		map.put("BA", "BA");
		map.put("CE", "CE");
		map.put("DF", "DF");
		map.put("ES", "ES");
		map.put("GO", "GO");
		map.put("MA", "MA");
		map.put("MG", "MG");
		map.put("MS", "MS");
		map.put("MT", "MT");
		map.put("PA", "PA");
		map.put("PB", "PB");
		map.put("PE", "PE");
		map.put("PI", "PI");
		map.put("PR", "PR");
		map.put("RJ", "RJ");
		map.put("RN", "RN");
		map.put("RO", "RO");
		map.put("RR", "RR");
		map.put("RS", "RS");
		map.put("SC", "SC");
		map.put("SE", "SE");
		map.put("SP", "SP");
		map.put("TO", "TO");
		selectItemUF.setValueMap(mapUf);

		textItemBairro = new TextItem();
		textItemBairro.setTitle(ArteFinoOrderManager.getConstants().bairro());
		textItemBairro.setWidth(500);
		textItemBairro.setCharacterCasing(CharacterCasing.UPPER);
		textItemBairro.setStartRow(true);
		textItemBairro.setColSpan(3);

		textItemCidade = new TextItem();
		textItemCidade.setTitle(ArteFinoOrderManager.getConstants().cidade());
		textItemCidade.setWidth(200);
		textItemCidade.setCharacterCasing(CharacterCasing.UPPER);

		textItemTelFixo = new TextItem();
		textItemTelFixo.setTitle(ArteFinoOrderManager.getConstants()
				.telefoneFixo());
		textItemTelFixo.setMask("(##) ####-####");
		textItemTelFixo.setWidth(110);

		textItemTelCel = new TextItem();
		textItemTelCel.setTitle(ArteFinoOrderManager.getConstants()
				.telefoneCel());
		textItemTelCel.setMask("(##) ####-####");
		textItemTelCel.setWidth(110);

		dynamicForm = new DynamicForm();
		dynamicForm.setTitleOrientation(TitleOrientation.TOP);
		dynamicForm.setColWidths(110, 110, 380, 90, 200);
		dynamicForm.setNumCols(5);
		dynamicForm.setWidth(780);
		dynamicForm.setFields(textItemNome, selectItemTipoPessoa,
				textItemCnpjf, textItemEndereco, textItemNumero,
				textItemBairro, selectItemUF, textItemCidade, textItemTelFixo,
				textItemTelCel);
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
		clienteVo.setBairro(textItemBairro.getValueAsString());
		clienteVo.setCidade(textItemCidade.getValueAsString());
		clienteVo.setUf(selectItemUF.getValueAsString());
		clienteVo.setTelefoneFixo(textItemTelFixo.getValueAsString());
		clienteVo.setTelefoneCelular(textItemTelCel.getValueAsString());

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
			textItemCnpjf.setValue(FormatadorUtil.formatarCPF(clienteVo
					.getCnpjf()));
		} else {
			textItemCnpjf.setMask("##.###.###/####-##");
			textItemCnpjf.setValue(FormatadorUtil.formatarCNPJ(clienteVo
					.getCnpjf()));
		}

		textItemBairro.setValue(clienteVo.getBairro());
		textItemCidade.setValue(clienteVo.getCidade());
		selectItemUF.setValue(clienteVo.getUf());
		textItemTelFixo.setValue(clienteVo.getTelefoneFixo());
		textItemTelCel.setValue(clienteVo.getTelefoneCelular());
	}

	@Override
	public void limparFormulario() {
		dynamicForm.clearValues();
		clienteVo = null;
	}

	@Override
	public void setIdCliente(Long id) {
		clienteVo.setId(id);
	}
}
