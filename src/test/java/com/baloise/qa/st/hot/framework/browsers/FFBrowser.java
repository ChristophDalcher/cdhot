package com.baloise.qa.st.hot.framework.browsers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

@SuppressWarnings("deprecation")
public class FFBrowser extends ABrowser {

	private static final String GECKODRIVER = "geckodriver_0_22_0.exe";

	@SuppressWarnings("removal")
	@Override
	protected WebDriver getNewDriver() {
		ProfilesIni init = new ProfilesIni();
		FirefoxProfile profile = init.getProfile("default");
		profile.setPreference("dom.w3c_touch_events.enabled", 0);
		FirefoxOptions ffo;

		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(CapabilityType.OVERLAPPING_CHECK_DISABLED, true);
		dc.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);

		WebDriver driver;

		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

		System.setProperty("webdriver.gecko.driver", IBrowser.getPath(GECKODRIVER));
		if (System.getProperty(FirefoxDriver.SystemProperty.BROWSER_BINARY) == null)
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_BINARY, "C:\\Tools\\Mozilla Firefox 3.14.0\\firefox.exe");

		FirefoxBinary binary = new FirefoxBinary();
		if ("true".equalsIgnoreCase(System.getProperty("headless"))) {
			binary.addCommandLineOptions("--headless");
		}
		// This is to disable all built-in authentication methods
		// binary.addCommandLineOptions("--private-window");
		ffo = new FirefoxOptions().setBinary(binary).setProfile(profile).merge(dc);

		driver = new FirefoxDriver(ffo);
		driver.manage().timeouts().implicitlyWait(new Double(20 * 1000).longValue(), TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();

		return driver;
	}

	@Override
	public void killDriver() {
		System.out.println("Will 'taskkill' running geckodriver(s): " + GECKODRIVER);
		runCommand("taskkill /F /IM " + GECKODRIVER);
//		runCommand("Taskkill /F /T /FI \"Imagename eq firefox.exe*\"");
	}

}
