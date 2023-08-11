package com.baloise.qa.st.hot.tests;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.RuleChain;

import com.baloise.qa.st.hot.base.testcasemanager.HOTTestcaseManager;
import com.baloise.qa.st.hot.base.testcasemanager.ITestcaseManager.TestcaseManager;
import com.baloise.qa.st.hot.framework.applications.HoT;
import com.baloise.qa.st.hot.framework.applications.IApplication.Application;
import com.baloise.qa.st.hot.framework.reporting.EISClassWatcher;
import com.baloise.qa.st.hot.framework.reporting.EISRuleChain;
import com.baloise.qa.st.hot.pages.login.HoTLogin;
import com.baloise.testautomation.taf.base._base.ABase;

@TestcaseManager(HOTTestcaseManager.class)
@Application(HoT.class)
public abstract class AHOTTest extends ABase {

  HoTLogin login;
  
  @ClassRule
  public static EISClassWatcher classWatcher = new EISClassWatcher("target\\");

  public EISRuleChain ruleChain = new EISRuleChain(classWatcher);

  @Rule
  public RuleChain rules = ruleChain.getRules();

  public HOTTestcaseManager getManager() {
    return (HOTTestcaseManager)ruleChain.getManager();
  }

  protected void doLogin() {
	  sleep(6);
	  login.doLogin();
  }
 
 
}
