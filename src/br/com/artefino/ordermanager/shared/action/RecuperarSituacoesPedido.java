package br.com.artefino.ordermanager.shared.action;

import java.util.List;

import br.com.artefino.ordermanager.shared.vo.SituacaoPedidoVo;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.Out;
import com.gwtplatform.dispatch.shared.ActionImpl;

@GenDispatch(isSecure = false, serviceName = ActionImpl.DEFAULT_SERVICE_NAME)
public class RecuperarSituacoesPedido {

	  @Out(1) List<SituacaoPedidoVo> situacaoPedidoVos;
}


