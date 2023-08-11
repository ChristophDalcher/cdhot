package com.baloise.qa.st.hot.framework.browsers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GCBrowser extends ABrowser {

  private static final String CHROMEDRIVER = "chromedriver_114.exe";

  @SuppressWarnings("removal")
@Override
  protected WebDriver getNewDriver() {
    System.setProperty("webdriver.chrome.driver", IBrowser.getPath(CHROMEDRIVER));
    ChromeOptions co = new ChromeOptions();
    co.addArguments("--touch-events=disabled");
    co.addArguments("--incognito");
    WebDriver driver = new ChromeDriver(co);
    driver.manage().timeouts().implicitlyWait(new Double(20 * 1000).longValue(), TimeUnit.MILLISECONDS);
    driver.manage().window().maximize();
    return driver;
  }

  @Override
  public void killDriver() {
    System.out.println("Will 'taskkill' running chromedriver(s): " + CHROMEDRIVER);
    runCommand("taskkill /F /IM " + CHROMEDRIVER);
  }

}
