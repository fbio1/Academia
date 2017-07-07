package bean;

import model.Administrador;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.AdministradorDaoImpl;

@ManagedBean
@SessionScoped
public class AdministradorController {

    private Administrador administrador;
    private List<Administrador> listaAdministrador;   
    private AdministradorDaoImpl administradordao;
      
    public AdministradorController() {    	
    	this.administrador = new Administrador();
    	administradordao = new AdministradorDaoImpl();
    	listaAdministrador = new ArrayList<Administrador>();
        this.listaAdministrador = administradordao.list();
    }        
    
	public void salvar(){ 
		boolean achou = false;		
		FacesContext fc = FacesContext.getCurrentInstance();
		if(administrador.getId() == 0){		
			for(Administrador a: listaAdministrador){
				if(a.getCpf().equals(administrador.getCpf())){
					achou = true;					
					FacesMessage messagem = new FacesMessage("Administrador ja cadastrado!");
					messagem.setSeverity(FacesMessage.SEVERITY_ERROR);
					fc.addMessage(null, messagem);
					break;
				}			
			}
				if(achou == false){
					FacesMessage messagem = new FacesMessage("Administrador cadastrado com sucesso!");
					messagem.setSeverity(FacesMessage.SEVERITY_INFO);
					fc.addMessage(null, messagem);		
					
					administradordao.save(administrador);	
			    	this.administrador = new Administrador();
			    	this.listaAdministrador = administradordao.list();		    	
			}
		}else{			
	    	FacesMessage messagem = new FacesMessage("Administrador atualizado com sucesso!");
			messagem.setSeverity(FacesMessage.SEVERITY_INFO);
			fc.addMessage(null, messagem);	
			administradordao.update(administrador);
			this.administrador = new Administrador();
	    	this.listaAdministrador = administradordao.list();	
		}		
    	achou=false;
    }
    
    public void remover(Administrador administrador){   
    	FacesContext fc = FacesContext.getCurrentInstance();		
		FacesMessage messagem = new FacesMessage("Administrador removido com sucesso!");
		messagem.setSeverity(FacesMessage.SEVERITY_INFO);
		fc.addMessage(null, messagem);	
		administrador.setDataRemocao(Calendar.getInstance().getTime());
    	administradordao.update(administrador);      	
    	this.listaAdministrador = administradordao.list();	
	}  
    
    public String editar(Administrador administrador){
    	this.administrador = administrador;
    	return "cadastrarAdministrador.xhtml";
    }

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public List<Administrador> getListaAdministrador() {
		return listaAdministrador;
	}

	public void setListaAdministrador(List<Administrador> listaAdministrador) {
		this.listaAdministrador = listaAdministrador;
	}

	public AdministradorDaoImpl getAdministradordao() {
		return administradordao;
	}

	public void setAdministradordao(AdministradorDaoImpl administradordao) {
		this.administradordao = administradordao;
	}  
   
}
