package br.com.artefino.ordermanager.client.ui.widgets;

import com.smartgwt.client.types.SelectionType;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class ToolBar extends HLayout {

	public static final String NEW_BUTTON = "toolbar/new.png";
	public static final String BACK_BUTTON = "toolbar/back.png";
	public static final String NEW_IMPORT_BUTTON = "toolbar/newimport.png";
	public static final String NEW_ACCOUNT_BUTTON = "toolbar/newaccount.png";
	public static final String NEW_CONTACT_BUTTON = "toolbar/newcontact.png";
	public static final String NEW_QUEUE_BUTTON = "toolbar/newqueue.png";
	public static final String NEW_REPORT_BUTTON = "toolbar/newreport.png";

	public static final String PRINT_PREVIEW_BUTTON = "toolbar/printpreview.png";
	public static final String EXPORT_BUTTON = "toolbar/export.png";
	public static final String MAIL_MERGE_BUTTON = "toolbar/mailmerge.png";
	public static final String ASSIGN_BUTTON = "toolbar/assign.png";
	public static final String DELETE_BUTTON = "toolbar/delete.png";
	public static final String EMAIL_BUTTON = "toolbar/sendemail.png";
	public static final String ATTACH_BUTTON = "toolbar/attach.png";
	public static final String REFRESH_BUTTON = "toolbar/refresh.png";

	public static final String WORKFLOW_BUTTON = "toolbar/workflow.png";
	public static final String REPORTS_BUTTON = "toolbar/reports.png";

	public static final String SAVE_BUTTON = "toolbar/save.png";
	public static final String SAVE_AND_CLOSE_BUTTON = "toolbar/saveandclose.png";
	public static final String HELP_BUTTON = "toolbar/help.png";

	public static final String TOOLBAR_HEIGHT = "25px";
	public static final String TOOLSTRIP_WIDTH = "*";

	public static final String EDIT_CLIENT = "toolbar/edit_user.gif";
	public static final String ADD_CLIENT = "toolbar/add_user.png";

	public static final String ADD_ORDER = "toolbar/new.png";
	public static final String PESQUISAR = "toolbar/find.png";
	public static final String EDITAR_PEDIDO = "toolbar/edit.png";
	public static final String ADICIONAR_DESPESA = "toolbar/money_add.png";
	public static final String EDITAR_DESPESA = "toolbar/edit.png";;


	protected final ToolStrip toolStrip;

	public ToolBar() {
		super();

		// initialise the ToolBar layout container
		this.setStyleName("crm-ToolBar");
		this.setHeight(TOOLBAR_HEIGHT);

		// initialise the ToolBar's ToolStrip
		toolStrip = new ToolStrip();
		toolStrip.setHeight(TOOLBAR_HEIGHT);
		toolStrip.setWidth(TOOLSTRIP_WIDTH);

		// add the ToolStrip to the ToolBar's layout container
		this.addMember(toolStrip);
	}

	public ToolStripButton addButton(String title, ClickHandler clickHandler) {
		ToolStripButton button = new ToolStripButton();
		button.setTitle(title);

		if (clickHandler != null) {
			button.addClickHandler(clickHandler);
		}

		toolStrip.addButton(button);

		return button;
	}

	public ToolStripButton addButton(String icon, String tooltip,
			ClickHandler clickHandler) {
		ToolStripButton button = new ToolStripButton();
		button.setIcon(icon);
		button.setTooltip(tooltip);
		if (clickHandler != null) {
			button.addClickHandler(clickHandler);
		}
		toolStrip.addButton(button);

		return button;
	}

	public ToolStripButton addButton(String icon, String tooltip, SelectionType selectionType,
			ClickHandler clickHandler) {
		ToolStripButton button = new ToolStripButton();
		button.setIcon(icon);
		button.setTooltip(tooltip);
		button.setActionType(selectionType);

		if (clickHandler != null) {
			button.addClickHandler(clickHandler);
		}
		toolStrip.addButton(button);

		return button;
	}



	public ToolStripButton addButton(String icon, String title, String tooltip,
			ClickHandler clickHandler) {
		ToolStripButton button = new ToolStripButton();
		button.setIcon(icon);
		button.setTitle(title);
		button.setTooltip(tooltip);

		if (clickHandler != null) {
			button.addClickHandler(clickHandler);
		}
		toolStrip.addButton(button);

		return button;
	}

	public Label addLabel(String contents) {
		Label label = new Label();
		label.setContents(contents);

		toolStrip.addMember(label);

		return label;
	}

	public void addSeparator() {
		toolStrip.addSeparator();
	}
}
