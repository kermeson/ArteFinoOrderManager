package br.com.artefino.ordermanager.client.ui.login;

import br.com.artefino.ordermanager.client.ui.login.handlers.SignInPageUiHandlers;

import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VLayout;

public class LoginView extends ViewWithUiHandlers<SignInPageUiHandlers>
		implements LoginPresenter.MyView {

	private static final String DEFAULT_USER_NAME = "Administrator";
	private static final String DEFAULT_PASSWORD = "12345";

	private VLayout panel;

	private final TextItem userNameField;
	private final PasswordItem passwordField;
	private final Button signInButton;

	public LoginView() {

		panel = new VLayout();

		DynamicForm dynamicForm = new DynamicForm();
		userNameField = new TextItem();
		userNameField.setTitle("Usuário");
		userNameField.setDefaultValue(DEFAULT_USER_NAME);

		passwordField = new PasswordItem();
		passwordField.setTitle("Senha");
		passwordField.setDefaultValue(DEFAULT_PASSWORD);


		dynamicForm.setFields(userNameField, passwordField);

		signInButton = new Button("Entrar");

		panel.addMember(dynamicForm);
		panel.addMember(signInButton);

		bindCustomUiHandlers();
	}

	protected void bindCustomUiHandlers() {

		signInButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				if (getUserName() != null && getPassword() != null) {
					if (getUiHandlers() != null) {
						getUiHandlers().onOkButtonClicked();
					}
				} else {
					event.cancel();
					SC.say("Sign in",
							"You must enter a valid User name and Password.");
					resetAndFocus();
				}
			}
		});
	}

	@Override
	public Widget asWidget() {
		return panel;
	}

	@Override
	public String getUserName() {
		return userNameField.getValueAsString();
	}

	@Override
	public String getPassword() {
		return passwordField.getValueAsString();
	}

	@Override
	public void resetAndFocus() {
		userNameField.focusInItem();
	}
}
