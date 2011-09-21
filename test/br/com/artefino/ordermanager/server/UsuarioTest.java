package br.com.artefino.ordermanager.server;

import org.junit.Test;

import br.com.artefino.ordermanager.server.businessobject.UsuarioBO;
import br.com.artefino.ordermanager.server.entities.Usuario;
import br.com.artefino.ordermanager.server.util.JPAUtil;
import br.com.artefino.ordermanager.server.util.Security;

import com.allen_sauer.gwt.log.client.Log;
import com.gwtplatform.dispatch.shared.ActionException;

public class UsuarioTest {

	@Test
	public void cadastrarUsuario() {

		Usuario user1 = new Usuario();
		user1.setLogin("maracinthya");
		user1.setAdminstrador("S");

	    String password = "acasadedeus";
	    String hash = Security.md5(password);
	    user1.setSenha(hash);

	    Usuario user2 = new Usuario();
	    user2.setLogin("cosme");
	    user2.setAdminstrador("S");

	    password = "c062064n";
	    hash = Security.md5(password);
	    user2.setSenha(hash);

	    Usuario user3 = new Usuario();
	    user3.setLogin("leila");
	    user3.setAdminstrador("S");

	    password = "300664";
	    hash = Security.md5(password);
	    user3.setSenha(hash);



	    try {
			UsuarioBO.criarUsuario(user1);
			UsuarioBO.criarUsuario(user2);
			UsuarioBO.criarUsuario(user3);
		} catch (ActionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	@Test
	public void atualizarUsuario() {

			    try {
	    	Usuario user3 = UsuarioBO.retornarUsuario("leila");
	    	String salt = Security.randomCharString();
	 	    Log.debug("salt: " + salt);

	 	    String password = "300664";
	 	    String hash = Security.md5(password);
	 	    user3.setSalt(salt);
	 	    user3.setSenha(hash);


	 	   JPAUtil.update(user3);

	 	  Usuario user1 = UsuarioBO.retornarUsuario("maracinthya");
	    	salt = Security.randomCharString();
	 	    Log.debug("salt: " + salt);

	 	    password = "acasadedeus";
	 	    hash = Security.sha256(salt + password);
	 	    user1.setSalt(salt);
	 	    user1.setSenha(hash);


	 	   JPAUtil.update(user1);

		} catch (ActionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


}
