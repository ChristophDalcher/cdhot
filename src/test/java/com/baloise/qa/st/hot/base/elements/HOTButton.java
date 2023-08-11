package com.baloise.qa.st.hot.base.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.baloise.qa.st.hot.base.finder.HOTBrFinder;
import com.baloise.testautomation.taf.base._interfaces.IAnnotations.ByCustom;
import com.baloise.testautomation.taf.browser.elements.BrButton;

public class HOTButton extends BrButton {

  @Override
  public WebElement brFindByCustom() {
    WebDriver driver = ((HOTBrFinder)getFinder()).getDriver();
    String text = ((ByCustom)by).value();
    return driver.findElement(By.xpath("//button[contains(text(), '" + text + "')]"));
  }

}
