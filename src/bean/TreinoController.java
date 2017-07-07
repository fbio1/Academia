package bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dao.ExerciciosDaoImpl;
import dao.TreinoDaoImpl;
import model.Cliente;
import model.Exercicios;
import model.Instrutor;
import model.Tipo;
import model.Treino;

@ManagedBean
@SessionScoped
public class TreinoController {
	private Treino treino;
	private TreinoDaoImpl treinodao;
	private List<Treino> listatreino;
	private Exercicios exercicios;
	private ExerciciosDaoImpl exerciciosdao;

	private List<Tipo> listaTipo;
	
	private Cliente cliente;
	private Instrutor instrutor;	
	
	
	public TreinoController(){
		this.treino = new Treino();
		this.treinodao = new TreinoDaoImpl();
		this.listatreino = new ArrayList<Treino>();
		this.listatreino = treinodao.list();
		this.cliente = new Cliente();
		this.exercicios = new Exercicios();
		this.exerciciosdao = new ExerciciosDaoImpl();		
		this.listaTipo = Arrays.asList(Tipo.values());
	}
	
	public void salvar(){		
		FacesContext fc = FacesContext.getCurrentInstance();		
		if(treino.getId() == 0){		
			FacesMessage messagem = new FacesMessage("Treino cadastrado com sucesso!");
			messagem.setSeverity(FacesMessage.SEVERITY_INFO);
			fc.addMessage(null, messagem);
			
			
			ExternalContext ec = fc.getExternalContext();
			HttpSession s = (HttpSession) ec.getSession(true);
			s.getAttribute("instrutor");
			instrutor = (Instrutor) s.getAttribute("instrutor");	
			instrutor.getId();
			
			instrutor.adicionarTreino(treino);
			
			treino.setCliente(cliente);
			
			treino.getExercicios().add(exercicios);
			
			treinodao.save(treino);
			
			this.treino = new Treino();
			this.exercicios = new Exercicios();
			this.listatreino = treinodao.list();
		}else{
			FacesMessage messagem = new FacesMessage("Treino atualizado com sucesso!");
			messagem.setSeverity(FacesMessage.SEVERITY_INFO);
			fc.addMessage(null, messagem);
			treinodao.update(treino);
			this.treino = new Treino();
			this.listatreino = treinodao.list();
		}
		
	}

	public void remover(Treino treino){   
    	FacesContext fc = FacesContext.getCurrentInstance();		
		FacesMessage messagem = new FacesMessage("Treino removido com sucesso!");
		messagem.setSeverity(FacesMessage.SEVERITY_INFO);
		fc.addMessage(null, messagem);	
    	treino.setDataRemocao(Calendar.getInstance().getTime());   	
    	treinodao.update(treino);  
    	this.treino = new Treino();
		this.listatreino = treinodao.list();
	}   
    
    public String editar(Treino treino){
    	this.treino = treino;  
    	return "cadastrartreino.xhtml";
    }
    
    public String enviarDados(Cliente novocliente){
    	cliente = novocliente;
    	System.out.println(cliente.getId());
    	return "cadastrarTreino.xhtml";
    }

	public Treino getTreino() {
		return treino;
	}
	
	public void setTreino(Treino treino) {
		this.treino = treino;
	}

	public TreinoDaoImpl getTreinodao() {
		return treinodao;
	}

	public void setTreinodao(TreinoDaoImpl treinodao) {
		this.treinodao = treinodao;
	}

	public List<Treino> getListatreino() {
		return listatreino;
	}

	public void setListatreino(List<Treino> listatreino) {
		this.listatreino = listatreino;
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

	public Exercicios getExercicios() {
		return exercicios;
	}

	public void setExercicios(Exercicios exercicios) {
		this.exercicios = exercicios;
	}

	public ExerciciosDaoImpl getExerciciosdao() {
		return exerciciosdao;
	}

	public void setExerciciosdao(ExerciciosDaoImpl exerciciosdao) {
		this.exerciciosdao = exerciciosdao;
	}

	public List<Tipo> getListaTipo() {
		return listaTipo;
	}
		
}
