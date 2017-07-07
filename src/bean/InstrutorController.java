package bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.InstrutorDaoImpl;
import model.Instrutor;

@ManagedBean
@SessionScoped
public class InstrutorController {

    private Instrutor instrutor;
    private List<Instrutor> listaInstrutor;
    private InstrutorDaoImpl instrutorDao;
    
    public InstrutorController() {    	
    	this.instrutor = new Instrutor();
    	instrutorDao = new InstrutorDaoImpl();
    	listaInstrutor = new ArrayList<Instrutor>();
        this.listaInstrutor = instrutorDao.list();
    }        
	
	public void salvar(){    	
		boolean achou = false;		
		FacesContext fc = FacesContext.getCurrentInstance();
		if(instrutor.getId() == 0){
		for(Instrutor i: listaInstrutor){
			if(i.getCpf().equals(instrutor.getCpf())){
				achou = true;
				FacesMessage messagem = new FacesMessage("Instrutor ja cadastrado!");
				messagem.setSeverity(FacesMessage.SEVERITY_ERROR);
				fc.addMessage(null, messagem);				
				break;
			}			
		}
			if(achou == false){
				FacesMessage messagem = new FacesMessage("Instrutor cadastrado com sucesso!");
				messagem.setSeverity(FacesMessage.SEVERITY_INFO);
				fc.addMessage(null, messagem);		 	
				instrutorDao.save(this.instrutor);        
		    	this.instrutor = new Instrutor();
		    	this.listaInstrutor = instrutorDao.list();
			}
		}else{
			FacesMessage messagem = new FacesMessage("Instrutor atualizado com sucesso!");
			messagem.setSeverity(FacesMessage.SEVERITY_INFO);
			fc.addMessage(null, messagem);	
			instrutorDao.update(instrutor);
			this.instrutor = new Instrutor();
			this.listaInstrutor = instrutorDao.list();
		}
    	achou=false;
    }
    
    
    public void remover(Instrutor instrutor){	
    	FacesContext fc = FacesContext.getCurrentInstance();		
		FacesMessage messagem = new FacesMessage("Instrutor removido com sucesso!");
		messagem.setSeverity(FacesMessage.SEVERITY_INFO);
		fc.addMessage(null, messagem);	    
		instrutor.setDataRemocao(Calendar.getInstance().getTime());
		instrutorDao.update(instrutor);   
		this.listaInstrutor = instrutorDao.list();
    }   

    public String editar(Instrutor instrutor){
    	this.instrutor = instrutor;
    	return "cadastrarInstrutor.xhtml";
    }
    
    public String salvarAvaliação(){
    	
    	
    	return "bemvindoInstrutor.xhtml";
    }

	public Instrutor getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;
	}

	public List<Instrutor> getListaInstrutor() {
		return listaInstrutor;
	}

	public void setListaInstrutor(List<Instrutor> listaInstrutor) {
		this.listaInstrutor = listaInstrutor;
	}

	public InstrutorDaoImpl getInstrutorDao() {
		return instrutorDao;
	}

	public void setInstrutorDao(InstrutorDaoImpl instrutorDao) {
		this.instrutorDao = instrutorDao;
	} 
}
