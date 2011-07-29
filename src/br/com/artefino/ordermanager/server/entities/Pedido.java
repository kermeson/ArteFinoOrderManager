package br.com.artefino.ordermanager.server.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;

import br.com.artefino.ordermanager.shared.vo.ClienteVo;

@Entity
@Table(name = "TB_PEDIDO")
public class Pedido {

	private Long id;

	private Cliente cliente;

	private Date dataCadastro;

	private List<ItemPedido> itens = new ArrayList<ItemPedido>();

	// JPA requires a no-argument constructor
	public Pedido() {

	}

	public Pedido(ClienteVo clienteVo) {
		this.id = clienteVo.getId();
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "PID_CLIENTE")
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_CADASTRO")
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@SuppressWarnings("deprecation")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "PID_PEDIDO", nullable = false)
	@Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	public ClienteVo converterParaVo() {
		ClienteVo clienteVo = new ClienteVo();
		clienteVo.setId(this.getId());
		return clienteVo;
	}

}
