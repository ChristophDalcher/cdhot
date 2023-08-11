package com.baloise.qa.st.hot.pages.initialseite;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.baloise.qa.st.hot.base.elements.AHOTComponent;
import com.baloise.qa.st.hot.base.elements.HOTLinkButton;
import com.baloise.testautomation.taf.base._interfaces.IAnnotations.ByXpath;
import com.baloise.testautomation.taf.base._interfaces.IAnnotations.Data;
import com.baloise.testautomation.taf.base._interfaces.IAnnotations.DataProvider;
import com.baloise.testautomation.taf.base._interfaces.IAnnotations.DataProviderType;
import com.baloise.testautomation.taf.base.types.TafString;


@DataProvider(DataProviderType.EXCEL)

public class InitialSeite extends AHOTComponent {

      
  /* erwarteter Text, in Datapool, um prüfen zu können, ob gewisse Texte auf der Seite vorhanden sind.
  * VerschiedeneTexte können im Datapool, mit ";" separiert, gespeichert werden, um mehrfachprüfungen 
  // zu machen via Methode "assertTexts" */
  @Data
  public TafString expectedTexts;
 
  // Links, Buttons und Eingabefelder dieser Seite
  
  
  @ByXpath("//a[text()='Services']")
  public HOTLinkButton services;
  
  @ByXpath("//a[text()='About us']")
  public HOTLinkButton aboutus;
  
  @ByXpath("//a[text()='Join us']")
  public HOTLinkButton joinus;
  
  @ByXpath("//a[text()='Contact']")
  public HOTLinkButton contact;
  
  
  // Cookie consent: accept all cookies"
  public void acceptCookies() {
      sleep(8);
        Long timeout = getBrowserFinder().getTimeoutInMsecs();
    try {
      System.out.println("looking for button to accept all cookies");
      getBrowserFinder().setTimeoutInMsecs(1000L);
         getBrowserFinder().getDriver()
              .findElement(By.xpath("//div[text()='Accept All Cookies']")).click();
      System.out.println("Accept All Cookies found - I will now accept them");
        }
    catch (NoSuchElementException ex) {}
    finally {
      getBrowserFinder().setTimeoutInMsecs(timeout);
    }
  }
      
  public void assertTexts() {
    String[] texts = expectedTexts.asString().split(";");
    for (String text : texts) {
      System.out.println("Checking text: " + text);
      getBrowserFinder().getDriver().findElement(By.xpath("//*[contains(text(), '" + text + "')]"));
    }
   }
  }
