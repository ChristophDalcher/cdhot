package com.baloise.qa.st.hot.pages.expertconsulting;

import org.openqa.selenium.By;

import com.baloise.qa.st.hot.base.elements.AHOTComponent;
import com.baloise.qa.st.hot.base.elements.HOTTextareaInput;
import com.baloise.testautomation.taf.base._interfaces.IAnnotations.ById;
import com.baloise.testautomation.taf.base._interfaces.IAnnotations.ByXpath;
import com.baloise.testautomation.taf.base._interfaces.IAnnotations.Check;
import com.baloise.testautomation.taf.base._interfaces.IAnnotations.Data;
import com.baloise.testautomation.taf.base._interfaces.IAnnotations.DataProvider;
import com.baloise.testautomation.taf.base._interfaces.IAnnotations.DataProviderType;
import com.baloise.testautomation.taf.base._interfaces.IAnnotations.Fill;
import com.baloise.testautomation.taf.base.types.TafString;
import com.baloise.testautomation.taf.browser.elements.BrButton;
import com.baloise.testautomation.taf.browser.elements.BrStringInput;

@DataProvider(DataProviderType.EXCEL)

public class ExpertConsulting extends AHOTComponent {
 
  // Links, Buttons and Fields
  
  @Fill
  @Check
  @ById("Name")
  public BrStringInput yourname;
    
  @Fill
  @Check
  @ById("Email-Form")
  public BrStringInput youremail;
  
  @Fill
  @Check
  @ById("Phone-Number-3")
  public BrStringInput phone;
  
  @Fill
  @Check
  @ById("your-company-2")
  public BrStringInput company;
  
  @Fill
  @Check
  @ById("message")
  public HOTTextareaInput message;
  
  
  /* If I find time, I will define this as a real checkbox and not just a button to click 
  *(checkbox would be clearer related checking whether checked or unchecked)
  but a BrButton to klick on will do, but is not absolutely nice
  */ 
  //@Fill
  @ByXpath("//*[@id=\"recaptcha-anchor\"]")
  public BrButton recaptchacheckbox;
    
  @ById("submit")
  public BrButton submit; 
  
  
//erwarteter Text, in Datapool, um prüfen zu können, ob gewisse Texte auf der Seite vorhanden sind.
 // VerschiedeneTexte können im Datapool, mit ";" separiert, gespeichert werden, um mehrfachprüfungen 
 // zu machen via Methode "assertTexts" 
  
//String to verify any defined texts on the page    
 @Data
 public TafString expectedTexts;
    
  public void assertTexts() {
    String[] texts = expectedTexts.asString().split(";");
    for (String text : texts) {
      System.out.println("Checking text: " + text);
      getBrowserFinder().getDriver().findElement(By.xpath("//*[contains(text(), '" + text + "')]"));
    }
   }
  
  
  public void iFrameWechsel() {
	  getBrowserFinder().switchToFrame(By.xpath("//iframe[@title='reCAPTCHA']"));
  }
  
  
  
  }
