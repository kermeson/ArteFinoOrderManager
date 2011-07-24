package br.com.artefino.ordermanager.client.ui.clients;

import java.util.LinkedHashMap;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.ui.clients.handlers.ClientInformationUIHandlers;
import br.com.artefino.ordermanager.client.ui.widgets.ToolBar;
import br.com.artefino.ordermanager.shared.vo.ClienteVo;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
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

	@Inject
	public ClientInformationView() {
		clienteVo = null;;

		painel = new VLayout();

		textItemNome = new TextItem();
		textItemNome.setTitle(ArteFinoOrderManager.getConstants().nome());

		selectItemTipoPessoa = new SelectItem();
		selectItemTipoPessoa.setTitle(ArteFinoOrderManager.getConstants()
				.tipoPessoa());
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("1", ArteFinoOrderManager.getConstants().fisica());
		map.put("2", ArteFinoOrderManager.getConstants().juridica());
		selectItemTipoPessoa.setValueMap(map);

		textItemEndereco = new TextItem();
		textItemEndereco.setTitle(ArteFinoOrderManager.getConstants().endereco());

		dynamicForm = new DynamicForm();
		dynamicForm.setFields(textItemNome, selectItemTipoPessoa, textItemEndereco);

		toolBar = new ToolBar();
		painel.addMember(toolBar);
		painel.addMember(dynamicForm);
		bindCustomUiHandlers();
	}

	protected void bindCustomUiHandlers() {

		// initialise the ToolBar and register its handlers
		initToolBar();
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
				// if (validateTabs()) {

			}
		});

		toolBar.addButton(ToolBar.SAVE_BUTTON, ArteFinoOrderManager
				.getConstants().salvar(), ArteFinoOrderManager.getConstants()
				.saveButtonTooltip(), new ClickHandler() {
			public void onClick(ClickEvent event) {
				//if (validateTabs()) {
					if (getUiHandlers() != null) {
						//Log.debug("onSaveClicked()");

						getUiHandlers()
								.onButtonSalvarClicked(getCliente());
					}
				//}
			}
		});

	}

	protected ClienteVo getCliente() {
		if (clienteVo != null) {
			clienteVo = new ClienteVo();
		}
		clienteVo.setNome(textItemNome.getValueAsString());
		clienteVo.setTipoPessoa(Integer.valueOf(selectItemTipoPessoa.getValueAsString()));

		return clienteVo;
	}
}
