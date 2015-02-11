package net.tarine.ibb.business;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.tarine.ibb.AppConstants;
import net.tarine.ibb.SystemException;
import net.tarine.ibb.model.Ip2nationCountries;
import net.tarine.ibb.model.Participants;
import net.tarine.ibb.persistence.GenericDao;
import net.tarine.ibb.persistence.HibernateSessionFactory;
import net.tarine.ibb.persistence.Ip2nationDao;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class WizardBusiness {

	public static void getParameters(HttpSession session, HttpServletRequest request) 
			throws SystemException {
		//STEP
		String step = request.getParameter(AppConstants.PARAMS_STEP);
		if (step == null) step = "0";
		session.setAttribute(AppConstants.PARAMS_STEP, step);
		//CODE
		String code = request.getParameter(AppConstants.PARAMS_CODE);
		if (code == null) code = WizardBusiness.createCode(session.getId(), 6);
		session.setAttribute(AppConstants.PARAMS_CODE, code);
		//EMAIL
		String email = request.getParameter(AppConstants.PARAMS_EMAIL);
		if (email == null) email = "";
		session.setAttribute(AppConstants.PARAMS_EMAIL, email);
		//NAME
		String name = request.getParameter(AppConstants.PARAMS_NAME);
		if (name == null) name = "";
		session.setAttribute(AppConstants.PARAMS_NAME, name);
		//FOOD
		String food = request.getParameter(AppConstants.PARAMS_FOOD);
		if (food == null) food = "";
		session.setAttribute(AppConstants.PARAMS_FOOD, food);
		//COUNTRY
		String country = request.getParameter(AppConstants.PARAMS_COUNTRY);
		if (country == null) country = "";
		session.setAttribute(AppConstants.PARAMS_COUNTRY, country);
		//ARRIVAL TIME
		String arrivalTime = request.getParameter(AppConstants.PARAMS_ARRIVAL_TIME);
		if (arrivalTime == null) arrivalTime = "";
		session.setAttribute(AppConstants.PARAMS_ARRIVAL_TIME, arrivalTime);
		//VOLUNTEER
		String volunteer = request.getParameter(AppConstants.PARAMS_VOLUNTEER);
		if (volunteer == null) volunteer = "";
		session.setAttribute(AppConstants.PARAMS_VOLUNTEER, volunteer);
		//AMOUNT
		String amount = request.getParameter(AppConstants.PARAMS_AMOUNT);
		if (amount == null) amount = "";
		if (amount.equals("") && !country.equals("")) {
			if (country.equalsIgnoreCase("ITALY")) {
				amount = ConfigBusiness.findValueByName(AppConstants.CONFIG_PRICE_ITALY);
				if (amount == null) throw new SystemException("price_italy has not been configured");
			} else {
				amount = ConfigBusiness.findValueByName(AppConstants.CONFIG_PRICE_ABROAD);
				if (amount == null) throw new SystemException("price_abroad has not been configured");
			}
		}
		session.setAttribute(AppConstants.PARAMS_AMOUNT, amount);
	}
	
	public static void saveParticipant(HttpServletRequest request) throws SystemException {
		Participants prtc = new Participants();
		//CODE
		String code = request.getParameter(AppConstants.PARAMS_CODE);
		if (code !=null) if (code.length()>64) code=code.substring(0, 64);
		prtc.setCode(code);
		//EMAIL
		String email = request.getParameter(AppConstants.PARAMS_EMAIL);
		if (email !=null) if (email.length()>64) email=email.substring(0, 64);
		prtc.setEmail(email);
		//NAME
		String name = request.getParameter(AppConstants.PARAMS_NAME);
		if (name !=null) if (name.length()>128) name=name.substring(0, 128);
		prtc.setName(name);
		//FOOD
		String food = request.getParameter(AppConstants.PARAMS_FOOD);
		if (food !=null) if (food.length()>2048) food=food.substring(0, 2048);
		prtc.setFoodRestrictions(food);
		//COUNTRY
		String country = request.getParameter(AppConstants.PARAMS_COUNTRY);
		if (country !=null) if (country.length()>256) country=country.substring(0, 256);
		prtc.setCountryName(country);
		//ARRIVAL TIME
		String arrivalTime = request.getParameter(AppConstants.PARAMS_ARRIVAL_TIME);
		if (arrivalTime !=null) if (arrivalTime.length()>128) arrivalTime=arrivalTime.substring(0, 128);
		prtc.setArrivalTime(arrivalTime);
		//VOLUNTEER
		String volunteer = request.getParameter(AppConstants.PARAMS_VOLUNTEER);
		if (volunteer !=null) if (volunteer.length()>2048) volunteer=volunteer.substring(0, 2048);
		prtc.setVolunteering(volunteer);
		//AMOUNT
		String amountString = request.getParameter(AppConstants.PARAMS_AMOUNT);
		Double amount = null;
		if (amountString != null) {
			try {
				amount = Double.parseDouble(amountString);
			} catch (NumberFormatException e1) { }
		}
		prtc.setAmount(amount);
		//
		prtc.setCreated(new Date());
		Session ses = HibernateSessionFactory.getSession();
		Transaction trn = ses.beginTransaction();
		try {
			GenericDao.saveGeneric(ses, prtc);
			trn.commit();
		} catch (SystemException e) {
			trn.rollback();
			throw new SystemException(e.getMessage(), e);
		} finally {
			ses.close();
		}
	}
	
	public static String createCode(String seed, int size) throws SystemException {
		if (seed==null) seed="";
		seed += new Date().getTime();
		String md5;
		try {
			//Create MessageDigest object for MD5
			MessageDigest digest = MessageDigest.getInstance("MD5");
			//Update input string in message digest
			digest.update(seed.getBytes(), 0, seed.length());
			//Converts message digest value in base 16 (hex)
			md5 = new BigInteger(1, digest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			throw new SystemException(e.getMessage(), e);
		}
		String code = md5.substring(0, size);
		return code.toUpperCase();
	}
	
	public static List<Ip2nationCountries> listCountries() throws SystemException {
		List<Ip2nationCountries> result = new ArrayList<Ip2nationCountries>();
		Session ses = HibernateSessionFactory.getSession();
		try {
			result = new Ip2nationDao().findCountries(ses);
		} catch (SystemException e) {
			Ip2nationCountries c = new Ip2nationCountries();
			c.setCountry("Error loading countries");
			result.add(c);
			//throw new SystemException(e.getMessage(), e);
		} finally {
			ses.close();
		}
		return result;
	}
	
}
