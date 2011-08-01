package br.com.artefino.ordermanager.client.ui.clientes;

import java.util.List;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.model.ClienteRecord;
import br.com.artefino.ordermanager.client.util.FormatadorUtil;
import br.com.artefino.ordermanager.shared.vo.ClienteVo;

import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class ClientesListGrid extends ListGrid {
	public ClientesListGrid() {
		super();

		setSelectionType(SelectionStyle.SINGLE);

		ListGridField listGridFieldId = new ListGridField(ClienteRecord.ID,
				ClienteRecord.ID_NAME);
		listGridFieldId.setType(ListGridFieldType.INTEGER);
		listGridFieldId.setWidth(25);

		ListGridField listGridFieldNome = new ListGridField(ClienteRecord.NOME,
				ArteFinoOrderManager.getConstants().nome());
		ListGridField listGridFieldTipoPessoa = new ListGridField(
				ClienteRecord.TIPO_PESSOA, ArteFinoOrderManager.getConstants()
						.tipoPessoa());
		listGridFieldTipoPessoa.setCellFormatter(new CellFormatter() {

			@Override
			public String format(Object value, ListGridRecord record,
					int rowNum, int colNum) {
				String valueFormated = "";
				if (value != null) {
					if (value.toString().equalsIgnoreCase("1")) {
						valueFormated = ArteFinoOrderManager.getConstants()
								.fisica();
					} else if (value.toString().equalsIgnoreCase("2")) {
						valueFormated = ArteFinoOrderManager.getConstants()
								.juridica();
					}
				}
				return valueFormated;
			}
		});

		ListGridField listGridFieldCNPJF = new ListGridField(
				ClienteRecord.CNPJF, ArteFinoOrderManager.getConstants()
						.cnpjf());
		listGridFieldCNPJF.setCellFormatter(new CellFormatter() {
			@Override
			public String format(Object value, ListGridRecord record,
					int rowNum, int colNum) {
				String valueFormated = "";
				if (value != null) {
					String tipoPessoa = record.getAttribute("tipoPessoa");
					if (tipoPessoa.equals("1")) {
						valueFormated = FormatadorUtil.formatarMascaraCPF(Long
								.parseLong(value.toString()));
					} else if (tipoPessoa.equals("2")) {
						valueFormated = FormatadorUtil.formatarMascaraCNPJ(Long
								.parseLong(value.toString()));
					}
				}
				return valueFormated;
			}
		});

		ListGridField listGridFieldEndereco = new ListGridField(
				ClienteRecord.ENDERECO, ArteFinoOrderManager.getConstants()
						.endereco());
		this.setFields(new ListGridField[] { listGridFieldId,
				listGridFieldNome, listGridFieldTipoPessoa, listGridFieldCNPJF,
				listGridFieldEndereco });
	}

	public void setResultSet(List<ClienteVo> clientesVo) {

		ClienteRecord[] clienteRecords = new ClienteRecord[clientesVo.size()];

		for (int i = 0; i < clientesVo.size(); i++) {
			clienteRecords[i] = createClienteRecord(clientesVo.get(i));
		}

		// populate the List Grid
		this.setData(clienteRecords);
	}

	private ClienteRecord createClienteRecord(ClienteVo clienteVo) {
		return new ClienteRecord(clienteVo.getId().intValue(), clienteVo
				.getNome(), clienteVo.getEndereco(), clienteVo.getTipoPessoa()
				.intValue(), clienteVo.getCnpjf().longValue());
	}
}
