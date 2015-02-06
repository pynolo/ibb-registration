package net.tarine.ibb.business;

import java.util.ArrayList;
import java.util.List;

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

	public static void wizardSave(Participants participantsTransient) {
		//TODO
	}
}
