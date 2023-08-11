package com.baloise.qa.st.hot.base.elements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.baloise.qa.st.hot.base.finder.HOTBrFinder;
import com.baloise.testautomation.taf.base._interfaces.IAnnotations.ByCustom;
import com.baloise.testautomation.taf.browser.elements.BrButton;

public class HOTDropdownMenu extends BrButton {

  @Override
  public WebElement brFindByCustom() {
    WebDriver driver = ((HOTBrFinder)getFinder()).getDriver();
    String text = ((ByCustom)by).value();
    return driver.findElement(By.xpath("//bal-multi-select-dropdown[@formcontrolname='" + text + "']"));
  }

  public void selectSome(String... items) {
    open();
    for (String item : items) {
      selectCheckbox(item);
    }
    close();
  }

  public void select(String item) {
    selectSome(item);
  }

  private void selectCheckbox(String item) {
    WebElement checkbox = findCheckbox(item);
    if (!isSelected(checkbox)) {
      checkbox.click();
    }
  }

  private WebElement findCheckbox(String item) {
    WebElement textElement = find().findElement(By.xpath(".//bal-checkbox//*[text()='" + item + "']"));
    while (true) {
      if (textElement.getTagName().equals("bal-checkbox")) {
        break;
      }
      textElement = textElement.findElement(By.xpath("./.."));
    }
    return textElement;
  }

  private boolean isSelected(WebElement checkbox) {
    List<WebElement> elements = checkbox.findElements(By.xpath(".//div[@class='checkbox-image']//*"));
    int pathCount = 0;
    for (WebElement element : elements) {
      if ("path".equals(element.getTagName())) {
        pathCount++;
      }
    }
    return pathCount == 2;
  }

  private void open() {
    if (!isExpanded()) {
      click();
    }
  }

  private void close() {
    if (isExpanded()) {
      click();
    }
  }

  private boolean isExpanded() {
    String expanded = find().findElement(By.xpath(".//button")).getAttribute("aria-expanded");
    System.out.println(expanded);
    return "true".equals(expanded);
  }
}
