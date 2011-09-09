package br.com.artefino.ordermanager.server.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.artefino.ordermanager.shared.vo.UsuarioVo;

@Entity
@Table(name = "TB_USUARIO")
public class Usuario {

	private Long id;

	private String login;

	private String senha;

	private String salt;
	
	private String adminstrador;

	// JPA requires a no-argument constructor
	public Usuario() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "DS_LOGIN")
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "DS_SENHA")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Column(name = "DS_SALT")
	public String getSalt() {
		return salt;
	}

	public void setAdminstrador(String adminstrador) {
		this.adminstrador = adminstrador;
	}

	@Column(name = "FL_ADMIN")
	public String getAdminstrador() {
		return adminstrador;
	}
	
	public UsuarioVo converterParaVo() {
		UsuarioVo usuarioVo = new UsuarioVo();
		usuarioVo.setLogin(getLogin());
		usuarioVo.setAdminstrador(getAdminstrador());
		return usuarioVo;
		
	}

}
