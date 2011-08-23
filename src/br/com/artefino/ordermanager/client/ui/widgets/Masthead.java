package br.com.artefino.ordermanager.client.ui.widgets;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class Masthead extends HLayout {

	private static final int MASTHEAD_HEIGHT = 75;

	private static final String WEST_WIDTH = "50%";
	private static final String EAST_WIDTH = "50%";
	private static final String LOGO = "logo.png";
	private static final String NAME_LABEL = "ARTE FINO SERIGRAFIA";
	private static final String SIGNED_IN_USER_LABEL = "<b>Administrator</b>";

	private Label logoutLabel;

	public Masthead() {
		super();

		// Log.debug("Masthead()");

		// initialise the Masthead layout container
		this.setStyleName("masthead");
		this.setHeight(MASTHEAD_HEIGHT);

		// initialise the Logo image
		Img logo = new Img(LOGO, 64, 69);
		logo.setStyleName("mastheadLogo");

		// initialise the Name label
		Label name = new Label();
		name.setStyleName("mastheadName");
		name.setContents(NAME_LABEL);

		// initialise the West layout container
		HLayout westLayout = new HLayout();
		westLayout.setHeight(MASTHEAD_HEIGHT);
		westLayout.setWidth(WEST_WIDTH);
		westLayout.addMember(logo);
		westLayout.addMember(name);

		// initialise the Signed In User label
		Label signedInUser = new Label();
		signedInUser.setStyleName("crm-Masthead-SignedInUser");
		signedInUser.setContents(SIGNED_IN_USER_LABEL);
		signedInUser.setAutoHeight();

		// initialise the Signed In User label
		logoutLabel = new Label();
		logoutLabel.setStyleName("linkSair");
		logoutLabel.setContents(ArteFinoOrderManager.getConstants().sair());
		logoutLabel.setAutoHeight();

		// initialise the East layout container
		VLayout eastLayout = new VLayout();
		eastLayout.setAlign(Alignment.RIGHT);
		eastLayout.setAutoHeight();
		eastLayout.setWidth(EAST_WIDTH);
		eastLayout.addMember(signedInUser);
		eastLayout.addMember(logoutLabel);

		// add the West and East layout containers to the Masthead layout
		// container
		this.addMember(westLayout);
		this.addMember(eastLayout);
	}

	public void addLabelSairClickHandler(ClickHandler handler) {
		logoutLabel.addClickHandler(handler);

	}
}
