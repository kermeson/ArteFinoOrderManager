package br.com.artefino.ordermanager.client;

public interface ArteFinoOrderManagerConstants extends
		com.google.gwt.i18n.client.Constants {

	// Menus

	@DefaultStringValue("<u>N</u>ew Activity")
	String newActivityMenuName();

	@DefaultStringValue("Task, Fax, Phone Call, Email, Letter, Appointment")
	String newActivityMenuItemNames();

	@DefaultStringValue("New Re<u>c</u>ord")
	String newRecordMenuName();

	// @DefaultStringValue("Account, Contact, separator, Lead, Opportunity")
	// newRecordMenuItemNames: Konto, Kontakt, separator, Blei, Gelegenheit
	@DefaultStringValue("Account, Contact")
	String newRecordMenuItemNames();

	@DefaultStringValue("<u>G</u>o To")
	String goToMenuName();

	@DefaultStringValue("Sales")
	String salesMenuItemName();

	@DefaultStringValue("Activities, Calendar, Dashboards, Imports, Accounts, Contacts, Queues, Reports")
	String salesMenuItemNames();

	@DefaultStringValue("Activities")
	String activitiesMenuItemName();

	@DefaultStringValue("Calendar")
	String calendarMenuItemName();

	@DefaultStringValue("Dashboards")
	String dashboardsMenuItemName();

	@DefaultStringValue("Imports")
	String importsMenuItemName();

	@DefaultStringValue("Accounts")
	String accountsMenuItemName();

	@DefaultStringValue("Contacts")
	String contactsMenuItemName();

	@DefaultStringValue("Queues")
	String queuesMenuItemName();

	@DefaultStringValue("Reports")
	String reportsMenuItemName();

	@DefaultStringValue("Settings")
	String settingsMenuItemName();

	@DefaultStringValue("Administration, Templates, Data Management")
	String settingsMenuItemNames();

	@DefaultStringValue("Administration")
	String administrationMenuItemName();

	@DefaultStringValue("Resource Centre")
	String resourceCentreMenuItemName();

	@DefaultStringValue("Highlights, Sales, Settings")
	String resourceCentreMenuItemNames();

	@DefaultStringValue("Highlights")
	String highlightsMenuItemName();

	@DefaultStringValue("<u>T</u>ools")
	String toolsMenuName();

	@DefaultStringValue("Import Data, Duplicate Detection, Advanced Find, Options")
	String toolsMenuItemNames();

	@DefaultStringValue("<u>H</u>elp")
	String helpMenuName();

	@DefaultStringValue("Help on this Page, Contents, Serendipity Online, About Serendipity")
	String helpMenuItemNames();

	// Navigation Pane Header

	@DefaultStringValue("Workplace")
	String workplace();

	@DefaultStringValue("Activities")
	String activities();

	// Navigation Pane

	@DefaultStringValue("Sales")
	String salesStackSectionName();

	@DefaultStringValue("Settings")
	String settingsStackSectionName();

	@DefaultStringValue("Resource Centre")
	String resourceCentreStackSectionName();

	// Entity Navigation Pane

	@DefaultStringValue("Details")
	String accountDetailsStackSectionName();

	// ToolBar

	@DefaultStringValue("Novo")
	String newButton();

	@DefaultStringValue("Novo")
	String newButtonTooltip();

	@DefaultStringValue("Run Workflow...")
	String workflowButton();

	@DefaultStringValue("Run Workflow")
	String workflowButtonTooltip();

	@DefaultStringValue("Reports")
	String reportsButton();

	@DefaultStringValue("Reports")
	String reportsButtonTooltip();

	@DefaultStringValue("Print Preview")
	String printPreviewButtonTooltip();

	@DefaultStringValue("Export")
	String exportButtonTooltip();

	@DefaultStringValue("Mail Merge")
	String mailMergeButtonTooltip();

	@DefaultStringValue("Assign")
	String assignButtonTooltip();

	@DefaultStringValue("Excluir")
	String deleteButtonTooltip();

	@DefaultStringValue("Email")
	String emailButtonTooltip();

	@DefaultStringValue("Attach")
	String attachButtonTooltip();

	@DefaultStringValue("Refresh")
	String refreshButtonTooltip();

	// Form ToolBar

	@DefaultStringValue("Save and Close")
	String saveAndCloseButton();

	@DefaultStringValue("Help")
	String helpButton();

	@DefaultStringValue("Salvar")
	String saveButtonTooltip();

	@DefaultStringValue("Save and Close")
	String saveAndCloseButtonTooltip();

	@DefaultStringValue("Ajuda")
	String helpButtonTooltip();

	//
	// Account Form tabs
	//

	@DefaultStringValue("Account: ")
	String accountWindowTitle();

	@DefaultStringValue("General")
	String generalTab();

	@DefaultStringValue("General Information")
	String generalInformationSectionItem();

	@DefaultStringValue("Account Name")
	String accountNameLabel();

	@DefaultStringValue("Account Number")
	String accountNumberLabel();

	@DefaultStringValue("Parent Account")
	String parentAccountLabel();

	@DefaultStringValue("Primary Contact")
	String primaryContactLabel();

	@DefaultStringValue("Relationship Type")
	String relationshipTypeLabel();

	@DefaultStringValue("Main Phone")
	String mainPhoneLabel();

	@DefaultStringValue("Other Phone")
	String otherPhoneLabel();

	@DefaultStringValue("Fax")
	String faxLabel();

	@DefaultStringValue("Web Site")
	String webSiteLabel();

	@DefaultStringValue("Email")
	String emailLabel();

	@DefaultStringValue("Address Information")
	String addressInformationSectionItem();

	@DefaultStringValue("Address Name")
	String addressNameLabel();

	@DefaultStringValue("Street 1")
	String addressLine1Label();

	@DefaultStringValue("Street 2")
	String addressLine2Label();

	@DefaultStringValue("Street 3")
	String addressLine3Label();

	@DefaultStringValue("City")
	String cityLabel();

	@DefaultStringValue("State/Province")
	String stateLabel();

	@DefaultStringValue("ZIP/Postal Code")
	String postalCodeLabel();

	@DefaultStringValue("Country/Region")
	String countryLabel();

	@DefaultStringValue("Address Type")
	String addressTypeLabel();

	@DefaultStringValue("Administration")
	String administrationTab();

	@DefaultStringValue("Notes")
	String notesTab();

	// ActivitiesRecord
	@DefaultStringValue("Activity Type")
	String activityType();

	@DefaultStringValue("Subject")
	String subject();

	@DefaultStringValue("Regarding")
	String regarding();

	@DefaultStringValue("Priority")
	String priority();

	@DefaultStringValue("Start Date")
	String startDate();

	@DefaultStringValue("Due Date")
	String dueDate();

	// AccountsRecord
	@DefaultStringValue("Account Name")
	String accountName();

	@DefaultStringValue("Main Phone")
	String mainPhone();

	@DefaultStringValue("Location")
	String location();

	@DefaultStringValue("Primary Contact")
	String primaryContact();

	@DefaultStringValue("Email (Primary Contact)")
	String emailPrimaryContact();

	// ReportsRecord
	@DefaultStringValue("Report Name")
	String reportName();

	@DefaultStringValue("Report Type")
	String reportType();

	@DefaultStringValue("Modified On")
	String modifiedOn();

	@DefaultStringValue("Description")
	String description();

	// ImportsRecord
	@DefaultStringValue("Import Name")
	String importName();

	@DefaultStringValue("Status")
	String status();

	@DefaultStringValue("Successes")
	String successes();

	@DefaultStringValue("Errors")
	String errors();

	@DefaultStringValue("Total")
	String total();

	@DefaultStringValue("Created On")
	String createdOn();

	@DefaultStringValue("Salvar")
	String salvar();

	@DefaultStringValue("Voltar")
	String voltar();

	@DefaultStringValue("Nome")
	String nome();

	@DefaultStringValue("Tipo de Pessoa")
	String tipoPessoa();

	@DefaultStringValue("Física")
	String fisica();

	@DefaultStringValue("Jurídica")
	String juridica();

	@DefaultStringValue("Endereço")
	String endereco();

	@DefaultStringValue("CPF/CNPJ")
	String cnpjf();

	@DefaultStringValue("Nº")
	String numeroEndereco();

	@DefaultStringValue("Carregando...")
	String mensagemCarregando();

	@DefaultStringValue("Aguarde...")
	String mensagemAguarde();

	@DefaultStringValue("Editar")
	String editar();

	@DefaultStringValue("Clientes")
	String tituloClientes();

	@DefaultStringValue("Informações do cliente")
	String tituloInformacoesCliente();


	@DefaultStringValue("Menu Principal")
	String menuPrincipalStackSectionName();

	@DefaultStringValue("Bairro")
	String bairro();

	@DefaultStringValue("Pedidos")
	String tituloPedidos();

	@DefaultStringValue("Detalhes do pedido")
	String tituloDetalhesPedido();

	@DefaultStringValue("Cliente")
	String cliente();

}
