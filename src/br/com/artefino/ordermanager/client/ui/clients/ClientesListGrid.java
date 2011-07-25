package br.com.artefino.ordermanager.client.ui.clients;

import java.util.List;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;
import br.com.artefino.ordermanager.client.model.ClienteRecord;
import br.com.artefino.ordermanager.shared.vo.ClienteVo;

import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class ClientesListGrid extends ListGrid {
	public ClientesListGrid() {
		super();

		ListGridField listGridFieldId = new ListGridField(ClienteRecord.ID, ClienteRecord.ID_NAME);
		
		ListGridField listGridFieldNome = new ListGridField(ClienteRecord.NOME,
				ArteFinoOrderManager.getConstants().nome());
		ListGridField listGridFieldTipoPessoa = new ListGridField(
				ClienteRecord.TIPO_PESSOA, ArteFinoOrderManager.getConstants()
						.tipoPessoa());
		ListGridField listGridFieldEndereco = new ListGridField(
				ClienteRecord.ENDERECO, ArteFinoOrderManager.getConstants()
						.endereco());
		this.setFields(new ListGridField[] { listGridFieldId, listGridFieldNome,
				listGridFieldTipoPessoa, listGridFieldEndereco });
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
		return new ClienteRecord(clienteVo.getId(), clienteVo.getNome(),
				clienteVo.getEndereco(), clienteVo.getTipoPessoa());
	}
}
