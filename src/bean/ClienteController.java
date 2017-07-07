package bean;

import model.Administrador;
import model.Cliente;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dao.AdministradorDaoImpl;
import dao.ClienteDaoImpl;


@ManagedBean
@SessionScoped
public class ClienteController {

    private Cliente cliente;  
    private ClienteDaoImpl clientedao;
    private List<Cliente> listaclientes;
    private Administrador administrador;
    private AdministradorDaoImpl administradordao;

      
    public ClienteController() {    	
    	this.cliente = new Cliente();
    	this.clientedao = new ClienteDaoImpl();
    	this.listaclientes = new ArrayList<Cliente>();
        this.listaclientes = clientedao.list();
        
        administrador = new Administrador();
        administradordao = new AdministradorDaoImpl();

        this.cliente.getMatricula().setDataMatricula(Calendar.getInstance().getTime());        
    }        
    
	public void salvar() throws Exception{ 
		boolean achou = false;		
		FacesContext fc = FacesContext.getCurrentInstance();
		if(cliente.getId() == 0){		
			for(Cliente c: listaclientes){
				if(c.getCpf().equals(cliente.getCpf())){
					achou = true;
					System.out.println("Entrou no if do CPF");
					FacesMessage messagem = new FacesMessage("Cliente ja cadastrado!");
					messagem.setSeverity(FacesMessage.SEVERITY_ERROR);
					fc.addMessage(null, messagem);					
					break;					
				}			
			}
				if(achou == false){
					System.out.println("Não achou");
					FacesMessage messagem = new FacesMessage("Cliente cadastrado com sucesso!");
					messagem.setSeverity(FacesMessage.SEVERITY_INFO);
					fc.addMessage(null, messagem);
					
					cliente.getEndereco().setCliente(cliente);					
										
					ExternalContext ec = fc.getExternalContext();
					HttpSession s = (HttpSession) ec.getSession(true);
					s.getAttribute("administrador");
					administrador = (Administrador) s.getAttribute("administrador");	
					administrador.getId();							
										
					cliente.getMatricula().setCliente(cliente);
					
					administrador.adicionarMatricula(cliente.getMatricula());
								
					System.out.println(cliente.toString());
					
					clientedao.save(cliente);
					
			    	this.cliente = new Cliente();			    	
			    	this.listaclientes = clientedao.list();			    	
			}
		}else{			
	    	FacesMessage messagem = new FacesMessage("Cliente atualizado com sucesso!");
			messagem.setSeverity(FacesMessage.SEVERITY_INFO);
			fc.addMessage(null, messagem);	
			clientedao.update(cliente);
			this.cliente = new Cliente();
			this.listaclientes = clientedao.list();
		}		
    	achou=false;
    }
    
    public void remover(Cliente cliente){   
    	FacesContext fc = FacesContext.getCurrentInstance();		
		FacesMessage messagem = new FacesMessage("Cliente removido com sucesso!");
		messagem.setSeverity(FacesMessage.SEVERITY_INFO);
		fc.addMessage(null, messagem);	
    	cliente.setDataRemocao(Calendar.getInstance().getTime());
    	cliente.getEndereco().setDataRemocao(Calendar.getInstance().getTime());
    	cliente.getMatricula().setDataRemocao(Calendar.getInstance().getTime());
    	clientedao.update(cliente);
    	this.cliente = new Cliente();
    	this.listaclientes = clientedao.list();
	}   
    
    public String editar(Cliente cliente){
    	this.cliente = cliente;  
    	return "cadastrarCliente.xhtml";
    }
    
    public String pagamento(){
    	FacesContext fc = FacesContext.getCurrentInstance();		
		FacesMessage messagem = new FacesMessage("Pagemnto feito com sucesso!");
		messagem.setSeverity(FacesMessage.SEVERITY_INFO);
		fc.addMessage(null, messagem);	
		return "";
    }
    
 	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ClienteDaoImpl getClientedao() {
		return clientedao;
	}

	public void setClientedao(ClienteDaoImpl clientedao) {
		this.clientedao = clientedao;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public AdministradorDaoImpl getAdministradordao() {
		return administradordao;
	}

	public void setAdministradordao(AdministradorDaoImpl administradordao) {
		this.administradordao = administradordao;
	}

	public List<Cliente> getListaclientes() {
		return listaclientes;
	}

	public void setListaclientes(List<Cliente> listaclientes) {
		this.listaclientes = listaclientes;
	}

}
