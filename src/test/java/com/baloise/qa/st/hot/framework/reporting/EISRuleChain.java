package com.baloise.qa.st.hot.framework.reporting;

import org.junit.rules.RuleChain;

import com.baloise.qa.st.hot.base.testcasemanager.ITestcaseManager;
//import com.baloise.testautomation.taf.base.testing.ScreenshotOnFailed;
//import com.baloise.testautomation.taf.base.testing.ScreenshotRule;

public class EISRuleChain {

  private EISMethodWatcher methodWatcher;
  private EISTestcaseWatcher gwTestcaseWatcher;
 // private ScreenshotRule ssof;

  public EISRuleChain(EISClassWatcher classWatcher) {
    methodWatcher = new EISMethodWatcher(classWatcher);
    gwTestcaseWatcher = new EISTestcaseWatcher(classWatcher);
    //ssof = new ScreenshotOnFailed("target\\").withClassName();
  }

  public ITestcaseManager getManager() {
    return gwTestcaseWatcher.getManager();
  }

  public RuleChain getRules() {
    return RuleChain.outerRule(methodWatcher).around(gwTestcaseWatcher); //.around(ssof);
  }

}
