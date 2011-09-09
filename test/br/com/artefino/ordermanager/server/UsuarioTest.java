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
		user1.setLogin("admin");
		user1.setAdminstrador("S");

	    String salt = Security.randomCharString();
	    Log.debug("salt: " + salt);

	    String password = "12345";
	    String hash = Security.sha256(salt + password);
	    user1.setSalt(salt);
	    user1.setSenha(hash);
	    
	    Usuario user2 = new Usuario();
	    user2.setLogin("user");
	    user2.setAdminstrador("N");

	    salt = Security.randomCharString();
	    Log.debug("salt: " + salt);

	    password = "12345";
	    hash = Security.sha256(salt + password);
	    user2.setSalt(salt);
	    user2.setSenha(hash);

	    try {
			UsuarioBO.criarUsuario(user1);
			UsuarioBO.criarUsuario(user2);
		} catch (ActionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


}
