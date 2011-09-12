package br.com.artefino.ordermanager.client.ui.pedidos;

import java.util.List;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.model.PedidoRecord;
import br.com.artefino.ordermanager.client.ui.widgets.ListGridFieldDecimal;
import br.com.artefino.ordermanager.client.ui.widgets.ListGridFieldInteger;
import br.com.artefino.ordermanager.client.util.FormatadorUtil;
import br.com.artefino.ordermanager.shared.vo.PedidoVo;

import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class PedidosListGrid extends ListGrid {
	public PedidosListGrid() {
		super();

		setSelectionType(SelectionStyle.SINGLE);
		setCanAutoFitFields(false);
		setCanFreezeFields(false);

		ListGridField listGridFieldId = new ListGridField(PedidoRecord.ID,
				PedidoRecord.ID_NAME);
		listGridFieldId.setType(ListGridFieldType.INTEGER);
		listGridFieldId.setWidth(25);

		ListGridField listGridFieldNomeCliente = new ListGridField(
				PedidoRecord.NOME_CLIENTE, ArteFinoOrderManager.getConstants()
						.cliente());

		ListGridField listGridFieldSituacao = new ListGridField(
				PedidoRecord.SITUACAO, ArteFinoOrderManager.getConstants()
						.situacao());
		listGridFieldSituacao.setWidth(150);

		ListGridField listGridFieldDataCadastro = new ListGridField(
				PedidoRecord.DATA_CADASTRO, ArteFinoOrderManager.getConstants()
						.dataCadastro());
		listGridFieldDataCadastro.setWidth(110);
		listGridFieldDataCadastro.setCellFormatter(FormatadorUtil
				.getCellFormatterData());

		ListGridField listGridFieldQtdItens = new ListGridFieldInteger(
				PedidoRecord.QTD_ITENS, ArteFinoOrderManager.getConstants()
						.quantidadeItens());
		listGridFieldQtdItens.setWidth(80);

		ListGridField listGridFieldValorTotal = new ListGridFieldDecimal(
				PedidoRecord.VALOR_TOTAL, ArteFinoOrderManager.getConstants()
						.valorTotal());
		listGridFieldValorTotal.setCellFormatter(FormatadorUtil
				.getCellFormatterMoeda());
		listGridFieldValorTotal.setWidth(180);

		this.setFields(new ListGridField[] { listGridFieldId,
				listGridFieldNomeCliente, listGridFieldSituacao, listGridFieldDataCadastro,
				listGridFieldQtdItens, listGridFieldValorTotal });
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
				.getCliente().getNome(), pedidoVo.getItens().size(),
				pedidoVo.getValorTotal(), pedidoVo.getDataCadastro(),
				pedidoVo.getSituacao().getNome());
	}
}
