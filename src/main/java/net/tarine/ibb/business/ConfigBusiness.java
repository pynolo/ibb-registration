package net.tarine.ibb.business;

import net.tarine.ibb.BusinessException;
import net.tarine.ibb.SystemException;
import net.tarine.ibb.model.Config;
import net.tarine.ibb.persistence.ConfigDao;
import net.tarine.ibb.persistence.HibernateSessionFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ConfigBusiness {
	
	public static String findValueByName(String name) throws SystemException {
		String value = null;
		Session ses = HibernateSessionFactory.getSession();
		try {
			Config config;
			try {
				config = new ConfigDao().findByName(ses, name);
			} catch (BusinessException e) {
				config = null;
			}
			if (config != null) value = config.getVal();
		} catch (SystemException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			ses.close();
		}
		return value;
	}

	public static void saveOrUpdateValueByName(String name, String value)
			throws SystemException {
		Session ses = HibernateSessionFactory.getSession();
		Transaction trn = ses.beginTransaction();
		try {
			new ConfigDao().saveOrUpdateValueByName(ses, name, value);
			trn.commit();
		} catch (SystemException e) {
			trn.rollback();
			throw new SystemException(e.getMessage(), e);
		} finally {
			ses.close();
		}
	}
	
}
