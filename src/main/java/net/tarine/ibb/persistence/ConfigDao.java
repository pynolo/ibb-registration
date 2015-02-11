package net.tarine.ibb.persistence;

import java.util.List;

import net.tarine.ibb.BusinessException;
import net.tarine.ibb.SystemException;
import net.tarine.ibb.model.Config;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class ConfigDao {

	public Config findByName(Session ses, String name) 
			throws SystemException, BusinessException {
		try {
			String hql = "from Config c where "+
					"c.name = :s1 ";
			Query q = ses.createQuery(hql);
			q.setParameter("s1", name);
			q.setMaxResults(1);
			@SuppressWarnings("unchecked")
			List<Config> list = q.list();
			if (list != null) {
				if (list.size() > 0) return list.get(0);
			}
			throw new BusinessException("Can't find "+name+" config");
		} catch (HibernateException e) {
			throw new SystemException(e.getMessage(), e);
		}
	}
	
	public void saveOrUpdateValueByName(Session ses, String name, String value) 
			throws SystemException {
		Config config = null;
		try {
			config = findByName(ses, name);
		} catch (BusinessException e1) {
			config = new Config();
			config.setName(name);
		}
		config.setVal(value);
		if (config.getId() == null) {
			GenericDao.saveGeneric(ses, config);
		} else {
			GenericDao.updateGeneric(ses, config.getId(), config);
		}
	}
	
}
