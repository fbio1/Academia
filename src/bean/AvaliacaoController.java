package bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dao.AvaliacaoDaoImpl;
import dao.InstrutorDaoImpl;
import model.Avaliacao;
import model.Cliente;
import model.Instrutor;

@ManagedBean
@SessionScoped
public class AvaliacaoController {
	private Cliente cliente;
	private Instrutor instrutor;	
	private Avaliacao avaliacao;
	private AvaliacaoDaoImpl avaliacaodao;
	private List<Avaliacao> listaAvaliacao;
	
	public AvaliacaoController(){
		this.cliente = new Cliente();
    	//this.instrutor = new Instrutor();
    	this.avaliacao = new Avaliacao();
    	this.avaliacaodao = new AvaliacaoDaoImpl();
    	this.listaAvaliacao = new ArrayList<Avaliacao>();
        this.listaAvaliacao = avaliacaodao.list();
	}
	
	public void salvar(){		
		FacesContext fc = FacesContext.getCurrentInstance();		
		if(avaliacao.getId() == 0){		
			FacesMessage messagem = new FacesMessage("Avaliação cadastrada com sucesso!");
			messagem.setSeverity(FacesMessage.SEVERITY_INFO);
			fc.addMessage(null, messagem);
			
			ExternalContext ec = fc.getExternalContext();
			HttpSession s = (HttpSession) ec.getSession(true);
			s.getAttribute("instrutor");
			instrutor = (Instrutor) s.getAttribute("instrutor");	
			instrutor.getId();
			
			instrutor.adicionarAvaliacao(avaliacao);
			
			avaliacao.setCliente(cliente);
			avaliacaodao.save(avaliacao);
			this.avaliacao = new Avaliacao();
			this.listaAvaliacao = avaliacaodao.list();
		}else{
			FacesMessage messagem = new FacesMessage("Avaliação atualizada com sucesso!");
			messagem.setSeverity(FacesMessage.SEVERITY_INFO);
			fc.addMessage(null, messagem);
			avaliacaodao.update(avaliacao);
			this.avaliacao = new Avaliacao();
			this.listaAvaliacao = avaliacaodao.list();			
		}		
	}

	public void remover(Avaliacao avaliacao){   
    	FacesContext fc = FacesContext.getCurrentInstance();		
		FacesMessage messagem = new FacesMessage("Avaliação removido com sucesso!");
		messagem.setSeverity(FacesMessage.SEVERITY_INFO);
		fc.addMessage(null, messagem);	
    	avaliacao.setDataRemocao(Calendar.getInstance().getTime());   	
    	avaliacaodao.update(avaliacao);  
    	this.avaliacao = new Avaliacao();
    	this.listaAvaliacao = avaliacaodao.list();
	}   
    
    public String editar(Avaliacao avaliacao){
    	this.avaliacao = avaliacao;  
    	return "cadastrarAvaliacao.xhtml";
    }
    
    public String enviarDados(Cliente novocliente){
    	cliente = novocliente;
    	System.out.println(cliente.getId());
    	return "cadastrarAvaliacao.xhtml";
    }
    
    public String visualizarDados(Cliente novocliente){
    	cliente = novocliente;
    	listaAvaliacao = cliente.getListaavaliacao();
    	this.listaAvaliacao = avaliacaodao.list();
    	return "gerenciarAvaliacao.xhmtl";    	
    }
    
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public AvaliacaoDaoImpl getAvaliacaodao() {
		return avaliacaodao;
	}

	public void setAvaliacaodao(AvaliacaoDaoImpl avaliacaodao) {
		this.avaliacaodao = avaliacaodao;
	}

	public List<Avaliacao> getListaAvaliacao() {
		return listaAvaliacao;
	}

	public void setListaAvaliacao(List<Avaliacao> listaAvaliacao) {
		this.listaAvaliacao = listaAvaliacao;
	}		
	
}
