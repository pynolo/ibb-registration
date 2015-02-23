package net.tarine.ibb.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.tarine.ibb.SystemException;
import net.tarine.ibb.model.Resales;
import net.tarine.ibb.persistence.GenericDao;
import net.tarine.ibb.persistence.HibernateSessionFactory;
import net.tarine.ibb.persistence.ResalesDao;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ResaleBusiness {
	
	public static List<Resales> findNonExpired(Date now) throws SystemException {
		List<Resales> result = new ArrayList<Resales>();
		Session ses = HibernateSessionFactory.getSession();
		try {
			result = new ResalesDao().findNonExpired(ses, now);
		} catch (SystemException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			ses.close();
		}
		return result;
	}

	public static void saveOrUpdateResale(Resales resale) throws SystemException {
		Session ses = HibernateSessionFactory.getSession();
		Transaction trn = ses.beginTransaction();
		try {
			if (resale.getId() == null) {
				//SAVE
				GenericDao.saveGeneric(ses, resale);
			} else {
				//UPDATE
				GenericDao.updateGeneric(ses, resale.getId(), resale);
			}
			trn.commit();
		} catch (SystemException e) {
			trn.rollback();
			throw new SystemException(e.getMessage(), e);
		} finally {
			ses.close();
		}
	}
}
