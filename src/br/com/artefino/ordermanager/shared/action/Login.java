package br.com.artefino.ordermanager.shared.action;

import br.com.artefino.ordermanager.shared.vo.UsuarioVo;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

@GenDispatch(isSecure = false, serviceName = UnsecuredActionImpl.DEFAULT_SERVICE_NAME)
public class Login {

  @In(1) String login;
  @In(2) String password;
  @Out(1) String sessionKey;
  @Out(2) UsuarioVo usuario;

}

