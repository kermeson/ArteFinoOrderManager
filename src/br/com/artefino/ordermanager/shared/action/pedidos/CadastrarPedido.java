package br.com.artefino.ordermanager.shared.action.pedidos;

import br.com.artefino.ordermanager.shared.vo.PedidoVo;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;
import com.gwtplatform.dispatch.shared.ActionImpl;

@GenDispatch(isSecure = true, serviceName = ActionImpl.DEFAULT_SERVICE_NAME)
public class CadastrarPedido {
	@In(1) PedidoVo pedidoVo;
	@Out(1) Long id;
}
