package com.baloise.qa.st.hot.base.testcasemanager;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.baloise.qa.st.hot.base.finder.BaloiseBrFinder;
import com.baloise.qa.st.hot.base.finder.HOTBrFinder;
import com.baloise.qa.st.hot.framework.applications.IApplication;
import com.baloise.qa.st.hot.framework.browsers.FFBrowser;
import com.baloise.qa.st.hot.framework.browsers.IBrowser;
import com.baloise.qa.st.hot.framework.environments.IHOTEnvironment;
import com.baloise.qa.st.hot.framework.environments.IEnvironment;
import com.baloise.qa.st.hot.framework.locales.DELocale;
import com.baloise.qa.st.hot.framework.locales.ILocale;
import com.baloise.qa.st.hot.framework.stacks.IStack;
import com.baloise.qa.st.hot.framework.stacks.StandardStack;

public class HOTTestcaseManager implements ITestcaseManager {

  WebDriver driver = null;

  IApplication application = null;
  IBrowser browser = null;
  IHOTEnvironment environment = null;

  IStack stack = null;
  ILocale locale = null;

  HOTBrFinder HOTFinder = null;
  BaloiseBrFinder baloiseFinder = null;

  boolean debug = true;

  @Override
  public IApplication getApplication() {
    return application;
  }

  @Override
  public IBrowser getBrowser() {
    return browser;
  }

  @Override
  public IHOTEnvironment getEnvironment() {
    return environment;
  }

  public HOTBrFinder getEISFinder() {
    return HOTFinder;
  }

  public BaloiseBrFinder getLoginFinder() {
    return baloiseFinder;
  }

  @Override
  public ILocale getLocale() {
    return locale;
  }

  @Override
  public IStack getStack() {
    return stack;
  }

  private void initDebug() {
    environment = IHOTEnvironment.production();
    stack = new StandardStack();
    locale = new DELocale();
  }

  private boolean isDebug() {
    return debug;
  }

  public void setApplication(IApplication application) {
    this.application = application;
  }

  public void setBrowser(IBrowser browser) {
    this.browser = browser;
  }

  public void setEnvironment(IEnvironment environment) {
    this.environment = (IHOTEnvironment)environment;

  }

  @Override
  public void setLocale(ILocale locale) {
    this.locale = locale;
  }

  @Override
  public void setStack(IStack stack) {
    this.stack = stack;
  }

  public void startApplication() {
    if (isDebug()) {
      initDebug();
    }
    if (browser == null) {
      browser = new FFBrowser();
    }

    if (environment == null) {
      environment = IHOTEnvironment.getEnvironment();
    }

    
    environment.setTafGlobalMandant();

    if (locale == null) {
      locale = new DELocale();
    }
    Assert.assertNotNull("@Application fehlt --> entweder auf Klassen- oder Methodenebene hinzufügen", application);
    driver = browser.getDriver();
    HOTFinder = new HOTBrFinder(driver, 30);
    baloiseFinder = new BaloiseBrFinder(driver, 30);
    String url = application.getUrl(this);
    System.out.println("Starten mit: " + url);
    driver.get(url);
  }


  

  public void stopApplication() {
//    if (!isDebug()) {
      driver.quit();
     //    }
//    else {
//      browser.killDriver();
//    }
  }

  @Override
  public void reset() {
    setApplication(null);
    setBrowser(null);
    setEnvironment(null);
    setLocale(null);
    setStack(null);
  }

}
