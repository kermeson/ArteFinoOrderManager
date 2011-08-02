package br.com.artefino.ordermanager.client.ui.clientes;

import java.util.List;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.model.ClienteRecord;
import br.com.artefino.ordermanager.client.util.FormatadorUtil;
import br.com.artefino.ordermanager.shared.vo.ClienteVo;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;

public class ClientesSimplesListGrid extends ListGrid {
	private ListGridField listGridFieldSelecionar;

	public ClientesSimplesListGrid() {
		super();

		setSelectionType(SelectionStyle.SINGLE);


		ListGridField listGridFieldNome = new ListGridField(ClienteRecord.NOME,
				ArteFinoOrderManager.getConstants().nome());
		listGridFieldNome.setWidth(350);

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

		listGridFieldSelecionar = new ListGridField();
		listGridFieldSelecionar.setType(ListGridFieldType.ICON);
		listGridFieldSelecionar.setCanFilter(false);
		listGridFieldSelecionar.setCanSort(false);
		listGridFieldSelecionar.setAlign(Alignment.CENTER);
		listGridFieldSelecionar.setCanHide(false);
		listGridFieldSelecionar.setCellIcon("icons/16/ok.png");
		listGridFieldSelecionar.setWidth(30);

		this.setFields(new ListGridField[] { listGridFieldNome, listGridFieldCNPJF, listGridFieldSelecionar });
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

	public void addRecordSelecionarClickHandler(
			RecordClickHandler recordClickHandler) {
		listGridFieldSelecionar.addRecordClickHandler(recordClickHandler);
	}
}
