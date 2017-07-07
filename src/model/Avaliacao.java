package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="avaliacao")
public class Avaliacao implements Serializable{
	private static final long serialVersionUID = 2490636088457856992L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id_avaliacao")
	private int id;
	
	@Column(name="dataavaliacao", nullable=false)
	@NotNull (message="Data Avaliação não pode ser vazio")
	private Date dataavaliacao;
	
	@Column(name="pesocliente", nullable=false)
	@NotNull (message="Peso não pode ser vazio")
	private double pesocliente;
	
	@Column(name="alturacliente", nullable=false)
	@NotNull (message="Altura não pode ser vazio")
	private double alturacliente;
	
	@Column(name="gorduracliente", nullable=false)
	@NotNull (message="Gordura não pode ser vazio")
	private double gorduracliente;
	
	@Column(name="observacao", nullable=true)
	private String observacao;
	
	@Column(name="dataRemocao", nullable=true)
	@Temporal(TemporalType.DATE)
	private Date dataRemocao;
	
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "instrutor_id")
	private Instrutor instrutor;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataavaliacao() {
		return dataavaliacao;
	}

	public void setDataavaliacao(Date dataavaliacao) {
		this.dataavaliacao = dataavaliacao;
	}

	public double getPesocliente() {
		return pesocliente;
	}

	public void setPesocliente(double pesocliente) {
		this.pesocliente = pesocliente;
	}

	public double getAlturacliente() {
		return alturacliente;
	}

	public void setAlturacliente(double alturacliente) {
		this.alturacliente = alturacliente;
	}

	public double getGorduracliente() {
		return gorduracliente;
	}

	public void setGorduracliente(double gorduracliente) {
		this.gorduracliente = gorduracliente;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Instrutor getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;
	}

	public Date getDataRemocao() {
		return dataRemocao;
	}

	public void setDataRemocao(Date dataRemocao) {
		this.dataRemocao = dataRemocao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(alturacliente);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((dataRemocao == null) ? 0 : dataRemocao.hashCode());
		result = prime * result + ((dataavaliacao == null) ? 0 : dataavaliacao.hashCode());
		temp = Double.doubleToLongBits(gorduracliente);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((instrutor == null) ? 0 : instrutor.hashCode());
		result = prime * result + ((observacao == null) ? 0 : observacao.hashCode());
		temp = Double.doubleToLongBits(pesocliente);
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
		Avaliacao other = (Avaliacao) obj;
		if (Double.doubleToLongBits(alturacliente) != Double.doubleToLongBits(other.alturacliente))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (dataRemocao == null) {
			if (other.dataRemocao != null)
				return false;
		} else if (!dataRemocao.equals(other.dataRemocao))
			return false;
		if (dataavaliacao == null) {
			if (other.dataavaliacao != null)
				return false;
		} else if (!dataavaliacao.equals(other.dataavaliacao))
			return false;
		if (Double.doubleToLongBits(gorduracliente) != Double.doubleToLongBits(other.gorduracliente))
			return false;
		if (id != other.id)
			return false;
		if (instrutor == null) {
			if (other.instrutor != null)
				return false;
		} else if (!instrutor.equals(other.instrutor))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (Double.doubleToLongBits(pesocliente) != Double.doubleToLongBits(other.pesocliente))
			return false;
		return true;
	}
	
}
