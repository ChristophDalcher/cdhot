package com.baloise.qa.st.hot.pages.services;

import org.openqa.selenium.By;

import com.baloise.qa.st.hot.base.elements.AHOTComponent;
import com.baloise.qa.st.hot.base.elements.HOTLinkButton;
import com.baloise.testautomation.taf.base._interfaces.IAnnotations.ByXpath;
import com.baloise.testautomation.taf.base._interfaces.IAnnotations.Data;
import com.baloise.testautomation.taf.base._interfaces.IAnnotations.DataProvider;
import com.baloise.testautomation.taf.base._interfaces.IAnnotations.DataProviderType;
import com.baloise.testautomation.taf.base.types.TafString;


@DataProvider(DataProviderType.EXCEL)

public class Services extends AHOTComponent {

      
  // erwarteter Text, in Datapool, um prüfen zu können, ob gewisse Texte auf der Seite vorhanden sind.
  // VerschiedeneTexte können im Datapool, mit ";" separiert, gespeichert werden, um mehrfachprüfungen 
  // zu machen via Methode "assertTexts" 
  @Data
  public TafString expectedTexts;
 
  // Links, Buttons und Eingabefelder dieser Seite
  
  
  @ByXpath("//h2[text()=' Testing & Consulting']")
  public HOTLinkButton testingandconsulting;
  
    
  public void assertTexts() {
    String[] texts = expectedTexts.asString().split(";");
    for (String text : texts) {
      System.out.println("Checking text: " + text);
      getBrowserFinder().getDriver().findElement(By.xpath("//*[contains(text(), '" + text + "')]"));
    }
   }
  }
