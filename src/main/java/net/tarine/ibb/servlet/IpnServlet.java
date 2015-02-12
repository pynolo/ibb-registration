package net.tarine.ibb.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tarine.ibb.AppConstants;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

public class IpnServlet extends HttpServlet {
	private static final long serialVersionUID = -7594337685342241190L;

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(AppConstants.PAYPAL_URL_SANDBOX);//TODO
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
			for (NameValuePair pair:params) {
				System.out.println("name:'"+pair.getName()+"' value:'"+pair.getValue()+"'");
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

}