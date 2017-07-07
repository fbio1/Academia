package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="matricula")
public class MatriculaAluno implements Serializable{
	private static final long serialVersionUID = 9211209110348469686L;
	
	@Id
	@GeneratedValue(generator = "fk_endereco_id_cliente")
	@org.hibernate.annotations.GenericGenerator(name = "fk_endereco_id_cliente",
	strategy = "foreign", parameters = @Parameter(name = "property", value = "cliente"))
	@Column(name = "id_matriculaAluno")
	private int id;
	
	@Column(name="dataMatricula", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date dataMatricula;
	
	@Column(name="dataVencimento", nullable=false)
	@NotNull (message="DataVencimento não pode ser vazio")
	private Date dataVencimento;
	
	@Column(name="dataRemocao", nullable=true)
	@Temporal(TemporalType.DATE)
	private Date dataRemocao;
	
	public Date getDataRemocao() {
		return dataRemocao;
	}
	public void setDataRemocao(Date dataRemocao) {
		this.dataRemocao = dataRemocao;
	}
	
	//Cliente
	@OneToOne
	//@Cascade({CascadeType.ALL})	
	private Cliente cliente;
	
	//Administrador	
	@ManyToOne
	@JoinColumn(name = "admin_id")
	private Administrador administrador;
			
	public Administrador getAdministrador() {
		return administrador;
	}
	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}
		
	public MatriculaAluno(){
		super();
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDataMatricula() {
		return dataMatricula;
	}
	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((administrador == null) ? 0 : administrador.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((dataMatricula == null) ? 0 : dataMatricula.hashCode());
		result = prime * result + ((dataRemocao == null) ? 0 : dataRemocao.hashCode());
		result = prime * result + ((dataVencimento == null) ? 0 : dataVencimento.hashCode());
		result = prime * result + id;
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
		MatriculaAluno other = (MatriculaAluno) obj;
		if (administrador == null) {
			if (other.administrador != null)
				return false;
		} else if (!administrador.equals(other.administrador))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (dataMatricula == null) {
			if (other.dataMatricula != null)
				return false;
		} else if (!dataMatricula.equals(other.dataMatricula))
			return false;
		if (dataRemocao == null) {
			if (other.dataRemocao != null)
				return false;
		} else if (!dataRemocao.equals(other.dataRemocao))
			return false;
		if (dataVencimento == null) {
			if (other.dataVencimento != null)
				return false;
		} else if (!dataVencimento.equals(other.dataVencimento))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
