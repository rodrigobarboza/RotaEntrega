package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import util.HibernateUtil;
import entity.Trecho;

public class TrechoDAO {

	@SuppressWarnings("unchecked")
	public List<Trecho> findByExample(Trecho trecho) {
		
		Session session = HibernateUtil.getSession();
		Example trechoExample =  Example.create(trecho);
		Criteria criteria = session.createCriteria(Trecho.class).add(trechoExample);
		return criteria.list();
	}
	
}
