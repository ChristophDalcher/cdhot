package com.baloise.qa.st.hot.framework.browsers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class IEBrowser extends ABrowser {

  private static final String IEDRIVERSERVER = "IEDriverServer_3_14_0.exe";

  @SuppressWarnings("removal")
@Override
  protected WebDriver getNewDriver() {
    // Single Sign Off ausschalten
    System.out.println("----- vor Setzen der Registry -----");
    runCommand(
        "REG ADD \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\" /v EnableNegotiate /t REG_DWORD /d 0x0 /f");

    // alle IE-Zones --> Protected Mode einschalten
    // runCommand("REG ADD \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\0\" /v 2500
    // /t REG_DWORD /d 0x0 /f");
    runCommand(
        "REG ADD \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\1\" /v 2500 /t REG_DWORD /d 0x0 /f");
    runCommand(
        "REG ADD \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\2\" /v 2500 /t REG_DWORD /d 0x0 /f");
    runCommand(
        "REG ADD \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\3\" /v 2500 /t REG_DWORD /d 0x0 /f");
    runCommand(
        "REG ADD \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\4\" /v 2500 /t REG_DWORD /d 0x0 /f");
    System.out.println("----- nach Setzen der Registry -----");
    
    System.setProperty("webdriver.ie.driver", IBrowser.getPath(IEDRIVERSERVER));
    DesiredCapabilities dc = new DesiredCapabilities();
    dc.setCapability(CapabilityType.OVERLAPPING_CHECK_DISABLED, true);
    // dc.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, "false");
    // dc.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);

    InternetExplorerOptions ieo = new InternetExplorerOptions();
    ieo.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, 1);

    // ieo.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, 1);
    // ieo.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, 1);

    WebDriver driver = null;
    List<String> runningIExplorerIDs = getRunningIExplorerIDs();
    try {
      driver = new InternetExplorerDriver(ieo);
      driver.toString();
    }
    catch (Throwable t) {
      for (String id : getRunningIExplorerIDs()) {
        if (!runningIExplorerIDs.contains(id)) {
          runCommand("taskkill /F /PID " + id);
        }
      }
      killDriver();
      throw t;
    }
    driver.manage().timeouts().implicitlyWait(new Double(20 * 1000).longValue(), TimeUnit.MILLISECONDS);
    driver.manage().window().maximize();
    return driver;
  }

  public List<String> getRunningIExplorerIDs() {
    List<String> processes = new ArrayList<String>();
    try {
      String line;
      Process p = Runtime.getRuntime().exec("tasklist.exe /fo csv /nh");
      BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
      while ((line = input.readLine()) != null) {
        if (!line.trim().isEmpty()) {
          String[] elements = line.split(",");
          if ("\"iexplore.exe\"".equalsIgnoreCase(elements[0])) {
            processes.add(elements[1].replace("\"", ""));
          }
        }
      }
      input.close();
    }
    catch (Exception err) {
      err.printStackTrace();
    }
    return processes;
  }

  @Override
  public void killDriver() {
    System.out.println("Will 'taskkill' running IEDriverServer(s): " + IEDRIVERSERVER);
    runCommand("taskkill /F /IM " + IEDRIVERSERVER);
  }

}
