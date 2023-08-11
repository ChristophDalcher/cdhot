package com.baloise.qa.st.hot.base.elements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.baloise.qa.st.hot.base.finder.HOTBrFinder;
import com.baloise.testautomation.taf.base._interfaces.IAnnotations.ByCustom;
import com.baloise.testautomation.taf.browser.elements.BrCheckbox;

public class HOTCheckbox extends BrCheckbox {

  @Override
  public WebElement brFindByCustom() {
    WebDriver driver = ((HOTBrFinder)getFinder()).getDriver();
    String text = ((ByCustom)by).value();
    return driver.findElement(By.xpath("//bal-checkbox//div[contains(@class, 'checkbox-wrapper') and text() = '" + text + "']/div"));
  }

  
  @Override
  public boolean isSelected() {
    List<WebElement> elements = find().findElements(By.xpath(".//*"));
    int pathCount = 0;
    for (WebElement element : elements) {
      if ("path".equals(element.getTagName())) {
        pathCount++;
      }
    }
    return pathCount == 2;
  }

}
