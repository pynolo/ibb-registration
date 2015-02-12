package net.tarine.ibb.persistence;

import java.util.List;

import net.tarine.ibb.SystemException;
import net.tarine.ibb.model.Participants;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StringType;

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
	
	@SuppressWarnings("unchecked")
	public Participants findByCode(Session ses, String code) throws SystemException {
		try {
			String hql = "from Participants p where p.code = :s1 ";
			Query q = ses.createQuery(hql);
			q.setParameter("s1", code, StringType.INSTANCE);
			List<Participants> list = q.list();
			if (list != null) {
				if (list.size() > 0) return list.get(0);
			}
		} catch (HibernateException e) {
			throw new SystemException(e.getMessage(), e);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Integer countTicketsByAmount(Session ses, String amount) throws SystemException {
		try {
			String hql = "select count(id) from Participants p where "+
					"p.amount = :s1 and " +
					"p.payment is not null";
			Query q = ses.createQuery(hql);
			q.setParameter("s1", amount, StringType.INSTANCE);
			List<Object> list = q.list();
			if (list != null) {
				if (list.size() > 0) {
					Long count = (Long) list.get(0);
					return count.intValue();
				}
			}
		} catch (HibernateException e) {
			throw new SystemException(e.getMessage(), e);
		}
		return null;
	}
}
