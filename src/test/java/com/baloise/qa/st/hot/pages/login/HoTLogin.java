package com.baloise.qa.st.hot.pages.login;

import com.baloise.qa.st.hot.base.elements.ABaloiseComponent;
import com.baloise.testautomation.taf.base._interfaces.IAnnotations.ByXpath;
import com.baloise.testautomation.taf.browser.elements.BrButton;
import com.baloise.testautomation.taf.browser.elements.BrStringInput;

public class HoTLogin extends ABaloiseComponent {
  
  @ByXpath("//input[@type='email']")
  private BrStringInput username;
  
  @ByXpath("//input[@name='passwd']")
  private BrStringInput password;

  @ByXpath("//*[@id=\'idSIButton9\']")
  private BrButton weiter;

  @ByXpath("//input[@type='submit']")
  private BrButton anmelden;

    public void doLogin() {
    username.fillWith(User.username);
    weiter.click();
    sleep(2);
    password.fillWith(User.password);
    anmelden.click();
  }

}
