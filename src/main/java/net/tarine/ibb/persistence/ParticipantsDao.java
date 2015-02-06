package net.tarine.ibb.persistence;

import java.util.List;

import net.tarine.ibb.SystemException;
import net.tarine.ibb.model.Participants;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.BooleanType;

public class ParticipantsDao {

	public List<Participants> findAll(Session ses, boolean filterExpired, boolean filterHidden) throws SystemException {
		List<Participants> result = null;
		try {
			String hql = "from Participants h ";
			if (filterExpired || filterHidden) hql += "where ";
			if (filterExpired) hql += "h.expired = :b1 ";
			if (filterExpired && filterHidden) hql += "and ";
			if (filterHidden) hql += "h.hidden = :b2 ";
			hql += "order by h.pollTime asc";
			Query q = ses.createQuery(hql);
			if (filterExpired) q.setParameter("b1", Boolean.FALSE, BooleanType.INSTANCE);
			if (filterHidden) q.setParameter("b2", Boolean.FALSE, BooleanType.INSTANCE);
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
