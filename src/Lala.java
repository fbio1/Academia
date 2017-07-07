import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Administrador;
import util.HibernateUtil;

public class Lala {

	public static void main(String[] args) {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		Transaction transacao = sessao.beginTransaction();
		
		Administrador a = new Administrador();
		a.setCpf("132.868.657-45");
		a.setFuncao("administrador");
		a.setNome("Teste");
		a.setLogin("teste");
		a.setSenha("123");
		a.setSalario(1000.0);			
		sessao.save(a);
		
		transacao.commit();
		System.out.println("Cadastrou!");
		System.exit(0);

	}

}
