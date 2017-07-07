package bean;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dao.AdministradorDaoImpl;
import dao.InstrutorDaoImpl;
import model.Administrador;
import model.Instrutor;

@SessionScoped
@ManagedBean
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 3650764898228516750L;
	
	private InstrutorController instrutorController = new InstrutorController();
	private InstrutorDaoImpl instrutordao = new InstrutorDaoImpl();

	private AdministradorController administradorController = new AdministradorController();
	private AdministradorDaoImpl administradordao = new AdministradorDaoImpl();
	
	private String login;
	private String senha;
	public String nome;
	public String nome1;
		
	public String login(){
		List<Administrador> listaAdministrador = administradordao.list();
		List<Instrutor> listaInstrutor = instrutordao.list();
		FacesContext context = FacesContext.getCurrentInstance();
		
		for (Administrador u : listaAdministrador) {			
			if (u.getLogin().equals(login)){
				if(u.getSenha().equals(senha)){					
						ExternalContext ec = context.getExternalContext();
						HttpSession s = (HttpSession) ec.getSession(true);
						s.setAttribute("administrador", u);		
						nome = u.getNome();
						return "/administrador/bemvindoAdm.xhtml?faces-redirect=true";			
				} else {
					FacesMessage messagem = new FacesMessage("Senha inválida!");
					messagem.setSeverity(FacesMessage.SEVERITY_ERROR);
					context.addMessage(null, messagem);					
					System.out.println("erro senha");
					return null;
				}				
			}		
		}		
		
		for (Instrutor u1 : listaInstrutor) {			
			if (u1.getLogin().equals(login)){
				if(u1.getSenha().equals(senha)){						
						ExternalContext ec = context.getExternalContext();
						HttpSession s = (HttpSession) ec.getSession(true);
						s.setAttribute("instrutor", u1);	
						nome1 = u1.getNome();
						return "/instrutor/bemvindoInstrutor.xhtml?faces-redirect=true";
					}
				} else {
					FacesMessage messagem = new FacesMessage("Senha inválida!");
					messagem.setSeverity(FacesMessage.SEVERITY_ERROR);
					context.addMessage(null, messagem);					
					System.out.println("erro senha");
					return null;
				}				
			}	
		
		System.out.println("erro login/senha");
		FacesMessage mensagem = new FacesMessage("Usuário/senha inválidos!");
		mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		context.addMessage(null, mensagem);
		return null;
		
	}
	
	public String sair(){
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externo = context.getExternalContext();
		HttpSession sessao = (HttpSession) externo.getSession(false);
	    sessao.invalidate();	      
		return "/index.xhtml?faces-redirect=true";
	}
	
	public AdministradorController getAdministradorController() {
		return administradorController;
	}

	public void setAdministradorController(AdministradorController administradorController) {
		this.administradorController = administradorController;
	}


	public AdministradorDaoImpl getAdministradordao() {
		return administradordao;
	}

	public void setAdministradordao(AdministradorDaoImpl administradordao) {
		this.administradordao = administradordao;
	}

	public String getLogin() {
		return login;
	}

	public InstrutorController getInstrutorController() {
		return instrutorController;
	}

	public void setInstrutorController(InstrutorController instrutorController) {
		this.instrutorController = instrutorController;
	}

	public InstrutorDaoImpl getInstrutordao() {
		return instrutordao;
	}

	public void setInstrutordao(InstrutorDaoImpl instrutordao) {
		this.instrutordao = instrutordao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome1() {
		return nome1;
	}

	public void setNome1(String nome1) {
		this.nome1 = nome1;
	}
	
}
