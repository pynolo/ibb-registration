package net.tarine.ibb.persistence;

import java.util.List;

import net.tarine.ibb.SystemException;
import net.tarine.ibb.model.Participants;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class ParticipantsDao {

	public List<Participants> findAll(Session ses) throws SystemException {
		List<Participants> result = null;
		try {
			String hql = "from Participants p ";
			hql += "order by p.created asc";
			Query q = ses.createQuery(hql);

			@SuppressWarnings("unchecked")
			List<Participants> list = q.list();
			result = list;
		} catch (HibernateException e) {
			throw new SystemException(e.getMessage(), e);
		}
		return result;
	}
	
//	public List<Participants> findExpired(Session ses) throws SystemException {
//		List<Participants> result = null;
//		try {
//			String hql = "from Participants h where h.expired = :b1 ";
//			Query q = ses.createQuery(hql);
//			q.setParameter("b1", Boolean.TRUE, BooleanType.INSTANCE);
//			@SuppressWarnings("unchecked")
//			List<Participants> list = q.list();
//			result = list;
//		} catch (HibernateException e) {
//			throw new SystemException(e.getMessage(), e);
//		}
//		return result;
//	}
	
}
