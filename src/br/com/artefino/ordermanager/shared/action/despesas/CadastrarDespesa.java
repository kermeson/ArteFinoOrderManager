package br.com.artefino.ordermanager.shared.action.despesas;

import br.com.artefino.ordermanager.shared.vo.DespesaVo;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;
import com.gwtplatform.dispatch.shared.ActionImpl;

@GenDispatch(isSecure = true, serviceName = ActionImpl.DEFAULT_SERVICE_NAME)
public class CadastrarDespesa {
	@In(1) DespesaVo despesa;
	@Out(1) Long id;
}
