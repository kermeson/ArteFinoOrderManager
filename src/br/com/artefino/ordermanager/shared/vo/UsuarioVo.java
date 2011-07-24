package br.com.artefino.ordermanager.shared.vo;

import java.io.Serializable;

public class UsuarioVo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8277710753566370458L;

	private String id;

	private String login;

	private String senha;

	// JPA requires a no-argument constructor
	public UsuarioVo() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}



}
