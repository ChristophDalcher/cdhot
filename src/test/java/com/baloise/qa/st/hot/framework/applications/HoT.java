package com.baloise.qa.st.hot.framework.applications;

import com.baloise.qa.st.hot.base.testcasemanager.ITestcaseManager;

public class HoT implements IApplication {

  @Override
  public String getUrl(ITestcaseManager manager) {
  //  HOTTestcaseManager tm = (HOTTestcaseManager)manager;
    
    
    return "https://houseoftest.ch";
    
    
  //  return "https://" + tm.getEnvironment().getUrlPart() + "rest der url";
    
    
  // bisher int ohne SSO    return "https://" + tm.getEnvironment().getUrlPart() + "rest der url";
      //+ tm.getLocale().getUrlPart()
  }

}
