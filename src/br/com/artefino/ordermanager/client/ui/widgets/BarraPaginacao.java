package br.com.artefino.ordermanager.client.ui.widgets;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;

public class BarraPaginacao extends HLayout {

	private static final String STATUSBAR_HEIGHT = "23px";
	public static final String TOOLSTRIP_WIDTH = "*";

	private final Label totalResultsLabel;

	private final ToolStrip toolStrip;
	private final BotaoBarraPaginacao resultSetFirstButton;
	private final BotaoBarraPaginacao resultSetPreviousButton;
	private final Label pageNumberLabel;
	private final BotaoBarraPaginacao resultSetNextButton;
	private final BotaoBarraPaginacao resultSetLastButton;

	public BarraPaginacao() {
		super();

		// initialise the StatusBar layout container
		this.setStyleName("crm-StatusBar");
		this.setHeight(STATUSBAR_HEIGHT);

		// initialise the Selected label
		totalResultsLabel = new Label();
		totalResultsLabel.setStyleName("crm-StatusBar-Total");
		totalResultsLabel.setAlign(Alignment.LEFT);
		totalResultsLabel.setOverflow(Overflow.HIDDEN);

		// initialise the StatusBar's ToolStrip
		toolStrip = new ToolStrip();
		toolStrip.setStyleName("crm-StatusBar-ToolStrip");
		toolStrip.setHeight(STATUSBAR_HEIGHT);
		toolStrip.setWidth(TOOLSTRIP_WIDTH);
		toolStrip.setAlign(Alignment.RIGHT);
		toolStrip.setPadding(2);
		toolStrip.setMembersMargin(5);

		// initialise the Result Set Next button
		resultSetLastButton = new BotaoBarraPaginacao();
		resultSetLastButton.setIcon("paginacao/resultsetlast.png");
		resultSetLastButton.setTooltip(ArteFinoOrderManager.getConstants()
				.ultimaPagina());

		// initialise the Result Set First button
		resultSetFirstButton = new BotaoBarraPaginacao();
		resultSetFirstButton.setIcon("paginacao/resultsetfirst.png");
		resultSetFirstButton.setTooltip(ArteFinoOrderManager.getConstants()
				.primeiraPagina());
		// requires resultsetfirst_Disabled.png
		resultSetFirstButton.disable();

		// initialise the Result Set Previous button
		resultSetPreviousButton = new BotaoBarraPaginacao();
		resultSetPreviousButton.setIcon("paginacao/resultsetprevious.png");
		resultSetPreviousButton.setTooltip(ArteFinoOrderManager.getConstants()
				.paginaAnterior());
		// requires resultsetprevious_Disabled.png
		resultSetPreviousButton.disable();

		// initialise the Page Number label
		pageNumberLabel = new Label();
		pageNumberLabel.setStyleName("crm-StatusBar-PageNumberLabel");
		pageNumberLabel.setTitle("");

		// initialise the Result Set Next button
		resultSetNextButton = new BotaoBarraPaginacao();
		resultSetNextButton.setIcon("paginacao/resultsetnext.png");
		resultSetNextButton.setTooltip(ArteFinoOrderManager.getConstants()
				.proximaPagina());
		// requires resultsetnext_Disabled.png

		// add the Selected label to the StatusBar
		this.addMember(totalResultsLabel);

		// add the buttons to the ToolStrip
		toolStrip.addMember(resultSetFirstButton);
		toolStrip.addMember(resultSetPreviousButton);
		toolStrip.addMember(pageNumberLabel);
		toolStrip.addMember(resultSetNextButton);
		toolStrip.addMember(resultSetLastButton);

		// add the ToolStrip to the StatusBar
		this.addMember(toolStrip);
	}

	public Label getSelectedLabel() {
		return totalResultsLabel;
	}

	public BotaoBarraPaginacao getResultSetFirstButton() {
		return resultSetFirstButton;
	}

	public BotaoBarraPaginacao getResultSetPreviousButton() {
		return resultSetPreviousButton;
	}

	public Label getPageNumberLabel() {
		return pageNumberLabel;
	}

	public BotaoBarraPaginacao getResultSetNextButton() {
		return resultSetNextButton;
	}

	public BotaoBarraPaginacao getResultSetLastButton() {
		return resultSetLastButton;
	}
	
	public ToolStrip getToolStrip() {
		return toolStrip;
	}
}
