package com.silvio.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ItemList implements Serializable {
	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotEmpty
	@Size(max=50)
	private String nome;
	
	@NotEmpty
	@Size(max=120)
	private String descricao;
	
	@DateTimeFormat
	private Date dataInicio;
	
	@DateTimeFormat
	private Date dataFim;
	
	@ManyToOne
	@JoinColumn(name="usuario_id", referencedColumnName="id")
	private Usuario usuario;
	
	@Enumerated(EnumType.STRING)
	private TipoLista tipoLista;
	
	public ItemList() {}
	
	public ItemList(String nome, String descricao, Date dataInicio, Date dataFim, Usuario usuario, TipoLista tipoLista) {
		this.nome = nome;
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.usuario = usuario;
		this.tipoLista = tipoLista;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TipoLista getTipoLista() {
		return tipoLista;
	}

	public void setTipolista(TipoLista tipoLista) {
		this.tipoLista = tipoLista;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemList other = (ItemList) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
