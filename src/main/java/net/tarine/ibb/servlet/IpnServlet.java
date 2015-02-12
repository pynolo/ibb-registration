package net.tarine.ibb.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tarine.ibb.AppConstants;
import net.tarine.ibb.BusinessException;
import net.tarine.ibb.SystemException;
import net.tarine.ibb.model.Config;
import net.tarine.ibb.model.IpnResponses;
import net.tarine.ibb.model.Participants;
import net.tarine.ibb.persistence.ConfigDao;
import net.tarine.ibb.persistence.GenericDao;
import net.tarine.ibb.persistence.HibernateSessionFactory;
import net.tarine.ibb.persistence.ParticipantsDao;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class IpnServlet extends HttpServlet {
	private static final long serialVersionUID = -7594337685342241190L;

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(AppConstants.PAYPAL_URL);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("cmd", "_notify-validate")); // You need to add this parameter to tell PayPal to verify
		for (Enumeration<String> e = request.getParameterNames(); e
				.hasMoreElements();) {
			String name = e.nextElement();
			String value = request.getParameter(name);
			params.add(new BasicNameValuePair(name, value));
		}
		post.setEntity(new UrlEncodedFormEntity(params));
		String rc = getRC(client.execute(post)).trim();
		if ("VERIFIED".equals(rc)) {
			IpnResponses ipnr = new IpnResponses();
			for (NameValuePair pair:params) {
				if (pair.getName().equals("item_number")) ipnr.setItemNumber(pair.getValue());
				if (pair.getName().equals("mc_currency")) ipnr.setMcCurrency(pair.getValue());
				if (pair.getName().equals("mc_gross")) ipnr.setMcGross(pair.getValue());
				if (pair.getName().equals("payer_email")) ipnr.setPayerEmail(pair.getValue());
				if (pair.getName().equals("payment_date")) ipnr.setPaymentDate(pair.getValue());
				if (pair.getName().equals("payment_status")) ipnr.setPaymentStatus(pair.getValue());
				if (pair.getName().equals("payment_type")) ipnr.setPaymentType(pair.getValue());
				if (pair.getName().equals("pending_reason")) ipnr.setPendingReason(pair.getValue());
			}
			try {
				registerPayment(ipnr);
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}

	private String getRC(HttpResponse response) throws IOException,
			IllegalStateException {
		InputStream is = response.getEntity().getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String result = "";
		String line = null;
		while ((line = br.readLine()) != null) {
			result += line;
		}
		return result;
	}


	private void registerPayment(IpnResponses ipnr) throws SystemException, BusinessException {
		Locale.setDefault(Locale.US);
		Session ses = HibernateSessionFactory.getSession();
		Transaction trn = ses.beginTransaction();
		try {
			GenericDao.saveGeneric(ses, ipnr);
			Participants prt = new ParticipantsDao().findByCode(ses, ipnr.getItemNumber());
			if (prt != null) {
				//Updating participant
				Double amount = Double.valueOf(ipnr.getMcGross());
				prt.setAmount(amount);
				prt.setPayment(new Date());
				GenericDao.updateGeneric(ses, prt.getId(), prt);
				//Updating payment count
				ConfigDao cDao = new ConfigDao();
				Config ticketCount = cDao.findByName(ses, AppConstants.CONFIG_TICKET_COUNT);
				Integer value = Integer.parseInt(ticketCount.getVal());
				cDao.saveOrUpdateValueByName(ses, AppConstants.CONFIG_TICKET_COUNT, (value++)+"");
			}
			trn.commit();
		} catch (SystemException e) {
			trn.rollback();
			throw new SystemException(e.getMessage(), e);
		} finally {
			ses.close();
		}
	}
	
	/* Example info received from IPN:
	2015/02/12 06:34:41,164 INFO  [stdout] (http--127.13.54.1-8080-3) name:'cmd' value:'_notify-validate'
	2015/02/12 06:34:41,164 INFO  [stdout] (http--127.13.54.1-8080-3) name:'item_number' value:'FA26A1'
	2015/02/12 06:34:41,165 INFO  [stdout] (http--127.13.54.1-8080-3) name:'residence_country' value:'US'
	2015/02/12 06:34:41,165 INFO  [stdout] (http--127.13.54.1-8080-3) name:'verify_sign' value:'AVVFXYTfxjgtzxri1C-ynmsGko1MAHJYHn0goRRxu468hcx9u0lRmE2J'
	2015/02/12 06:34:41,165 INFO  [stdout] (http--127.13.54.1-8080-3) name:'payment_status' value:'Pending'
	2015/02/12 06:34:41,165 INFO  [stdout] (http--127.13.54.1-8080-3) name:'business' value:'paolo-facilitator@tarine.net'
	2015/02/12 06:34:41,165 INFO  [stdout] (http--127.13.54.1-8080-3) name:'protection_eligibility' value:'Ineligible'
	2015/02/12 06:34:41,166 INFO  [stdout] (http--127.13.54.1-8080-3) name:'transaction_subject' value:''
	2015/02/12 06:34:41,166 INFO  [stdout] (http--127.13.54.1-8080-3) name:'first_name' value:'test'
	2015/02/12 06:34:41,166 INFO  [stdout] (http--127.13.54.1-8080-3) name:'payer_id' value:'BRY27QGWWQVPC'
	2015/02/12 06:34:41,166 INFO  [stdout] (http--127.13.54.1-8080-3) name:'payer_email' value:'paolo-buyer@tarine.net'
	2015/02/12 06:34:41,166 INFO  [stdout] (http--127.13.54.1-8080-3) name:'txn_id' value:'28187574TR775311A'
	2015/02/12 06:34:41,166 INFO  [stdout] (http--127.13.54.1-8080-3) name:'quantity' value:'0'
	2015/02/12 06:34:41,167 INFO  [stdout] (http--127.13.54.1-8080-3) name:'receiver_email' value:'paolo-facilitator@tarine.net'
	2015/02/12 06:34:41,167 INFO  [stdout] (http--127.13.54.1-8080-3) name:'notify_version' value:'3.8'
	2015/02/12 06:34:41,167 INFO  [stdout] (http--127.13.54.1-8080-3) name:'txn_type' value:'web_accept'
	2015/02/12 06:34:41,167 INFO  [stdout] (http--127.13.54.1-8080-3) name:'mc_gross' value:'58.00'
	2015/02/12 06:34:41,167 INFO  [stdout] (http--127.13.54.1-8080-3) name:'mc_currency' value:'EUR'
	2015/02/12 06:34:41,167 INFO  [stdout] (http--127.13.54.1-8080-3) name:'payer_status' value:'verified'
	2015/02/12 06:34:41,168 INFO  [stdout] (http--127.13.54.1-8080-3) name:'test_ipn' value:'1'
	2015/02/12 06:34:41,168 INFO  [stdout] (http--127.13.54.1-8080-3) name:'custom' value:''
	2015/02/12 06:34:41,168 INFO  [stdout] (http--127.13.54.1-8080-3) name:'payment_date' value:'03:34:35 Feb 12, 2015 PST'
	2015/02/12 06:34:41,168 INFO  [stdout] (http--127.13.54.1-8080-3) name:'charset' value:'windows-1252'
	2015/02/12 06:34:41,168 INFO  [stdout] (http--127.13.54.1-8080-3) name:'payment_gross' value:''
	2015/02/12 06:34:41,168 INFO  [stdout] (http--127.13.54.1-8080-3) name:'ipn_track_id' value:'9cadb988c9e24'
	2015/02/12 06:34:41,168 INFO  [stdout] (http--127.13.54.1-8080-3) name:'pending_reason' value:'multi_currency'
	2015/02/12 06:34:41,168 INFO  [stdout] (http--127.13.54.1-8080-3) name:'tax' value:'0.00'
	2015/02/12 06:34:41,168 INFO  [stdout] (http--127.13.54.1-8080-3) name:'item_name' value:'Donation'
	2015/02/12 06:34:41,168 INFO  [stdout] (http--127.13.54.1-8080-3) name:'last_name' value:'buyer'
	2015/02/12 06:34:41,168 INFO  [stdout] (http--127.13.54.1-8080-3) name:'payment_type' value:'instant'
	2015/02/12 06:34:41,169 INFO  [stdout] (http--127.13.54.1-8080-3) name:'receiver_id' value:'6UN4BCCPZ5VXN'
	 */
}