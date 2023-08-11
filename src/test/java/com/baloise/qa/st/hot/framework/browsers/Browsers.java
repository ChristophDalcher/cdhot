package com.baloise.qa.st.hot.framework.browsers;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class Browsers {

  public static WebDriver GetBrowserDriver() {
    String browserName = System.getProperty("browser");
    if (browserName == null) {
      return new FFBrowser().getDriver();
    }
    browserName = browserName.toLowerCase().trim();
    switch (browserName) {
      case "ie":
        return new IEBrowser().getDriver();
      case "ff":
        return new FFBrowser().getDriver();
      case "gc":
        return new GCBrowser().getDriver();
      case "me":
        return new MEBrowser().getDriver();
      default:
        break;
    }
    Assert.fail("Browser konnte nicht identifiziert werden, daher auch kein WebDriver allozierbar");
    return null;
  }

  @SuppressWarnings("deprecation")
public static WebDriver GetBrowserDriver(Class<? extends IBrowser> value) {
    try {
      return value.newInstance().getDriver();
    }
    catch (InstantiationException | IllegalAccessException e) {}
    Assert.fail("Browser konnte nicht instantiiert werden, daher auch kein WebDriver allozierbar");
    return null;
  }

}
