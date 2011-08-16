package br.com.artefino.ordermanager.client.ui.login;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.ui.login.handlers.SignInPageUiHandlers;
import br.com.artefino.ordermanager.shared.FieldVerifier;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

public class SignInPageView extends ViewWithUiHandlers<SignInPageUiHandlers>
		implements SignInPagePresenter.MyView {

	private static final String DEFAULT_USER_NAME = "Administrator";
	private static final String DEFAULT_PASSWORD = "N0More$ecrets";

	private static String html = "<div>\n"
			+ "<table align=\"center\">\n"
			+ "  <tr>\n"
			+ "<td>&nbsp;</td>\n"
			+ "<td>&nbsp;</td>\n"
			+ "</tr>\n"
			+ "  <tr>\n"
			+ "<td>&nbsp;</td>\n"
			+ "<td>&nbsp;</td>\n"
			+ "</tr>\n"
			+ "  <tr>\n"
			+ "<td>&nbsp;</td>\n"
			+ "<td>&nbsp;</td>\n"
			+ "</tr>\n"
			+ "  <tr>\n"
			+ "<td>&nbsp;</td>\n"
			+ "<td>&nbsp;</td>\n"
			+ "</tr>\n"
			+ "  <tr>\n"
			+ "<td>&nbsp;</td>\n"
			+ "<td>&nbsp;</td>\n"
			+ "</tr>\n"
			+ "  <tr>\n"
			+ "    <td colspan=\"2\" style=\"font-weight:bold;\">LOGIN <img src=\"images/signin.gif\"/></td>\n"
			+ "  </tr>\n" + "  <tr>\n" + "    <td>Usuário</td>\n"
			+ "    <td id=\"userNameFieldContainer\"></td>\n" + "  </tr>\n"
			+ "  <tr>\n" + "    <td>Senha</td>\n"
			+ "    <td id=\"passwordFieldContainer\"></td>\n" + "  </tr>\n"
			+ "  <tr>\n" + "    <td></td>\n"
			+ "    <td id=\"signInButtonContainer\"></td>\n" + "  </tr>\n"
			+ "  <tr>\n" + "<td>&nbsp;</td>\n" + "<td>&nbsp;</td>\n"
			+ "</tr>\n" + "</table>\n" + "</div>\n";

	private HTMLPanel panel;

	private final TextBox userNameField;
	private final PasswordTextBox passwordField;
	private final Button signInButton;

	public SignInPageView() {

		panel = new HTMLPanel(html);

		userNameField = new TextBox();
		passwordField = new PasswordTextBox();
		signInButton = new Button("Entrar");

		userNameField.setText(DEFAULT_USER_NAME);

		// See FieldVerifier
		// Passwords must contain at least 8 characters with at least one digit,
		// one upper case letter, one lower case letter and one special symbol
		// (�@#$%�).
		passwordField.setText(DEFAULT_PASSWORD);

		panel.add(userNameField, "userNameFieldContainer");
		panel.add(passwordField, "passwordFieldContainer");
		panel.add(signInButton, "signInButtonContainer");

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
		return userNameField.getText();
	}

	@Override
	public String getPassword() {
		return passwordField.getText();
	}

	@Override
	public void resetAndFocus() {
		userNameField.setFocus(true);
		userNameField.selectAll();
	}
}
