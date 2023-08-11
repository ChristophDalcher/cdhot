package com.baloise.qa.st.hot.framework.browsers;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ABrowser implements IBrowser {

  protected static Logger logger = LoggerFactory.getLogger(ABrowser.class);;

  private WebDriver driver = null;

  @Override
  public WebDriver getDriver() {
    logger.info("Driver wurde angefragt: " + this.getClass());
    if (driver == null) {
      try {
        driver = getNewDriver();
        logger.info("Driver wurde instantiiert: " + driver.toString());
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
    return driver;
  }

  protected abstract WebDriver getNewDriver();

  protected void runCommand(String cmd) {
    try {
      Process p = Runtime.getRuntime().exec(cmd);
      p.waitFor();
      p.destroy();
    }
    catch (IOException | InterruptedException e) {
      System.out.println("ERROR.RUNNING.CMD: " + cmd);
      e.printStackTrace();
    }
  }

}
