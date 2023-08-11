package com.baloise.qa.st.hot.framework.reporting;

import java.lang.annotation.Annotation;

import org.junit.Assume;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baloise.testautomation.taf.base._base.AnnotationHelper;

public class EISMethodWatcher extends TestWatcher {

  public static Logger logger = LoggerFactory.getLogger(EISMethodWatcher.class);;

  private EISClassWatcher classWatcher;

  public EISMethodWatcher(EISClassWatcher cw) {
    classWatcher = cw;
  }

  @Override
  public Statement apply(final Statement base, final Description description) {
    Assume.assumeTrue("Methode kann/darf nicht ausgeführt werden", canRun(description));
    if (classWatcher.needsToRun(description)) {
      logger.info(description + " --> not passed --> run it!");
      return super.apply(base, description);
    }
    else {
      logger.info(description + " --> passed --> do NOT run it but set directly to passed!");
      Statement statement = new Statement() {
        @Override
        public void evaluate() throws Throwable {}
      };
      return super.apply(statement, description);
    }
  }

  private boolean canRun(Description description) {
    return true;
  }

  @Override
  protected void failed(Throwable e, Description description) {
    classWatcher.setToFailed();
  }

  private <T extends Annotation> T getAnnotation(Description description, Class<T> klass) {
    // method level
    T annotation = description.getAnnotation(klass);
    if (annotation == null) {
      annotation = AnnotationHelper.getAnnotationInHierarchy(description.getTestClass(), klass);
    }
    return annotation;
  }

  private void reportResult(Description description, String testResult) {
    logger.info("Finished test: " + description + " -->  " + testResult);
  }

  @Override
  protected void skipped(org.junit.internal.AssumptionViolatedException e, Description description) {
    logger.info("Skipped: " + e.getMessage());
  }

  @Override
  protected void starting(Description description) {}

  @Override
  protected void succeeded(Description description) {
    reportResult(description, "passed");
  }

}
