package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity
@Table(name="pagamento")
public class Pagamento implements Serializable{
	
	private static final long serialVersionUID = -237490200922016451L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id_pagamento")
	private int id;
	
	@Column(name="dataPagamento", nullable=false)
	@NotNull (message="DataPagamento não pode ser vazio")
	private Date dataPagamento;
	
	@Column(name="valorPagamento", nullable=false)
	@NotNull (message="ValorPagamento não pode ser vazio")
	private double valorPagamento;
	
	//Cliente
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}	
	
	//Administrador
	@ManyToOne
	@JoinColumn(name="admin_id")
	private Administrador administrador;
	
	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public double getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(double valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((dataPagamento == null) ? 0 : dataPagamento.hashCode());
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(valorPagamento);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Pagamento other = (Pagamento) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (dataPagamento == null) {
			if (other.dataPagamento != null)
				return false;
		} else if (!dataPagamento.equals(other.dataPagamento))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(valorPagamento) != Double.doubleToLongBits(other.valorPagamento))
			return false;
		return true;
	}

}
