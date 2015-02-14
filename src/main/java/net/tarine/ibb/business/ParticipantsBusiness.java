package net.tarine.ibb.business;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.tarine.ibb.AppConstants;
import net.tarine.ibb.BusinessException;
import net.tarine.ibb.SystemException;
import net.tarine.ibb.model.Participants;
import net.tarine.ibb.persistence.HibernateSessionFactory;
import net.tarine.ibb.persistence.ParticipantsDao;

import org.hibernate.Session;

public class ParticipantsBusiness {
	
	public static List<Participants> findAllParticipants() throws SystemException {
		List<Participants> result = new ArrayList<Participants>();
		Session ses = HibernateSessionFactory.getSession();
		try {
			result = new ParticipantsDao().findAll(ses);
		} catch (SystemException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			ses.close();
		}
		return result;
	}

	public static void loadParticipantInSession(HttpSession session, HttpServletRequest request) 
			throws SystemException, BusinessException {
		//CODE
		String code = request.getParameter(AppConstants.PARAMS_CODE);
		
		Participants prtc = null;
		Session ses = HibernateSessionFactory.getSession();
		try {
			prtc = new ParticipantsDao().findByCode(ses, code);
			if (prtc == null) throw new BusinessException("Participant not found");
		} catch (SystemException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			ses.close();
		}
		session.setAttribute(AppConstants.PARAMS_CODE, prtc.getCode());
		//NAME
		session.setAttribute(AppConstants.PARAMS_NAME, prtc.getName());
		//AMOUNT
		session.setAttribute(AppConstants.PARAMS_AMOUNT, prtc.getAmount());
	}
}
