package com.baloise.qa.st.hot.base;

import java.net.URI;
import java.net.URISyntaxException;

import com.baloise.testautomation.taf.base._base.ABase;
import com.baloise.testautomation.taf.base.types.TafId;

public abstract class AServiceComponent<AService> extends ABase {

	public URI getURI() {
		try {
			
			String url = getURL();
			System.out.println("Url: " + url);
		//	String url = "https://_webservice/service/basicAuth/";
			url = url.replace("_env_", TafId.GetGlobalMandant().toLowerCase());
			URI uri = new URI(url + getServiceNameAndVersion());
			System.out.println("Uri: " + uri);
			return uri;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public abstract String getURL();
	
	public abstract String getServiceNameAndVersion();

	protected String getUsername() {
		return "user";
	}

	protected String getPassword() {
		return "pw";
	}
	
	protected abstract AService getService();

}
