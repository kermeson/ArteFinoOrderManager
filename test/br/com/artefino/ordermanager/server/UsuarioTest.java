package br.com.artefino.ordermanager.server;

import org.junit.Test;

import br.com.artefino.ordermanager.server.businessobject.UsuarioBO;
import br.com.artefino.ordermanager.server.entities.Usuario;
import br.com.artefino.ordermanager.server.util.Security;

import com.allen_sauer.gwt.log.client.Log;
import com.gwtplatform.dispatch.shared.ActionException;

public class UsuarioTest {

	@Test
	public void cadastrarUsuario() {

		Usuario user1 = new Usuario();
		user1.setLogin("Administrator");

	    String salt = Security.randomCharString();
	    Log.debug("salt: " + salt);

	    String password = "12345";
	    String hash = Security.sha256(salt + password);
	    user1.setSalt(salt);
	    user1.setSenha(hash);

	    try {
			UsuarioBO.criarUsuario(user1);
		} catch (ActionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


}
