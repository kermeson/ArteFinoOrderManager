package br.com.artefino.ordermanager.client.ui.pedidos;

import java.util.ArrayList;
import java.util.List;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.model.ItemPedidoRecord;
import br.com.artefino.ordermanager.client.ui.widgets.ListGridFieldDecimal;
import br.com.artefino.ordermanager.client.ui.widgets.ListGridFieldInteger;
import br.com.artefino.ordermanager.shared.vo.ItemPedidoVo;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionStyle;
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

	public ItemPedidoListGrid() {
		super();

		setSelectionType(SelectionStyle.SINGLE);
		setCanAutoFitFields(false);
		setCanResizeFields(false);
		setCanFreezeFields(false);

		listGridFieldReferencia = new ListGridField(
				ItemPedidoRecord.REFERENCIA, ArteFinoOrderManager
						.getConstants().referencia());

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
		listGridFieldValorUnitario.setLimit(10);
		listGridFieldValorUnitario.setPrefix(ArteFinoOrderManager
				.getConstants().prefixoMoeda());
		listGridFieldValorUnitario.setRequired(true);

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
				listGridFieldQuantidade, listGridFieldValorUnitario,
				listGridFieldRemover });
	}

	public void setResultSet(List<ItemPedidoVo> itens) {

		ItemPedidoRecord[] clienteRecords = new
		ItemPedidoRecord[itens.size()];
		
		 for (int i = 0; i < itens.size(); i++) {
		 clienteRecords[i] = createItemPedidoRecord(itens.get(i));
		 }
		
		 // populate the List Grid
		 this.setData(clienteRecords);
	}

	private ItemPedidoRecord createItemPedidoRecord(ItemPedidoVo itemPedidoVo) {
		return new ItemPedidoRecord(itemPedidoVo.getId().intValue(),
				itemPedidoVo.getReferencia(), itemPedidoVo.getQuantidadeItens()
						.intValue(), itemPedidoVo.getValorUnitario()
						.doubleValue());
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
		return itemPedidoVo;
	}

	public void removerItens() {
		setData(new ListGridRecord[0]);		
	}
}
