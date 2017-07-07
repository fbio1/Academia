package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="instrutor")
public class Instrutor implements Serializable{
	
	private static final long serialVersionUID = 8660616472742086440L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id_instrutor")
	private int id;
	
	@Column(name="nome", nullable=false)
	@NotNull (message="Nome não pode ser vazio")
    private String nome;
	
	@Column(name="login",  unique=true, nullable=false)
	@NotNull (message="Login não pode ser vazio")
    private String login;
	
	@Column(name="senha", nullable=false)
	@NotNull (message="Senha não pode ser vazio")
	@Size(min = 3, message = "A senha deve conter no minimo 3 dígitos")
    private String senha;
	
	@Column(name="cpf", nullable=false)
	@NotNull (message="CPF não pode ser vazio")
    private String cpf;
	
	@Column(name="telefone", nullable=true)
	@NotNull (message="telefone não pode ser vazio")
    private String telefone;
	
	@Column(name="salario", nullable=false)
	@NotNull (message="Salario não pode ser vazio")
	private double salario;
	
	@Column(name="anoFormacao", nullable=true)
	@NotNull (message="AnoFormacao não pode ser vazio")
	private Date anoFormacao;
	
	@Column(name="dataRemocao", nullable=true)
	@Temporal(TemporalType.DATE)
	private Date dataRemocao;	

	//Avaliacao
	@OneToMany(mappedBy="instrutor")
	@LazyCollection(LazyCollectionOption.FALSE)
  	@Cascade({CascadeType.ALL})
  	private List<Avaliacao> listaavaliacao = new ArrayList<Avaliacao>();
  	
  	public void adicionarAvaliacao(Avaliacao avaliacao){
  		listaavaliacao.add(avaliacao);
  		avaliacao.setInstrutor(this);
	}
  	
  	public void removePagamento(Avaliacao avaliacao){
  		listaavaliacao.remove(avaliacao);
  		avaliacao.setInstrutor(null);
  	}
  	
  	//Treino
  	@OneToMany(mappedBy="instrutor")
  	@LazyCollection(LazyCollectionOption.FALSE)
  	@Cascade({CascadeType.ALL})
  	private List<Treino> listatreino = new ArrayList<Treino>();
  	
  	public void adicionarTreino(Treino treino){
  		listatreino.add(treino);
  		treino.setInstrutor(this);
	}
  	
  	public void removeTreino(Treino treino){
  		listatreino.remove(treino);
  		treino.setInstrutor(null);
  	}
	
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Date getAnoFormacao() {
		return anoFormacao;
	}

	public void setAnoFormacao(Date anoFormacao) {
		this.anoFormacao = anoFormacao;
	}
	
	public Date getDataRemocao() {
		return dataRemocao;
	}

	public void setDataRemocao(Date dataRemocao) {
		this.dataRemocao = dataRemocao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anoFormacao == null) ? 0 : anoFormacao.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((dataRemocao == null) ? 0 : dataRemocao.hashCode());
		result = prime * result + id;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		long temp;
		temp = Double.doubleToLongBits(salario);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
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
		Instrutor other = (Instrutor) obj;
		if (anoFormacao == null) {
			if (other.anoFormacao != null)
				return false;
		} else if (!anoFormacao.equals(other.anoFormacao))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dataRemocao == null) {
			if (other.dataRemocao != null)
				return false;
		} else if (!dataRemocao.equals(other.dataRemocao))
			return false;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (Double.doubleToLongBits(salario) != Double.doubleToLongBits(other.salario))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}
}
