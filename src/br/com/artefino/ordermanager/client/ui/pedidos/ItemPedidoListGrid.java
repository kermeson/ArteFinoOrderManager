package br.com.artefino.ordermanager.client.ui.pedidos;

import java.util.ArrayList;
import java.util.List;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.model.ItemPedidoRecord;
import br.com.artefino.ordermanager.client.ui.widgets.ListGridFieldDecimal;
import br.com.artefino.ordermanager.client.ui.widgets.ListGridFieldInteger;
import br.com.artefino.ordermanager.client.ui.widgets.TextAreaItem;
import br.com.artefino.ordermanager.shared.vo.ItemPedidoVo;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.CharacterCasing;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.HoverCustomizer;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;

public class ItemPedidoListGrid extends ListGrid {
	private ListGridField listGridFieldReferencia;
	private ListGridFieldInteger listGridFieldQuantidade;
	private ListGridFieldDecimal listGridFieldValorUnitario;
	private ListGridField listGridFieldRemover;
	private ListGridFieldDecimal listGridFieldValorTotal;
	private ListGridField listGridFieldDescricao;

	public ItemPedidoListGrid() {
		super();

		setSelectionType(SelectionStyle.SINGLE);
		setCanAutoFitFields(false);
		setCanResizeFields(false);
		setCanFreezeFields(false);
		setCanEdit(true);

		TextItem textItemRef = new TextItem();
		textItemRef.setCharacterCasing(CharacterCasing.UPPER);
		textItemRef.setLength(10);
		textItemRef.setWidth(100);

		listGridFieldReferencia = new ListGridField(
				ItemPedidoRecord.REFERENCIA, ArteFinoOrderManager
						.getConstants().referencia());
		listGridFieldReferencia.setEditorType(textItemRef);
		listGridFieldReferencia.setWidth(100);

		TextAreaItem textAreaItemDescricao = new TextAreaItem();
		textAreaItemDescricao.setCharacterCasing(CharacterCasing.UPPER);
		listGridFieldDescricao = new ListGridField(ItemPedidoRecord.DESCRICAO,
				ArteFinoOrderManager.getConstants().descricao());
		listGridFieldDescricao.setEditorType(textAreaItemDescricao);

		listGridFieldQuantidade = new ListGridFieldInteger(
				ItemPedidoRecord.QUANTIDADE, ArteFinoOrderManager
						.getConstants().quantidadeItens());
		listGridFieldQuantidade.setWidth(80);
		listGridFieldQuantidade.setLimit(5);
		listGridFieldQuantidade.setRequired(true);

		listGridFieldValorUnitario = new ListGridFieldDecimal(
				ItemPedidoRecord.VALOR_UNITARIO, ArteFinoOrderManager
						.getConstants().valorUnitario());
		listGridFieldValorUnitario.setWidth(150);
		listGridFieldValorUnitario.setLimit(5);
		listGridFieldValorUnitario.setPrefix(ArteFinoOrderManager
				.getConstants().prefixoMoeda());
		listGridFieldValorUnitario.setRequired(true);

		listGridFieldValorTotal = new ListGridFieldDecimal(
				ItemPedidoRecord.VALOR_TOTAL, ArteFinoOrderManager
						.getConstants().valorTotal());
		listGridFieldValorTotal.setWidth(150);
		listGridFieldValorTotal.setLimit(10);
		listGridFieldValorTotal.setPrefix(ArteFinoOrderManager.getConstants()
				.prefixoMoeda());
		listGridFieldValorTotal.setRequired(true);
		listGridFieldValorTotal.setCanEdit(false);

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

		this.setFields(new ListGridField[] { listGridFieldReferencia,
				listGridFieldDescricao, listGridFieldQuantidade,
				listGridFieldValorUnitario, listGridFieldValorTotal,
				listGridFieldRemover });
	}

	public void setResultSet(List<ItemPedidoVo> itens) {

		ItemPedidoRecord[] itensRecord = new ItemPedidoRecord[itens.size()];

		for (int i = 0; i < itens.size(); i++) {
			itensRecord[i] = createItemPedidoRecord(itens.get(i));
		}

		// populate the List Grid
		this.setData(itensRecord);
	}

	private ItemPedidoRecord createItemPedidoRecord(ItemPedidoVo itemPedidoVo) {
		return new ItemPedidoRecord(itemPedidoVo.getId().intValue(),
				itemPedidoVo.getReferencia(), itemPedidoVo.getQuantidadeItens()
						.intValue(), itemPedidoVo.getValorUnitario()
						.doubleValue(), itemPedidoVo.getValorTotal(),
				itemPedidoVo.getDescricao());
	}

	public void addRecordExcluirClickHandler(
			RecordClickHandler recordClickHandler) {
		listGridFieldRemover.addRecordClickHandler(recordClickHandler);
	}

	public List<ItemPedidoVo> getItens() {
		List<ItemPedidoVo> itens = new ArrayList<ItemPedidoVo>();
		if (getRecords() != null) {
			for (int i = 0; i < getRecords().length; i++) {
				ItemPedidoRecord itemPedidoRecord = new ItemPedidoRecord(
						getRecords()[i].getJsObj());
				itens.add(createItemPedidoVo(itemPedidoRecord));
			}
		}
		return itens;
	}

	private ItemPedidoVo createItemPedidoVo(ItemPedidoRecord itemPedidoRecord) {
		ItemPedidoVo itemPedidoVo = new ItemPedidoVo();
		itemPedidoVo.setReferencia(itemPedidoRecord.getReferencia());
		itemPedidoVo.setQuantidadeItens(itemPedidoRecord.getQuantidade());
		itemPedidoVo.setValorUnitario(itemPedidoRecord.getValorUnitario());
		itemPedidoVo.setDescricao(itemPedidoRecord.getDescricao());
		return itemPedidoVo;
	}

	public void removerItens() {
		setData(new ListGridRecord[0]);
	}
}
