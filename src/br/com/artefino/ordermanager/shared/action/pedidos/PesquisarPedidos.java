package br.com.artefino.ordermanager.shared.action.pedidos;

import java.util.List;
import java.util.Map;

import br.com.artefino.ordermanager.shared.vo.PedidoVo;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;
import com.gwtplatform.dispatch.shared.ActionImpl;

@GenDispatch(isSecure = true, serviceName = ActionImpl.DEFAULT_SERVICE_NAME)
public class PesquisarPedidos {
	  @In(1) int maxResults;
	  @In(2) int firstResult;
	  @In(3) Map<String, Object> parametros;
	  @Out(1) List<PedidoVo> pedidosVo;
	  @Out(2) Long total;
}


