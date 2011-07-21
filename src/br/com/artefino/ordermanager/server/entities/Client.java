package br.com.artefino.ordermanager.server.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_USER")
public class Client {

	private String id;

	private String name;

	private String nickname;

	private Date date;

	private String address;

	private String phone;

	private Long cnpjf;

	private String flagTypePerson;

	// JPA requires a no-argument constructor
	public Client() {

	}

	@Id
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "DS_NAME")
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DS_NICKNAME")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "DT_BIRTH")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "DS_ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "DS_PHONE")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "NU_CNPJF")
	public Long getCnpjf() {
		return cnpjf;
	}

	public void setCnpjf(Long cnpjf) {
		this.cnpjf = cnpjf;
	}

	@Column(name = "FL_TYPE_PERSON")
	public String getFlagTypePerson() {
		return flagTypePerson;
	}

	public void setFlagTypePerson(String flagTypePerson) {
		this.flagTypePerson = flagTypePerson;
	}




}
