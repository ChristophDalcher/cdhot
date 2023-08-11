package com.baloise.qa.st.hot.base.elements;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baloise.qa.st.hot.base.finder.BaloiseBrFinder;
import com.baloise.qa.st.hot.base.testcasemanager.HOTTestcaseManager;
import com.baloise.qa.st.hot.base.testcasemanager.ITestcaseManager;
import com.baloise.qa.st.hot.tests.AHOTTest;
import com.baloise.testautomation.taf.base._base.ABase;

/**
 * 
 */
public abstract class ABaloiseComponent extends ABase {

  public static Logger logger = LoggerFactory.getLogger(ABaloiseComponent.class);

  @Override
  public BaloiseBrFinder getBrowserFinder() {
    AHOTTest root = (AHOTTest)findFirstParent(AHOTTest.class);
    Assert.assertNotNull("No root ATest found", root);
    ITestcaseManager manager = root.getManager();
    BaloiseBrFinder finder = ((HOTTestcaseManager)manager).getLoginFinder();
    finder.assumeDriverAssigned();
    return finder;
  }

}
