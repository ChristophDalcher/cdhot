package com.baloise.qa.st.hot.framework.reporting;

import java.lang.annotation.Annotation;

import org.junit.Assert;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.baloise.qa.st.hot.base.testcasemanager.ITestcaseManager;
import com.baloise.qa.st.hot.framework.applications.IApplication.Application;
import com.baloise.qa.st.hot.framework.browsers.IBrowser.Browser;
import com.baloise.qa.st.hot.framework.environments.IEnvironment;
import com.baloise.qa.st.hot.framework.environments.IEnvironment.Environment;
import com.baloise.qa.st.hot.framework.locales.ILocale.Locale;
import com.baloise.qa.st.hot.framework.stacks.IStack.Stack;
import com.baloise.testautomation.taf.base._base.AnnotationHelper;

public class EISTestcaseWatcher extends TestWatcher {

  private EISClassWatcher classWatcher;
  public static IEnvironment env = null;

  public EISTestcaseWatcher(EISClassWatcher classWatcher) {
    this.classWatcher = classWatcher;
  }

  @Override
  protected void finished(Description description) {
    getManager().stopApplication();
  }

  private <T extends Annotation> T getAnnotation(Description description, Class<T> klass) {
    // method level
    T annotation = description.getAnnotation(klass);
    if (annotation == null) {
      annotation = AnnotationHelper.getAnnotationInHierarchy(description.getTestClass(), klass);
    }
    return annotation;
  }

  public ITestcaseManager getManager() {
    return classWatcher.getManager();
  }

  public void init(Description description) {
    Assert.assertNotNull("To start a test, either class or test-method must be annotated with @getManager()",
        getManager());

    getManager().reset();
    
    Application application = getAnnotation(description, Application.class);
    if (application != null) {
      try {
        getManager().setApplication(application.value().newInstance());
      }
      catch (Exception e) {}
    }

    Browser browser = getAnnotation(description, Browser.class);
    if (browser != null) {
      try {
        getManager().setBrowser(browser.value().newInstance());
      }
      catch (Exception e) {}
    }

    Environment environment = getAnnotation(description, Environment.class);
    System.out.println("Environment from annotation: " + environment);
    if (environment != null) {
      try {
        getManager().setEnvironment(environment.value().newInstance());
      }
      catch (Exception e) {}
    }
    if (env != null) {
      System.out.println("Set environment from global variable: " + env);
      getManager().setEnvironment(env);
    }
    
    Stack stack = getAnnotation(description, Stack.class);
    if (stack != null) {
      try {
        getManager().setStack(stack.value().newInstance());
      }
      catch (Exception e) {}
    }

    Locale locale = getAnnotation(description, Locale.class);
    if (locale != null) {
      try {
        getManager().setLocale(locale.value().newInstance());
      }
      catch (Exception e) {}
    }

  }

  @Override
  protected void starting(Description description) {
    init(description);
    getManager().startApplication();
  }

}
