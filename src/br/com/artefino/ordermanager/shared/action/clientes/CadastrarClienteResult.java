package br.com.artefino.ordermanager.shared.action.clientes;

import com.gwtplatform.dispatch.shared.Result;
import java.lang.Long;

public class CadastrarClienteResult implements Result {

	private static final long serialVersionUID = 8629699111449194214L;
	private Long id;

	@SuppressWarnings("unused")
	private CadastrarClienteResult() {
		// For serialization only
	
	}

	public CadastrarClienteResult(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
