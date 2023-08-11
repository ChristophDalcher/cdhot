package com.baloise.qa.st.hot.framework.browsers;

import org.openqa.selenium.WebDriver;

public class NoBrowser extends ABrowser {
  @Override
  public void killDriver() {

  }

  @Override
  protected WebDriver getNewDriver() {
    return null;
  }
}
