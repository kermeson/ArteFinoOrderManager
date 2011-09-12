package br.com.artefino.ordermanager.client.ui.login;

import br.com.artefino.ordermanager.client.ui.login.handlers.SignInPageUiHandlers;

import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class LoginView extends ViewWithUiHandlers<SignInPageUiHandlers>
		implements LoginPresenter.MyView {

	private static final String DEFAULT_USER_NAME = "";
	private static final String DEFAULT_PASSWORD = "";
	private static final String LOGO = "logo.png";
	private static final String NAME_LABEL = "ESTAMPARIA ARTE FINO";

	private VLayout panel;

	private final TextItem userNameField;
	private final PasswordItem passwordField;
	private final Button signInButton;

	public LoginView() {

		panel = new VLayout();
		panel.setWidth100();
		panel.setHeight100();
		panel.setAlign(VerticalAlignment.CENTER);

		VLayout vLayoutLogin = new VLayout(8);
		vLayoutLogin.setWidth(300);
		vLayoutLogin.setHeight(100);
		vLayoutLogin.setLayoutAlign(Alignment.CENTER);


		// initialise the Logo image
		Img logo = new Img(LOGO, 64, 69);
		logo.setStyleName("mastheadLogo");

		// initialise the Name label
		Label name = new Label();
		name.setStyleName("mastheadName");
		name.setContents(NAME_LABEL);

		HLayout hLayoutLogo = new HLayout();
		hLayoutLogo.setMembers(logo, name);
		hLayoutLogo.setAutoHeight();

		userNameField = new TextItem();
		userNameField.setTitle("Usuário");
		userNameField.setDefaultValue(DEFAULT_USER_NAME);

		passwordField = new PasswordItem();
		passwordField.setTitle("Senha");
		passwordField.setDefaultValue(DEFAULT_PASSWORD);

		DynamicForm dynamicForm = new DynamicForm();
		dynamicForm.setFields(userNameField, passwordField);
		dynamicForm.setStyleName("containerPadrao");

		signInButton = new Button("Entrar");

		vLayoutLogin.addMember(hLayoutLogo);
		vLayoutLogin.addMember(dynamicForm);
		vLayoutLogin.addMember(signInButton);

		panel.addMember(vLayoutLogin);
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
