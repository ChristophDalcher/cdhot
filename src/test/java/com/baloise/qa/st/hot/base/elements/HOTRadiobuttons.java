package com.baloise.qa.st.hot.base.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.baloise.qa.st.hot.base.finder.HOTBrFinder;
import com.baloise.testautomation.taf.base._interfaces.IAnnotations.ByCustom;
import com.baloise.testautomation.taf.browser.elements.BrStringInput;

public class HOTRadiobuttons extends BrStringInput {

  String label = "";

  @Override
  public WebElement brFindByCustom() {
    WebDriver driver = ((HOTBrFinder)getFinder()).getDriver();
    String classText = ((ByCustom)by).value();
    return driver.findElement(
        By.xpath("//bal-select-button[contains(@class, '" + classText + "')]"));
  }

  @Override
  public void fill() {
    label = fillValueAsString();
    find().findElement(By.xpath(".//span[text()='" + label + "']/..")).click();
  }

}
