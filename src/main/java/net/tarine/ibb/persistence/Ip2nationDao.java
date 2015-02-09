package net.tarine.ibb.persistence;

import java.util.List;

import net.tarine.ibb.SystemException;
import net.tarine.ibb.model.Ip2nationCountries;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.LongType;

public class Ip2nationDao {

	public Ip2nationCountries findNationCodeByIp(Session ses, Long ipAsLong) throws SystemException {
		try {
			String hql = "select c from Ip2nationCountries c, Ip2nation i where "+
					"c.code = i.country and "+
					"i.ip < :l1 "+
					"order by i.ip desc";
			Query q = ses.createQuery(hql);
			q.setFirstResult(0);
			q.setMaxResults(1);
			q.setParameter("l1", ipAsLong, LongType.INSTANCE);
			@SuppressWarnings("rawtypes")
			List list = q.list();
			if (list != null) {
				if (list.size() > 0) {
					return (Ip2nationCountries) list.get(0);
				}
			}
		} catch (HibernateException e) {
			throw new SystemException(e.getMessage(), e);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Ip2nationCountries> findCountries(Session ses) throws SystemException {
		try {
			String hql = "select c from Ip2nationCountries c "+
					"order by c.country asc";
			Query q = ses.createQuery(hql);
			List<Ip2nationCountries> list = (List<Ip2nationCountries>) q.list();
			if (list != null) {
				if (list.size() > 0) {
					return list;
				}
			}
		} catch (HibernateException e) {
			throw new SystemException(e.getMessage(), e);
		}
		return null;
	}
}
