package com.baloise.qa.st.hot.base.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.baloise.qa.st.hot.base.finder.HOTBrFinder;
import com.baloise.testautomation.taf.base._interfaces.IAnnotations.ByCustom;
import com.baloise.testautomation.taf.browser.elements.BrCombobox;

public class HOTCombobox extends BrCombobox {

  @Override
  public WebElement brFindByCustom() {
    WebDriver driver = ((HOTBrFinder)getFinder()).getDriver();
    String text = ((ByCustom)by).value();
    return driver.findElement(
        By.xpath("//div[contains(@class, 'form__select')]//label[contains(text(), '" + text + "')]/../.."));
  }

  @Override
  public void fill() {
    find().click();
    find()
        .findElement(
            By.xpath(".//ul[@class='form__sr-list']//li//button[contains(text(), '" + fillValueAsString() + "')]"))
        .click();
  }

}
