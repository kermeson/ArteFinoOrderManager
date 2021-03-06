package br.com.artefino.ordermanager.shared.action.clientes;

import br.com.artefino.ordermanager.shared.vo.ClienteVo;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;
import com.gwtplatform.dispatch.shared.ActionImpl;

@GenDispatch(isSecure = true, serviceName = ActionImpl.DEFAULT_SERVICE_NAME)
public class CadastrarCliente {
	@In(1) ClienteVo clienteVo;
	@Out(1) Long id;
}
