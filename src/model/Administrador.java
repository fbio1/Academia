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
@Table(name="administrador")
public class Administrador implements Serializable{
  	private static final long serialVersionUID = -1673750081738709257L;
  	
  	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id_admin")
	private int id;
  	
  	//Matricula
  	@OneToMany(mappedBy="administrador", orphanRemoval=true)
  	@LazyCollection(LazyCollectionOption.FALSE)
  	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.PERSIST})
  	private List<MatriculaAluno> listaMatricula = new ArrayList<MatriculaAluno>();
  	
  	public void adicionarMatricula(MatriculaAluno matricula){
  		listaMatricula.add(matricula);
		matricula.setAdministrador(this);
	}
  	
  	public void removerMatricula(MatriculaAluno matricula){
  		listaMatricula.remove(matricula);
  		matricula.setAdministrador(null);
  	}
  	
  	//Pagamento
  	@OneToMany(mappedBy="administrador")
  	@Cascade({CascadeType.ALL})
  	private List<Pagamento> listapagamento = new ArrayList<Pagamento>();
  	
  	public void adicionarPagamentoadm(Pagamento pagamento1){
  		listapagamento.add(pagamento1);
		pagamento1.setAdministrador(this);
	}
  	
  	public void removePagamentoadm(Pagamento pagamento1){
  		listapagamento.remove(pagamento1);
  		pagamento1.setAdministrador(null);
  	}
  	
	@Column(name="nome", nullable=false)
	@NotNull (message="Nome não pode ser vazio")
    private String nome;
	
	@Column(name="login", unique=true, nullable=false)
	@NotNull (message="Login não pode ser vazio")
    private String login;
	
	@Column(name="senha", nullable=false)
	@NotNull (message="Senha não pode ser vazio")
	@Size(min = 3, message = "A senha deve conter no minimo 3 dígitos")
    private String senha;
	
	@Column(name="cpf", nullable=false)
	@NotNull (message="CPF não pode ser vazio")
    private String cpf;	
		
	@Column(name="salario", nullable=false)
	@NotNull (message="Salario não pode ser vazio")
	private double salario;
	
	@Column(name="funcao", nullable=false)
	@NotNull (message="Funcao não pode ser vazio")
	private String funcao;
	
	@Column(name="dataRemocao", nullable=true)
	@Temporal(TemporalType.DATE)
	private Date dataRemocao;
	
	public Administrador(){
		super();
	}
	
	public Date getDataRemocao() {
		return dataRemocao;
	}

	public void setDataRemocao(Date dataRemocao) {
		this.dataRemocao = dataRemocao;
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

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public List<MatriculaAluno> getListaMatricula() {
		return listaMatricula;
	}

	public void setListaMatricula(List<MatriculaAluno> listaMatricula) {
		this.listaMatricula = listaMatricula;
	}

	public List<Pagamento> getListapagamento() {
		return listapagamento;
	}

	public void setListapagamento(List<Pagamento> listapagamento) {
		this.listapagamento = listapagamento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((dataRemocao == null) ? 0 : dataRemocao.hashCode());
		result = prime * result + ((funcao == null) ? 0 : funcao.hashCode());
		result = prime * result + id;
		result = prime * result + ((listaMatricula == null) ? 0 : listaMatricula.hashCode());
		result = prime * result + ((listapagamento == null) ? 0 : listapagamento.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		long temp;
		temp = Double.doubleToLongBits(salario);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
		Administrador other = (Administrador) obj;
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
		if (funcao == null) {
			if (other.funcao != null)
				return false;
		} else if (!funcao.equals(other.funcao))
			return false;
		if (id != other.id)
			return false;
		if (listaMatricula == null) {
			if (other.listaMatricula != null)
				return false;
		} else if (!listaMatricula.equals(other.listaMatricula))
			return false;
		if (listapagamento == null) {
			if (other.listapagamento != null)
				return false;
		} else if (!listapagamento.equals(other.listapagamento))
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
		return true;
	}    
}
