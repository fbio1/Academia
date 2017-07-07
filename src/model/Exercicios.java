package model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="exercicios")
public class Exercicios implements Serializable{
	private static final long serialVersionUID = -1130630737396473958L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id_exercicios")
	private int id;
	
	@Column(name="nome", nullable=false)
	@NotNull (message="Nome não pode ser vazio")
	private String nome;
	
	@Column(name="repeticao", nullable=false)
	@NotNull (message="Repeticao não pode ser vazio")
	private int repeticao;
	
	@Column(name="carga", nullable=false)
	@NotNull (message="Carga não pode ser vazio")
	private int carga;
	
	@Column(name="tipo", nullable=false)
	@NotNull (message="Tipo não pode ser vazio")
	private Tipo tipo;

//	@ManyToMany(mappedBy="exercicios")
//	private List<Treino> listaTreino = new ArrayList<Treino>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getRepeticao() {
		return repeticao;
	}

	public void setRepeticao(int repeticao) {
		this.repeticao = repeticao;
	}

	public int getCarga() {
		return carga;
	}

	public void setCarga(int carga) {
		this.carga = carga;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

//	public List<Treino> getListaTreino() {
//		return listaTreino;
//	}
//
//	public void setListaTreino(List<Treino> listaTreino) {
//		this.listaTreino = listaTreino;
//	}	
}
