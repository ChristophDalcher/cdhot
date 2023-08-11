package com.baloise.qa.st.hot.base.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.baloise.qa.st.hot.base.finder.HOTBrFinder;
import com.baloise.testautomation.taf.base._interfaces.IAnnotations.ByCustom;
import com.baloise.testautomation.taf.browser.elements.BrStringInput;

public class HOTTogglerInput extends BrStringInput {

  @Override
  public WebElement brFindByCustom() {
    WebDriver driver = ((HOTBrFinder)getFinder()).getDriver();
    String text = ((ByCustom)by).value();
    return driver.findElement(By.xpath("//div[contains(@class, 'form__toggler')]//strong[contains(text(), '" + text
        + "')]/../..//ul[@class='form__toggler-switch']"));
  }

  @Override
  public void fill() {
    WebElement e = find().findElement(By.xpath(".//li//span[text()=\"" + fillValueAsString() + "\"]"));
    ((HOTBrFinder)getFinder()).scrollIntoView(e);
    e.click();
  }

}
