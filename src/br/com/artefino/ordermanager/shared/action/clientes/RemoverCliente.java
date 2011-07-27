package br.com.artefino.ordermanager.shared.action.clientes;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.shared.ActionImpl;

@GenDispatch(isSecure = true, serviceName = ActionImpl.DEFAULT_SERVICE_NAME)
public class RemoverCliente {
	  @In(1) Long idCliente;
}


