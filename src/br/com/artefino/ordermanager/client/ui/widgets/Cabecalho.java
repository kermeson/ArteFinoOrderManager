package br.com.artefino.ordermanager.client.ui.widgets;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class Cabecalho extends HLayout {

	private static final int MASTHEAD_HEIGHT = 75;

	private static final String WEST_WIDTH = "50%";
	private static final String EAST_WIDTH = "50%";
	private static final String LOGO = "logo.png";
	private static final String NAME_LABEL = "ESTAMPARIA ARTE FINO";

	public Cabecalho() {
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


		// initialise the East layout container
		VLayout eastLayout = new VLayout();
		eastLayout.setAlign(Alignment.RIGHT);
		eastLayout.setAutoHeight();
		eastLayout.setWidth(EAST_WIDTH);

		// initialise the Name label
		Label labelSistemaPedido = new Label();
		labelSistemaPedido.setStyleName("tituloSistema");
		labelSistemaPedido.setContents("SISTEMA DE PEDIDOS");
		labelSistemaPedido.setWidth100();
		labelSistemaPedido.setAutoHeight();
		eastLayout.addMember(labelSistemaPedido);

		// add the West and East layout containers to the Masthead layout
		// container
		this.addMember(westLayout);
		this.addMember(eastLayout);
	}

}
