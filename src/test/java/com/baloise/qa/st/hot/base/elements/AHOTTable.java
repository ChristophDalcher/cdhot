/*
 ===========================================================================
 @    $Author$
 @  $Revision$
 @      $Date$
 @
 ===========================================================================
 */
package com.baloise.qa.st.hot.base.elements;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import com.baloise.qa.st.hot.base.finder.HOTBrFinder;
import com.baloise.qa.st.hot.base.testcasemanager.HOTTestcaseManager;
import com.baloise.qa.st.hot.base.testcasemanager.ITestcaseManager;
import com.baloise.qa.st.hot.tests.AHOTTest;
import com.baloise.testautomation.taf.base._base.ATable;

/**
 * 
 */
public abstract class AHOTTable extends ATable {

  // @Override
  public WebElement find() {
    return getBrowserFinder().find(by);
  }

  @Override
  public HOTBrFinder getBrowserFinder() {
    AHOTTest root = (AHOTTest)findFirstParent(AHOTTest.class);
    Assert.assertNotNull("No root AGWTest found", root);
    ITestcaseManager manager = root.getManager();
    HOTBrFinder finder = ((HOTTestcaseManager)manager).getEISFinder();
    finder.assumeDriverAssigned();
    return finder;
  }

}
