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
@Table(name="cliente")
public class Cliente implements Serializable{
	private static final long serialVersionUID = 2731503992014985927L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id_cliente")
	private int id;
	
	@Column(name="nome", length=50, nullable=false)
	@NotNull (message="Nome não pode ser vazio")
    private String nome;
	
	@Column(name="cpf", unique=true, nullable=false)
	@NotNull (message="CPF não pode ser vazio")
    private String cpf;
	
	@Column(name="telefone", nullable=false)
	@NotNull (message="Telefone não pode ser vazio")
    private String telefone;
	
	@Column(name="dataNascimento", nullable=false)
	@NotNull (message="Data de Nascimento não pode ser vazio")
    private Date dataNascimento;  
	
	@Column(name="dataRemocao", nullable=true)
	@Temporal(TemporalType.DATE)
	private Date dataRemocao;
	
	//Matricula)
	@OneToOne(mappedBy="cliente")
	@Cascade({CascadeType.ALL})
	private MatriculaAluno matricula;	
	
	//Pagamento
	@OneToMany(mappedBy="cliente")
  	@Cascade({CascadeType.ALL})
  	private List<Pagamento> listapagamento = new ArrayList<Pagamento>();
  	
  	public void adicionarPagamento(Pagamento pagamento){
  		listapagamento.add(pagamento);
		pagamento.setCliente(this);
	}
  	
  	public void removePagamento(Pagamento pagamento){
  		listapagamento.remove(pagamento);
  		pagamento.setCliente(null);
  	}
  	
  	//Endereco
	@OneToOne (mappedBy="cliente")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.PERSIST})
	private Endereco endereco;
	
	
	//Avaliação
	@OneToMany(mappedBy="cliente")
	@LazyCollection(LazyCollectionOption.FALSE)
  	@Cascade({CascadeType.ALL})
  	private List<Avaliacao> listaavaliacao = new ArrayList<Avaliacao>();
  	
  	public void adicionarPagamento(Avaliacao avaliacao){
  		listaavaliacao.add(avaliacao);
  		avaliacao.setCliente(this);
	}
  	
  	public void removePagamento(Avaliacao avaliacao){
  		listaavaliacao.remove(avaliacao);
  		avaliacao.setCliente(null);
  	}
  	
  	//Treino
  	@OneToMany(mappedBy="cliente")
  	@Cascade({CascadeType.ALL})
  	private List<Treino> listatreino = new ArrayList<Treino>();
  	
  	public void adicionarTreino(Treino treino){
  		listatreino.add(treino);
  		treino.setCliente(this);
	}
  	
  	public void removerTreino(Treino treino){
  		listatreino.remove(treino);
  		treino.setCliente(null);
  	}  	
  	
	public Cliente() {
		this.endereco = new Endereco();
		this.matricula = new MatriculaAluno();
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataRemocao() {
		return dataRemocao;
	}

	public void setDataRemocao(Date dataRemocao) {
		this.dataRemocao = dataRemocao;
	}
	

	public MatriculaAluno getMatricula() {
		return matricula;
	}

	public void setMatricula(MatriculaAluno matricula) {
		this.matricula = matricula;
	}

	public List<Pagamento> getListapagamento() {
		return listapagamento;
	}

	public void setListapagamento(List<Pagamento> listapagamento) {
		this.listapagamento = listapagamento;
	}

	public List<Avaliacao> getListaavaliacao() {
		return listaavaliacao;
	}

	public void setListaavaliacao(List<Avaliacao> listaavaliacao) {
		this.listaavaliacao = listaavaliacao;
	}

	public List<Treino> getListatreino() {
		return listatreino;
	}

	public void setListatreino(List<Treino> listatreino) {
		this.listatreino = listatreino;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((dataRemocao == null) ? 0 : dataRemocao.hashCode());
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Cliente other = (Cliente) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (dataRemocao == null) {
			if (other.dataRemocao != null)
				return false;
		} else if (!dataRemocao.equals(other.dataRemocao))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", telefone=" + telefone + ", dataNascimento="
				+ dataNascimento + ", dataRemocao=" + dataRemocao + ", matricula=" + matricula + ", listapagamento="
				+ listapagamento + ", endereco=" + endereco + ", listaavaliacao=" + listaavaliacao + ", listatreino="
				+ listatreino + "]";
	}	
}
