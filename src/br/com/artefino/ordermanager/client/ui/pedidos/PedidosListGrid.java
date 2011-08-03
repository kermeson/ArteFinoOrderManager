package br.com.artefino.ordermanager.client.ui.pedidos;

import java.util.List;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.model.PedidoRecord;
import br.com.artefino.ordermanager.shared.vo.PedidoVo;

import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class PedidosListGrid extends ListGrid {
	public PedidosListGrid() {
		super();

		setSelectionType(SelectionStyle.SINGLE);

		ListGridField listGridFieldId = new ListGridField(PedidoRecord.ID,
				PedidoRecord.ID_NAME);
		listGridFieldId.setType(ListGridFieldType.INTEGER);
		listGridFieldId.setWidth(25);

		ListGridField listGridFieldNomeCliente = new ListGridField(
				PedidoRecord.NOME_CLIENTE, ArteFinoOrderManager.getConstants()
						.cliente());

		ListGridField listGridFieldQtdItens = new ListGridField(
				PedidoRecord.QTD_ITENS, ArteFinoOrderManager.getConstants()
						.quantidadeItens());

		ListGridField listGridFieldValorTotal = new ListGridField(
				PedidoRecord.VALOR_TOTAL, ArteFinoOrderManager.getConstants()
						.valorTotal());
		// listGridFieldValorTotal.setCellFormatter(new CellFormatter() {
		// @Override
		// public String format(Object value, ListGridRecord record,
		// int rowNum, int colNum) {
		// String valueFormated = "";
		// if (value != null) {
		// String tipoPessoa = record.getAttribute("tipoPessoa");
		// if (tipoPessoa.equals("1")) {
		// valueFormated = FormatadorUtil.formatarMascaraCPF(Long
		// .parseLong(value.toString()));
		// } else if (tipoPessoa.equals("2")) {
		// valueFormated = FormatadorUtil.formatarMascaraCNPJ(Long
		// .parseLong(value.toString()));
		// }
		// }
		// return valueFormated;
		// }
		// });

		this.setFields(new ListGridField[] { listGridFieldId,
				listGridFieldNomeCliente, listGridFieldQtdItens,
				listGridFieldValorTotal });
	}

	public void setResultSet(List<PedidoVo> pedidos) {

		PedidoRecord[] PedidoRecords = new PedidoRecord[pedidos.size()];

		for (int i = 0; i < pedidos.size(); i++) {
			PedidoRecords[i] = createPedidoRecord(pedidos.get(i));
		}

		// populate the List Grid
		this.setData(PedidoRecords);
	}

	private PedidoRecord createPedidoRecord(PedidoVo pedidoVo) {
		return new PedidoRecord(pedidoVo.getId().intValue(), pedidoVo
				.getCliente().getNome(), pedidoVo.getItens().size(), pedidoVo
				.getValorTotalItens());
	}
}
