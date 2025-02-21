package com.baloise.qa.st.hot.framework.reporting;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Hashtable;
import java.util.Map;

import org.junit.Assert;
import org.junit.AssumptionViolatedException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baloise.qa.st.hot.base.testcasemanager.ITestcaseManager;
import com.baloise.qa.st.hot.base.testcasemanager.ITestcaseManager.TestcaseManager;
import com.baloise.testautomation.taf.base._base.AnnotationHelper;

public class EISClassWatcher extends TestWatcher {


  public static Logger logger = LoggerFactory.getLogger(EISClassWatcher.class);;

  private static String nodeIndex = "";
  // private static GWClassWatcher singleton = null;
  // public static GWClassWatcher GetSingleton() {
  // if (singleton == null) {
  // singleton = GetSingleton("\\\\itq.linux.balgroupit.com\\transfer\\JenkinsResults\\");
  // }
  // return singleton;
  // }
  // public static GWClassWatcher GetSingleton(String path) {
  // if (singleton == null) {
  // singleton = new GWClassWatcher(path);
  // }
  // else {
  // singleton.path = path;
  // }
  // return singleton;
  // }
  //
  public String path = "";

  private boolean allPassed = true;

  private boolean debug = false;

  private Map<String, String> previousResults = new Hashtable<String, String>();

  private ITestcaseManager manager = null;
  public boolean illegalVersion = false;

  public EISClassWatcher(String path) {
    this.path = path;
    init();
  }

  public ITestcaseManager getManager() {
    return manager;
  }

  private String getExludedVersionsFilename() {
    return "target/unsupported_version.excluded";
  }

  private void init() {
    System.out.println("GWClassWatcher init...");
    String runId = "";
    int buildId = -1;
    if (!debug) {
      runId = new Long(System.currentTimeMillis()).toString();
    }
  }

  public boolean needsToRun(Description description) {
    String result = previousResults.get(description.getClassName());
    if (result == null) {
      return true;
    }
    return "failed".equalsIgnoreCase(result);
  }

  private void readPreviousResult(BufferedReader br) throws IOException {
    String line = br.readLine();
    while (line != null) {
      logger.info("Reading previous result for: " + line);
      String[] parts = line.split("-->");
      previousResults.put(parts[0], parts[1]);
      line = br.readLine();
    }
    br.close();
  }

  public void setToFailed() {
    allPassed = false;
  }

  @Override
  protected void skipped(AssumptionViolatedException e, Description description) {
    logger.info("GWClassWatcher --> skipped: " + description);
    logger.info("Illegal version: " + illegalVersion);
    if (illegalVersion) {
      illegalVersion = false;
      String path = getExludedVersionsFilename();
      logger.info("clear / create 'excluded because of unsupported version'-file: " + path);
      if (!new File(path).exists()) {
        try {
          Path fullpath = Paths.get(path);
          Files.write(fullpath, "".getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }
        catch (Exception e1) {
          e1.printStackTrace();
        }
      }
      try {
        logger.info("Writing description: " + description + " to: " + getExludedVersionsFilename());
        Files.write(Paths.get(path),
            (description.toString() + " --> skipped because of unsupported version" + System.lineSeparator()).getBytes(),
            StandardOpenOption.APPEND);
      }
      catch (IOException e1) {
        e1.printStackTrace();
      }
    }
    super.skipped(e, description);
  }

  @Override
  protected void starting(Description description) {
    logger.info("Starting test on class: " + description);
    TestcaseManager annotation = AnnotationHelper.getAnnotationInHierarchy(description.getTestClass(),
        TestcaseManager.class);
    Assert.assertNotNull("Keine Anntoation @TestcaseMananger gefunden --> Test kann nicht gestartet werden",
        annotation);
    try {
      // Constructor<?> c = annotation.value().getConstructor(Description.class);
      manager = (ITestcaseManager)annotation.value().newInstance();
    }
    catch (Exception e) {
      Assert.fail("Instantiierung TestcaseManager fehlgeschlagen");
    }
    allPassed = true;
  }

  @Override
  protected void succeeded(Description description) {
    // appendResult(description);
  }

}
