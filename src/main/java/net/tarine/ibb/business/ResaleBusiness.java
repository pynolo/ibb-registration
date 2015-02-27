package net.tarine.ibb.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.tarine.ibb.AppConstants;
import net.tarine.ibb.BusinessException;
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
	
	public static void saveParametersIfNotEmpty(HttpSession session, HttpServletRequest request) 
			throws SystemException, BusinessException {
		//RESALE TYPE
		String resaleType = request.getParameter(AppConstants.PARAMS_RESALE_TYPE);
		if (resaleType == null) resaleType = "";
		//MESSAGE
		String message = request.getParameter(AppConstants.PARAMS_MESSAGE);
		if (message == null) message = "";
		//EMAIL
		String email = request.getParameter(AppConstants.PARAMS_EMAIL);
		if (email == null) email = "";
		//EXP DATE
		String expDateString = request.getParameter(AppConstants.PARAMS_EXPDATE);
		if (expDateString == null) expDateString = "";
		
		//VERIFICATION
		if (resaleType.length() > 0 || message.length() > 0 || email.length() > 0) {
			if ((resaleType.length() < 2) || (resaleType.length() > 4))
					throw new BusinessException("Some fields are not valid. Please submit the form again.");
			if ((message.length() < 2) || (message.length() > 255))
					throw new BusinessException("Message is not valid. Please submit the form again.");
			if ((email.length() < 5) || (email.length() > 64))
					throw new BusinessException("Email is not valid. Please submit the form again.");
			
			//TODO date
			//SAVE
			//TODO save
		}
	}
}
