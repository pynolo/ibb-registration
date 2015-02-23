package net.tarine.ibb.persistence;

import java.util.Date;
import java.util.List;

import net.tarine.ibb.SystemException;
import net.tarine.ibb.model.Resales;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.TimestampType;

public class ResalesDao {

	public List<Resales> findNonExpired(Session ses, Date now) throws SystemException {
		List<Resales> result = null;
		try {
			String hql = "from Resales r ";
			hql += "where r.expiration >= :dt1 ";
			hql += "order by r.created desc";
			Query q = ses.createQuery(hql);
			q.setParameter("dt1", now, TimestampType.INSTANCE);
			@SuppressWarnings("unchecked")
			List<Resales> list = q.list();			
			result = list;
		} catch (HibernateException e) {
			throw new SystemException(e.getMessage(), e);
		}
		return result;
	}
	
}
