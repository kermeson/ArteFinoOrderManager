package br.com.artefino.ordermanager.shared.action.clientes;

import com.gwtplatform.dispatch.shared.ActionImpl;
import br.com.artefino.ordermanager.shared.action.clientes.CadastrarClienteResult;
import br.com.artefino.ordermanager.shared.vo.ClienteVo;

public class CadastrarCliente extends ActionImpl<CadastrarClienteResult> {

	private static final long serialVersionUID = 4591531207136626907L;
	private ClienteVo clienteVo;

	@SuppressWarnings("unused")
	private CadastrarCliente() {
		// For serialization only
	}

	public CadastrarCliente(ClienteVo clienteVo) {
		this.clienteVo = clienteVo;
	}

	public ClienteVo getClienteVo() {
		return clienteVo;
	}	
}
