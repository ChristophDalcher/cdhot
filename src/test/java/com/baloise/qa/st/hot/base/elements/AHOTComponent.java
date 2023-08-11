package com.baloise.qa.st.hot.base.elements;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baloise.qa.st.hot.base.finder.HOTBrFinder;
import com.baloise.qa.st.hot.base.testcasemanager.HOTTestcaseManager;
import com.baloise.qa.st.hot.base.testcasemanager.ITestcaseManager;
import com.baloise.qa.st.hot.tests.AHOTTest;
import com.baloise.testautomation.taf.base._base.ABase;

/**
 * 
 */
public abstract class AHOTComponent extends ABase {

  public static Logger logger = LoggerFactory.getLogger(AHOTComponent.class);

  @Override
  public HOTBrFinder getBrowserFinder() {
    AHOTTest root = (AHOTTest)findFirstParent(AHOTTest.class);
    Assert.assertNotNull("No root test found", root);
    ITestcaseManager manager = root.getManager();
    HOTBrFinder finder = ((HOTTestcaseManager)manager).getEISFinder();
    finder.assumeDriverAssigned();
    return finder;
  }

  public void sleep(int seconds) {
    try {
      Thread.sleep(seconds * 1000);
    }
    catch (Exception e) {}
  }

  protected void switchToFrameWithStartingId(String id) {
    System.out.println("Switch to frame, id starting with: " + id);
    getBrowserFinder().switchToFrameWithStartingId(id);
  }

    
  protected void switchToFrame(String... frames) {
    for (String frame : frames) {
      System.out.println("Switch to: " + frame);
      getBrowserFinder().switchToFrame(frame);
    }
  }
  
}
