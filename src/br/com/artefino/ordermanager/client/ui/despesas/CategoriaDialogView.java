package br.com.artefino.ordermanager.client.ui.despesas;

import java.util.List;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.model.CategoriaDespesaRecord;
import br.com.artefino.ordermanager.client.ui.despesas.handlers.CategoriaUIHandlers;
import br.com.artefino.ordermanager.shared.vo.CategoriaDespesaVo;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.CharacterCasing;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CanvasItem;
import com.smartgwt.client.widgets.form.fields.PickerIcon;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.HoverCustomizer;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class CategoriaDialogView extends
		ViewWithUiHandlers<CategoriaUIHandlers> implements
		CategoriaDialogPresenterWidget.MyView {

	private DynamicForm dynamicForm;
	private TextItem textItemNome;
	private PickerIcon pickerIconPesquisarClientes;

	private CategoriaDespesaVo categoriaDespesaVo;
	private Button btnIncluir;
	private Window panel;
	private ListGrid listGridCategoria;
	private ListGridField listGridFieldRemover;
	private ListGridField listGridFieldNome;
	private ListGridField listGridFieldId;

	@Inject
	public CategoriaDialogView() {
		super();
		panel = new Window();
		panel.setShowMinimizeButton(false);
		panel.setIsModal(true);
		panel.setShowModalMask(true);
		panel.setShowShadow(true);
		panel.setShadowDepth(10);
		panel.setSmoothFade(true);
		panel.setWidth(450);
		panel.setHeight(380);
		panel.setTitle(ArteFinoOrderManager.getConstants()
				.gerenciarCategorias());
		panel.setAutoCenter(true);

		VLayout vLayout = new VLayout(8);
		dynamicForm = new DynamicForm();

		textItemNome = new TextItem();
		textItemNome.setTitle(ArteFinoOrderManager.getConstants().nome());
		textItemNome.setWidth(310);
		textItemNome.setLength(100);
		textItemNome.setRequired(true);
		textItemNome.setIcons(pickerIconPesquisarClientes);
		textItemNome.setCharacterCasing(CharacterCasing.UPPER);

		btnIncluir = new Button();
		btnIncluir.setIcon("icons/16/add.png");
		btnIncluir.setTitle(ArteFinoOrderManager.getConstants().incluir());
		btnIncluir.setWidth(100);

		HLayout hLayoutBotoes = new HLayout(5);
		hLayoutBotoes.setMembers(btnIncluir);
		hLayoutBotoes.setAutoHeight();

		CanvasItem canvasItem = new CanvasItem();
		canvasItem.setShowTitle(true);
		canvasItem.setTitle("");
		canvasItem.setCanvas(hLayoutBotoes);
		canvasItem.setWidth(100);

		dynamicForm.setTitleOrientation(TitleOrientation.TOP);
		dynamicForm.setFields(textItemNome, canvasItem);
		dynamicForm.setNumCols(2);
		dynamicForm.setWidth100();

		vLayout.addMember(dynamicForm);
		vLayout.setStyleName("containerPadrao");

		listGridCategoria = new ListGrid();
		listGridCategoria.setSelectionType(SelectionStyle.SINGLE);
		listGridCategoria.setCanAutoFitFields(false);
		listGridCategoria.setCanResizeFields(false);
		listGridCategoria.setCanFreezeFields(false);
		listGridCategoria.setCanEdit(false);

		listGridFieldId = new ListGridField(CategoriaDespesaRecord.ID,
				CategoriaDespesaRecord.ID_NAME);
		listGridFieldId.setType(ListGridFieldType.INTEGER);
		listGridFieldId.setWidth(25);

		listGridFieldNome = new ListGridField(CategoriaDespesaRecord.NOME,
				ArteFinoOrderManager.getConstants().nome());

		// Icone Remover
		listGridFieldRemover = new ListGridField("btnRemover", 25);
		listGridFieldRemover.setType(ListGridFieldType.ICON);
		listGridFieldRemover.setAlign(Alignment.CENTER);
		listGridFieldRemover.setCanFilter(false);
		listGridFieldRemover.setCanSort(false);
		listGridFieldRemover.setCanHide(false);
		listGridFieldRemover.setCanEdit(false);
		listGridFieldRemover.setIcon("icons/16/remove.png");
		listGridFieldRemover.setShowDefaultContextMenu(false);
		listGridFieldRemover.setShowHover(true);
		listGridFieldRemover.setHoverCustomizer(new HoverCustomizer() {

			@Override
			public String hoverHTML(Object value, ListGridRecord record,
					int rowNum, int colNum) {
				return ArteFinoOrderManager.getConstants().remover();
			}
		});

		listGridCategoria.setFields(new ListGridField[] { listGridFieldNome, listGridFieldRemover });

		vLayout.addMember(listGridCategoria);

		panel.addItem(vLayout);

		bindCustomUiHandlers();
	}

	protected void bindCustomUiHandlers() {
		btnIncluir.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (dynamicForm.validate()) {
					if (getUiHandlers() != null) {
						getUiHandlers().onButtonIncluirClicked();
					}
				} else {
					SC.warn(ArteFinoOrderManager.getMessages()
							.nomeCategoriaObrigatorio());
				}
			}
		});

		listGridFieldRemover.addRecordClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(final RecordClickEvent event) {
				final CategoriaDespesaRecord categoriaDespesaRecord = (CategoriaDespesaRecord) event
						.getRecord();

				SC.confirm(
						ArteFinoOrderManager.getMessages()
								.confirmarRemoverCategoria(
										categoriaDespesaRecord.getNome()),
						new BooleanCallback() {
							public void execute(Boolean value) {
								if (value) {
									if (getUiHandlers() != null) {
										getUiHandlers()
												.onRemoverCategoriaClicked(
														categoriaDespesaRecord
																.getId());
									}
								}
							}
						});
			}
		});
	}

	@Override
	public Widget asWidget() {
		return panel;
	}

	@Override
	public void fecharDialogo() {
		panel.destroy();
	}

	@Override
	public void exibirDialogo() {
		panel.show();
	}

	@Override
	public CategoriaDespesaVo getCategoria() {
		if (categoriaDespesaVo == null) {
			categoriaDespesaVo = new CategoriaDespesaVo();
		}
		categoriaDespesaVo.setNome(textItemNome.getValueAsString());
		return categoriaDespesaVo;
	}

	@Override
	public void setResultSet(List<CategoriaDespesaVo> categorias) {
		if (categorias != null) {
			CategoriaDespesaRecord[] categoriasRecord = new CategoriaDespesaRecord[categorias
					.size()];

			for (int i = 0; i < categorias.size(); i++) {
				categoriasRecord[i] = createCategoriaDespesaRecord(categorias
						.get(i));
			}

			// populate the List Grid
			listGridCategoria.setData(categoriasRecord);
		}
	}

	private CategoriaDespesaRecord createCategoriaDespesaRecord(
			CategoriaDespesaVo categoria) {
		return new CategoriaDespesaRecord(categoria.getId().intValue(),
				categoria.getNome());
	}

	@Override
	public void limparFormulario() {
		dynamicForm.clearValues();
	}

}
