import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Administrador;
import model.Cliente;
import util.HibernateUtil;

public class TestePrincipal {

	public static void main(String[] args) {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		Transaction transacao = sessao.beginTransaction();
		
//		Administrador a = new Administrador();
//		a.setCpf("132.868.657-45");
//		a.setFuncao("administrador");
//		a.setNome("Teste");
//		a.setLogin("teste");
//		a.setSenha("123");
//		a.setSalario(1000.0);			
//		sessao.save(a);
		
		Cliente c = new Cliente();
		c.setNome("teste3");
		Date date1 = new GregorianCalendar(1986, Calendar.JANUARY, 03).getTime();
		c.setDataNascimento(date1);		
		c.setCpf("853.868.657-45");		
		c.setTelefone("telefone");	
		c.getEndereco().setBairro("aaaa");
		c.getEndereco().setCidade("aaaaa");
		c.getEndereco().setRua("aaaaaa");
		c.getMatricula().setDataMatricula(date1);
		c.getMatricula().setDataVencimento(date1);
		c.getMatricula().setCliente(c);
		//c.getMatricula().setAdministrador(null);
		
		
		Cliente c1 = new Cliente();
		c1.setNome("teste2");
		Date date2 = new GregorianCalendar(1986, Calendar.JANUARY, 18).getTime();
		c1.setDataNascimento(date2);		
		
		c1.setCpf("312.876.357-48");		
		c1.setTelefone("telefone");
		c1.getEndereco().setBairro("aaaa");
		c1.getEndereco().setCidade("aaaaa");
		c1.getEndereco().setRua("aaaaaa");
		c1.getMatricula().setDataMatricula(date2);
		c1.getMatricula().setDataVencimento(date2);
		c1.getMatricula().setCliente(c1);
		sessao.save(c);
		
		sessao.save(c1);
		
		transacao.commit();
		System.out.println("Cadastrou!");
		System.exit(0);

	}

}
