package br.com.artefino.ordermanager.client.ui.despesas;

import java.util.List;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.model.DespesaRecord;
import br.com.artefino.ordermanager.client.util.FormatadorUtil;
import br.com.artefino.ordermanager.shared.vo.DespesaVo;

import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class DespesasListGrid extends ListGrid {
	public DespesasListGrid() {
		super();

		setSelectionType(SelectionStyle.SINGLE);

		ListGridField listGridFieldId = new ListGridField(DespesaRecord.ID,
				DespesaRecord.ID_NAME);
		listGridFieldId.setType(ListGridFieldType.INTEGER);
		listGridFieldId.setWidth(25);

		ListGridField listGridFieldDescricao = new ListGridField(
				DespesaRecord.DESCRICAO, ArteFinoOrderManager.getConstants()
						.descricao());

		ListGridField listGridFieldCategoria = new ListGridField(
				DespesaRecord.NOME_CATEGORIA, ArteFinoOrderManager
						.getConstants().categoria());

		ListGridField listGridFieldData = new ListGridField(
				DespesaRecord.DATA_CADASTRO, ArteFinoOrderManager
						.getConstants().dataCadastro());
		listGridFieldData.setCellFormatter(FormatadorUtil
				.getCellFormatterData());

		ListGridField listGridFieldValor = new ListGridField(
				DespesaRecord.VALOR, ArteFinoOrderManager.getConstants()
						.valor());
		listGridFieldValor.setCellFormatter(FormatadorUtil
				.getCellFormatterMoeda());

		this.setFields(new ListGridField[] { listGridFieldId,
				listGridFieldDescricao, listGridFieldCategoria,
				listGridFieldData, listGridFieldValor });
	}

	public void setResultSet(List<DespesaVo> despesas) {

		DespesaRecord[] clienteRecords = new DespesaRecord[despesas.size()];

		for (int i = 0; i < despesas.size(); i++) {
			clienteRecords[i] = createDespesaRecord(despesas.get(i));
		}

		// populate the List Grid
		this.setData(clienteRecords);
	}

	private DespesaRecord createDespesaRecord(DespesaVo despesaVo) {
		return new DespesaRecord(despesaVo.getId().intValue(),	despesaVo.getCategoria().getNome(), despesaVo.getDescricao(),
				despesaVo.getDataCadastro(), despesaVo.getValor().doubleValue());
	}
}
