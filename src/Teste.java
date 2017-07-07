
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Administrador;
import model.Avaliacao;
import model.Cliente;
import model.Endereco;
import model.Exercicios;
import model.Instrutor;
import model.MatriculaAluno;
import model.Pagamento;
import model.Tipo;
import model.Treino;
import util.HibernateUtil;

public class Teste {
public static void main(String[] args) {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
			
		Transaction transacao = sessao.beginTransaction();
			
		//Cliente e Endereço
		Cliente c = new Cliente();
		c.setNome("Taniro");
		Date date1 = new GregorianCalendar(1986, Calendar.JANUARY, 03).getTime();
		c.setDataNascimento(date1);		
		c.setCpf("91192921912");		
		c.setTelefone("telefone");	
		
		Cliente c1 = new Cliente();
		c1.setNome("Adriano");
		Date date2 = new GregorianCalendar(1986, Calendar.JANUARY, 18).getTime();
		c1.setDataNascimento(date2);		
		c1.setCpf("91192921912");		
		c1.setTelefone("telefone");
		
		Endereco e = new Endereco();
		e.setBairro("ALGUM LUGAR DO MUNDO");
		e.setCidade("ZN");		
		e.setRua("Rua do OW");
		
		Endereco e1 = new Endereco();
		e1.setBairro("Alecrim");
		e1.setCidade("Natal");		
		e1.setRua("Rua do cs");		
		
		e.setCliente(c);
		c.setEndereco(e);
		
		e1.setCliente(c1);
		c1.setEndereco(e1);
		sessao.save(e1);
		sessao.save(e);
		
		
		//sessao.delete(c);
		
		
		//Deleta o endereço do cliente
//		c.setEndereco(null);
//		sessao.update(c);
		
		//Matricula ALUNO		
		MatriculaAluno m = new MatriculaAluno();
		m.setDataMatricula(date1);
		Date date3 = new GregorianCalendar(1986, Calendar.JANUARY, 05).getTime();
		m.setDataVencimento(date3);
		
		m.setCliente(c);
		c.setMatricula(m);
		
		MatriculaAluno m1 = new MatriculaAluno();
		m1.setDataMatricula(date1);
		Date date4 = new GregorianCalendar(1986, Calendar.JANUARY, 06).getTime();
		m1.setDataVencimento(date4);
		
		m1.setCliente(c1);
		c1.setMatricula(m1);	
		
		sessao.save(m);
		sessao.save(m1);
		sessao.save(c);
		sessao.save(c1);
		
		//ADMINISTRADOR
		Administrador a = new Administrador();
		a.setCpf("131331313");
		a.setFuncao("ADM");
		a.setNome("TALARICO");
		a.setLogin("teste");
		a.setSenha("123");
		a.setSalario(1500.0);		
		
		a.adicionarMatricula(m);
		a.adicionarMatricula(m1);
		
		sessao.save(a);
		
		Pagamento p = new Pagamento();
		p.setDataPagamento(date3);
		p.setValorPagamento(600.0);
		
		c.adicionarPagamento(p);
		
		a.adicionarPagamentoadm(p);
		
		sessao.update(c);
		sessao.update(a);
		sessao.save(p);
		
		//Instrutor
		Instrutor i = new Instrutor();
		i.setAnoFormacao(date1);
		i.setNome("severo");
		i.setLogin("severo123");
		i.setSenha("123");
		i.setSalario(500.00);
		i.setTelefone("21312124124");
		i.setAnoFormacao(date3);
		i.setCpf("21131231231");
		
		
		//Avaliacao
		Avaliacao av = new Avaliacao();
		av.setAlturacliente(1.70);
		av.setGorduracliente(30.0);
		av.setObservacao("VAI DA BOM");
		av.setPesocliente(80.00);
		av.setDataavaliacao(date2);		
		av.setInstrutor(i);
		av.setCliente(c);
		
		sessao.save(i);
		sessao.save(av);
		
		//Treino
		Treino t = new Treino();
		//t.setDataTreino(date3);
		t.setCliente(c);
		t.setInstrutor(i);		
		sessao.save(t);
		
		//Exercicios
		Exercicios ex = new Exercicios();
		ex.setCarga(10);
		ex.setNome("puxador");
		ex.setRepeticao(12);
		ex.setTipo(Tipo.DORSAL);
		
		Exercicios ex1 = new Exercicios();
		ex1.setCarga(20);
		ex1.setNome("remada");
		ex1.setRepeticao(8);
		ex1.setTipo(Tipo.PEITO);
		
//		t.addExercicios(ex);
//		t.addExercicios(ex1);
		sessao.save(ex);
		sessao.save(ex1);
		sessao.update(t);
		
		c.adicionarTreino(t);
		sessao.update(c);
		
		transacao.commit();		
		System.out.println("Cadastrou!");
		System.exit(0);
	}
}
