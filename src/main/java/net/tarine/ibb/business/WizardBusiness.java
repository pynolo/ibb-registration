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
import net.tarine.ibb.persistence.HibernateSessionFactory;
import net.tarine.ibb.persistence.Ip2nationDao;

import org.hibernate.Session;

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
		//TRAVEL TIME
		String arrivalTime = request.getParameter(AppConstants.PARAMS_ARRIVAL_TIME);
		if (arrivalTime == null) arrivalTime = "";
		session.setAttribute(AppConstants.PARAMS_ARRIVAL_TIME, arrivalTime);
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
