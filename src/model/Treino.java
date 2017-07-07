package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
@Table(name="treino")
public class Treino implements Serializable{
	private static final long serialVersionUID = 1319701548650034554L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id_treino")
	private int id;
	
	@Column(name="DiaTreino", nullable=false)
	@NotNull (message="O dia da semana do treino não pode ser vazio")
	private String DiaTreino;	
	
	@Column(name="dataRemocao", nullable=true)
	@Temporal(TemporalType.DATE)
	private Date dataRemocao;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "instrutor_id")
	private Instrutor instrutor;

	//Exercicios
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "treino_exercicios", joinColumns = { @JoinColumn(name = "treino_id",
	referencedColumnName = "id_treino") }, inverseJoinColumns = { @JoinColumn(name = "exercicios_id") })
	private List<Exercicios> exercicios = new ArrayList<Exercicios>();
	
//	public void addExercicios(Exercicios nexercicios) {
//		exercicios.add(nexercicios);
//		nexercicios.getListaTreino().add(this);
//	}
//	public void removeExercicios(Exercicios nexercicios) {
//		exercicios.remove(nexercicios);
//		nexercicios.getListaTreino().remove(this);
//	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getDiaTreino() {
		return DiaTreino;
	}

	public void setDiaTreino(String diaTreino) {
		DiaTreino = diaTreino;
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
	public List<Exercicios> getExercicios() {
		return exercicios;
	}
	public void setExercicios(List<Exercicios> exercicios) {
		this.exercicios = exercicios;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DiaTreino == null) ? 0 : DiaTreino.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((dataRemocao == null) ? 0 : dataRemocao.hashCode());
		result = prime * result + ((exercicios == null) ? 0 : exercicios.hashCode());
		result = prime * result + id;
		result = prime * result + ((instrutor == null) ? 0 : instrutor.hashCode());
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
		Treino other = (Treino) obj;
		if (DiaTreino == null) {
			if (other.DiaTreino != null)
				return false;
		} else if (!DiaTreino.equals(other.DiaTreino))
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
		if (exercicios == null) {
			if (other.exercicios != null)
				return false;
		} else if (!exercicios.equals(other.exercicios))
			return false;
		if (id != other.id)
			return false;
		if (instrutor == null) {
			if (other.instrutor != null)
				return false;
		} else if (!instrutor.equals(other.instrutor))
			return false;
		return true;
	}
}
