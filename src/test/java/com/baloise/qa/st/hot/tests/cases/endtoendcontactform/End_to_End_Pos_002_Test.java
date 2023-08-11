package com.baloise.qa.st.hot.tests.cases.endtoendcontactform;

import org.junit.Test;

import com.baloise.qa.st.hot.framework.applications.HoT;
import com.baloise.qa.st.hot.framework.applications.IApplication.Application;
import com.baloise.qa.st.hot.framework.browsers.GCBrowser;
import com.baloise.qa.st.hot.framework.browsers.IBrowser.Browser;
import com.baloise.qa.st.hot.framework.environments.HOTProdEnv;
import com.baloise.qa.st.hot.framework.environments.IEnvironment.Environment;
import com.baloise.qa.st.hot.pages.confirmation.Confirmation;
import com.baloise.qa.st.hot.pages.expertconsulting.ExpertConsulting;
import com.baloise.qa.st.hot.pages.initialseite.InitialSeite;
import com.baloise.qa.st.hot.pages.services.Services;
import com.baloise.qa.st.hot.tests.AHOTTest;
import com.baloise.testautomation.taf.base._interfaces.IAnnotations.Check;
import com.baloise.testautomation.taf.base._interfaces.IAnnotations.DataProvider;
import com.baloise.testautomation.taf.base._interfaces.IAnnotations.DataProviderType;
import com.baloise.testautomation.taf.base._interfaces.IAnnotations.Fill;

@DataProvider(DataProviderType.EXCEL)

public class End_to_End_Pos_002_Test extends AHOTTest {

	// page objects
	
	@Fill
	InitialSeite initialseite;

	Services services;
	
	@Check
	@Fill
	ExpertConsulting expertconsulting;
	
	@Fill
	Confirmation confirmation;
	
	@Test
	@Environment(HOTProdEnv.class)
	@Application(HoT.class)
	@Browser(GCBrowser.class)
	public void execute() {

		setFill("contactform-sonderzeichen");
		
		//needed for executing tests out of a company network
		//doLogin();
				
		// cookie consent: accept all cookies
		initialseite.acceptCookies();
		
		// to make sure a little bit more that the initial screen is present and certain
		// elements are there, check that certain texts are there
		// "small finding: Humans has a space at the end of the text in the html"
		
		initialseite.assertTexts();

		// selecting the services section
		initialseite.services.click();

		// selecting testing and consulting section
		services.testingandconsulting.click();
		
		expertconsulting.fill();
		expertconsulting.check();
		
		expertconsulting.iFrameWechsel();
		expertconsulting.recaptchacheckbox.click();
		expertconsulting.getBrowserFinder().setToDefaultContent();
		
		expertconsulting.submit.click();
				
		confirmation.assertTexts();
	}
}
