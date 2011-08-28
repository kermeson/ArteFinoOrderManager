package br.com.artefino.ordermanager.shared.action.despesas.categorias;

import br.com.artefino.ordermanager.shared.vo.CategoriaDespesaVo;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;
import com.gwtplatform.dispatch.shared.ActionImpl;

@GenDispatch(isSecure = true, serviceName = ActionImpl.DEFAULT_SERVICE_NAME)
public class CadastrarCategoriaDespesa {
	@In(1) CategoriaDespesaVo categoria;
	@Out(1) Long id;
}
