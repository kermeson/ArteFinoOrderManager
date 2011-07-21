package br.com.artefino.ordermanager.client.ui.clients;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VLayout;

public class ClientView extends ViewImpl implements ClientPresenter.MyView {
	private VLayout panel;
	@Inject
	public ClientView() {
		panel = new VLayout();

		DynamicForm dynamicForm = new DynamicForm();

		TextItem textItemName = new TextItem();
		textItemName.setTitle("Nome");

		Button buttonSave = new Button("Salvar");

		dynamicForm.setFields(textItemName);

		panel.addMember(dynamicForm);
		panel.addMember(buttonSave);
	}

	@Override
	public Widget asWidget() {
		return panel;
	}
}
