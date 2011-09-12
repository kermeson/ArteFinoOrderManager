package br.com.artefino.ordermanager.client.ui.main;

import java.util.Date;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.ArteFinoOrderManagerConstants;
import br.com.artefino.ordermanager.client.data.MenuPrincipalDataSource;
import br.com.artefino.ordermanager.client.ui.main.handlers.MainUIHandlers;
import br.com.artefino.ordermanager.client.ui.widgets.ApplicationMenu;
import br.com.artefino.ordermanager.client.ui.widgets.Cabecalho;
import br.com.artefino.ordermanager.client.ui.widgets.NavigationPane;
import br.com.artefino.ordermanager.client.ui.widgets.NavigationPaneHeader;
import br.com.artefino.ordermanager.client.ui.widgets.NavigationPaneSectionListGrid;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Cursor;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class MainPageView extends ViewWithUiHandlers<MainUIHandlers> implements
		MainPagePresenter.MyView {

	// NORTH_HEIGHT = MASTHEAD_HEIGHT + APPLICATION_MENU_HEIGHT +
	// NAVIGATION_PANE_HEADER_HEIGHT
	private static final int NORTH_HEIGHT = 85;
	private static final int DEFAULT_MENU_WIDTH = 70;
	private static final String DEFAULT_MARGIN = "0px";

	private final Cabecalho masthead;
	private final ApplicationMenu applicationMenu;
	private final NavigationPaneHeader navigationPaneHeader;
	// private final NavigationPane navigationPane;

	private VLayout panel;
	private HLayout northLayout;
	private HLayout southLayout;
	private VLayout westLayout;
	private VLayout content;
	private NavigationPaneSectionListGrid menuListGrid;
	private Label lblUsuarioLogado;
	private Label lblBoasVindas;
	private Label lblSair;

	private static final ArteFinoOrderManagerConstants constants = ArteFinoOrderManager
			.getConstants();
	private static final String NAME = "name";

	@Inject
	public MainPageView(Cabecalho masthead, ApplicationMenu applicationMenu,
			NavigationPaneHeader navigationPaneHeader,
			NavigationPane navigationPane) {
		this.masthead = masthead;
		this.applicationMenu = applicationMenu;
		this.navigationPaneHeader = navigationPaneHeader;

		menuListGrid = new NavigationPaneSectionListGrid(
				MenuPrincipalDataSource.getInstance());
		// this.navigationPane = navigationPane;

		// get rid of scroll bars, and clear out the window's built-in margin,
		// because we want to take advantage of the entire client area
		Window.enableScrolling(false);
		Window.setMargin(DEFAULT_MARGIN);

		// initialise the main layout container
		panel = new VLayout();
		panel.setWidth100();
		panel.setHeight100();

		// initialise the North layout container
		northLayout = new HLayout();
		northLayout.setHeight(NORTH_HEIGHT);

		// Label para informa o nome do usuário logado na aplicação
		lblUsuarioLogado = new Label();
		lblUsuarioLogado.setStyleName("boasVindas");
		lblUsuarioLogado.setID("nomeUsuario");
		lblUsuarioLogado.setWidth100();

		lblBoasVindas = new Label("Bem vindo(a), ");
		lblBoasVindas.setStyleName("boasVindas");
		lblBoasVindas.setWidth(70);

		// Container para a barra de navegação lado direito
		HLayout hlyBarraNavDir = new HLayout();
		hlyBarraNavDir.setWidth(310);
		hlyBarraNavDir.setHeight(20);
		hlyBarraNavDir.setStyleName("barraNavegacaoDir");

		hlyBarraNavDir.addMember(lblBoasVindas);
		hlyBarraNavDir.addMember(lblUsuarioLogado);

		// label para a data e versão
		String data = DateTimeFormat.getFormat("EEEE, dd 'de' MMMM 'de' yyyy.")
				.format(new Date());

		Label lblData = new Label(data);
		lblData.setWidth(250);
		lblData.setStyleName("barraNavEsqData");

		// Botão de sair
		lblSair = new Label("Sair");
		lblSair.setWidth(60);
		lblSair.setIcon("icons/16/exit.png");
		lblSair.setStyleName("barraNavEsq");
		lblSair.setCursor(Cursor.HAND);
		lblSair.setTooltip("Sair");

		HLayout hlyBarraNavEsq = new HLayout();
		hlyBarraNavEsq.setWidth100();
		hlyBarraNavEsq.setHeight(20);
		hlyBarraNavEsq.setAlign(Alignment.RIGHT);
		hlyBarraNavEsq.setStyleName("barraNavegacaoEsq");
		hlyBarraNavEsq.addMember(lblData);
		hlyBarraNavEsq.addMember(lblSair);

		// Container para a barra de navegação
		HLayout hlyBarraNavegacao = new HLayout();
		hlyBarraNavegacao.setWidth100();
		hlyBarraNavegacao.setHeight(20);
		hlyBarraNavegacao.addMember(hlyBarraNavDir);
		hlyBarraNavegacao.addMember(hlyBarraNavEsq);
		hlyBarraNavegacao.setStyleName("barraNavegacao");

		// initialise the nested layout container
		VLayout vLayout = new VLayout();
		vLayout.addMember(this.masthead);
		vLayout.addMember(hlyBarraNavegacao);
		vLayout.addMember(this.navigationPaneHeader);

		// add the nested layout container to the North layout container
		northLayout.addMember(vLayout);

		// initialise the West layout container
		westLayout = new VLayout();
		westLayout.setWidth("20%");
		westLayout.addMember(menuListGrid);

		// initialise the South layout container
		southLayout = new HLayout();

		// add the North and South layout containers to the main layout
		// container
		panel.addMember(northLayout);

		content = new VLayout();
		content.setStyleName("contextArea");

		southLayout.setMembers(westLayout, content);

		panel.addMember(southLayout);

		bindCustomUiHandlers();

	}

	/**
	 * SmartGWT Event and GWT Handler Mapping should be done here.
	 */
	protected void bindCustomUiHandlers() {
		lblSair.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (getUiHandlers() != null) {
					getUiHandlers().onLabelSairClicked();
				}

			}
		});

		// initialise the ToolBar and register its handlers
		initApplicationMenu();

		// initialise the NavigationPane and register its handlers
		initNavigationPane();
	}

	@Override
	public Widget asWidget() {
		return panel;
	}

	private void initApplicationMenu() {

		applicationMenu.addMenu(constants.newActivityMenuName(),
				DEFAULT_MENU_WIDTH, constants.newActivityMenuItemNames(), null);
		applicationMenu.addMenu(constants.newRecordMenuName(),
				DEFAULT_MENU_WIDTH, constants.newRecordMenuItemNames(), null);
		// Menu goToMenu = applicationMenu.addMenu(constants.goToMenuName(),
		// DEFAULT_MENU_WIDTH - 30);
		// applicationMenu.addSubMenu(goToMenu, constants.salesMenuItemName(),
		// constants.salesMenuItemNames(),
		// null);
		// applicationMenu.addSubMenu(goToMenu,
		// constants.settingsMenuItemName(),
		// constants.settingsMenuItemNames(),
		// null);
		// applicationMenu.addSubMenu(goToMenu,
		// constants.resourceCentreMenuItemName(),
		// constants.resourceCentreMenuItemNames(),
		// null);
		// applicationMenu.addMenu(constants.toolsMenuName(),
		// DEFAULT_MENU_WIDTH - 30, constants.toolsMenuItemNames(),
		// null);
		// applicationMenu.addMenu(constants.helpMenuName(),
		// DEFAULT_MENU_WIDTH - 30, constants.helpMenuItemNames(),
		// null);
	}

	private void initNavigationPane() {
		// navigationPane.addSection(ArteFinoOrderManager.getConstants()
		// .menuPrincipalStackSectionName(), MenuPrincipalDataSource
		// .getInstance());

		// navigationPane.addRecordClickHandler(ArteFinoOrderManager
		// .getConstants().menuPrincipalStackSectionName(),
		// new RecordClickHandler() {
		// @Override
		// public void onRecordClick(RecordClickEvent event) {
		// onRecordClicked(event);
		// }
		// });

		menuListGrid.addRecordClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(RecordClickEvent event) {
				onRecordClicked(event);
			}
		});

	}

	@Override
	public void setInSlot(Object slot, Widget content) {
		Log.debug("setInSlot()");

		if (slot == MainPagePresenter.TYPE_SetContextAreaContent) {
			if (content != null) {
				this.content.setMembers((VLayout) content);
				// southLayout.setMembers(westLayout, (VLayout) content);
			}
		} else {
			super.setInSlot(slot, content);
		}
	}

	// @Override
	// public NavigationPane getNavigationPane() {
	// return navigationPane;
	// }

	@Override
	public NavigationPaneHeader getNavigationPaneHeader() {
		return navigationPaneHeader;
	}

	private void onRecordClicked(RecordClickEvent event) {
		Record record = event.getRecord();
		String place = record.getAttributeAsString(NAME);

		if (getUiHandlers() != null) {
			getUiHandlers().onNavigationPaneSectionClicked(place);
		}
	}

	@Override
	public NavigationPaneSectionListGrid getMenuPricipalListGrid() {
		return menuListGrid;
	}

	@Override
	public void setNomeUsuario(String login) {
		lblUsuarioLogado.setContents(login);

	}

}
