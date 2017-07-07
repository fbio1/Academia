package dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class GenericDaoImpl<T, ID extends Serializable> { 

	private Class<T> klass; 
	private Transaction transacao = null;
    private Session sessao = null;

	public GenericDaoImpl() {} 
	public GenericDaoImpl(Class<T> klass) {		
		this.klass = klass; 
	} 

	public T findById(ID id) { 		
        Session session = HibernateUtil.getSessionFactory().openSession();
        T object = (T) session.get(klass, id);
        session.close();
        return object;
	} 

	public void save(T object) {
		sessao = HibernateUtil.getSessionFactory().openSession();
		try {			
	        transacao = sessao.beginTransaction();
	        sessao.save((T) object);
	        transacao.commit();	        
		} catch (RuntimeException erro) {
			if(transacao != null){
				transacao.rollback();
			}
		}finally{
			sessao.close();
		}       
	} 
	 
	public void delete(T object) {
		sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			transacao = sessao.beginTransaction();
		    sessao.delete( (T) object);
		    transacao.commit();	 
		} catch (Exception e) {
			if(transacao != null){
				transacao.rollback();
			}
		}finally{
			sessao.close();
		}
	}
	
	public void update(T object) {
		sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			transacao = sessao.beginTransaction();
		    sessao.update( (T) object);
		    transacao.commit();	 
		} catch (Exception e) {
			if(transacao != null){
				transacao.rollback();
			}
		}finally{
			sessao.close();
		}
	}	  
	
    public List<T> list() {    	
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();        
        CriteriaQuery<T> query = builder.createQuery(klass);
        
        Root<T> klassRoot = query.from(klass);
        
        query.select(klassRoot).where(builder.isNull(klassRoot.get("dataRemocao")));        	
        
        List<T> result = session.createQuery(query).getResultList();

        session.close();        
        return result;
        
    }
	
}
