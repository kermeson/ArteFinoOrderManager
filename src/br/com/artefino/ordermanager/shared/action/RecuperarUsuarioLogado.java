package br.com.artefino.ordermanager.shared.action;

import br.com.artefino.ordermanager.shared.vo.UsuarioVo;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.Out;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

@GenDispatch(isSecure = false, serviceName = UnsecuredActionImpl.DEFAULT_SERVICE_NAME)
public class RecuperarUsuarioLogado {
	@Out(1)	UsuarioVo usuario;
}
