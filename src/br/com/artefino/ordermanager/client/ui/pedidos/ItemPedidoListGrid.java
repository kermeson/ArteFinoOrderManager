package br.com.artefino.ordermanager.client.ui.pedidos;

import java.util.List;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.model.ItemPedidoRecord;
import br.com.artefino.ordermanager.shared.vo.ClienteVo;
import br.com.artefino.ordermanager.shared.vo.ItemPedidoVo;

import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class ItemPedidoListGrid extends ListGrid {
	public ItemPedidoListGrid() {
		super();

		setSelectionType(SelectionStyle.SINGLE);

		ListGridField listGridFieldReferencia = new ListGridField(
				ItemPedidoRecord.REFERENCIA, ArteFinoOrderManager
						.getConstants().referencia());

		ListGridField listGridFieldQuantidade = new ListGridField(
				ItemPedidoRecord.QUANTIDADE, ArteFinoOrderManager
						.getConstants().quantidadeItens());

		ListGridField listGridFieldValorUnitario = new ListGridField(
				ItemPedidoRecord.VALOR_UNITARIO, ArteFinoOrderManager
						.getConstants().valorUnitario());

		this.setFields(new ListGridField[] { listGridFieldReferencia,
				listGridFieldQuantidade, listGridFieldValorUnitario });
	}

	public void setResultSet(List<ClienteVo> clientesVo) {

		// ClienteRecord[] clienteRecords = new
		// ClienteRecord[clientesVo.size()];
		//
		// for (int i = 0; i < clientesVo.size(); i++) {
		// clienteRecords[i] = createItemPedidoRecord(clientesVo.get(i));
		// }
		//
		// // populate the List Grid
		// this.setData(clienteRecords);
	}

	private ItemPedidoRecord createItemPedidoRecord(ItemPedidoVo itemPedidoVo) {
		return new ItemPedidoRecord(itemPedidoVo.getId().intValue(),
				itemPedidoVo.getReferencia(), itemPedidoVo.getQuantidadeItens()
						.intValue(), itemPedidoVo.getValorUnitario()
						.doubleValue());
	}
}
