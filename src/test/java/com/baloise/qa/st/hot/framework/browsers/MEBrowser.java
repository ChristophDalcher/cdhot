package com.baloise.qa.st.hot.framework.browsers;

import com.microsoft.edge.seleniumtools.EdgeDriver;
import com.microsoft.edge.seleniumtools.EdgeOptions;
import org.openqa.selenium.WebDriver;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MEBrowser extends ABrowser {

  private static final String EDGEDRIVER = "msedgedriver_114.exe";

  @SuppressWarnings("removal")
@Override
  protected WebDriver getNewDriver() {
     // killDriver(); tut noch nicht so richtig, SSO wird nicht umgangen
      killDriver();

      System.setProperty("webdriver.edge.driver", IBrowser.getPath(EDGEDRIVER));
      EdgeOptions options = new EdgeOptions();
      Map<String, Object> prefs = new HashMap<String, Object>();
     //prefs.put("download.default_directory", Config.CLIENT_TEMP_FOLDER);
      prefs.put("download.prompt_for_download", false);
      prefs.put("download.directory_upgrade", true);
      prefs.put("safebrowsing.enabled", true);
      prefs.put("plugins.always_open_pdf_externally", true);
      options.setExperimentalOption("prefs", prefs);
      if (!getComputerName().equalsIgnoreCase("MSEDGEWIN10")) {
        String pathToUserDataDir =
                "C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Local\\Microsoft\\Edge\\User Data";
          //  Logger.logAction("Using the default profile from " + pathToUserDataDir, 3);
        options.addArguments("user-data-dir=" + pathToUserDataDir);
        options.addArguments("profile-directory=Default");
        options.addArguments("InPrivate");
      }
      WebDriver driver = new EdgeDriver(options);
      driver.manage().timeouts().implicitlyWait(new Double(20 * 1000).longValue(), TimeUnit.MILLISECONDS);
      driver.manage().window().maximize();
      return driver;

  }

  @Override
  public void killDriver() {
    System.out.println("Will 'taskkill' running edgedriver(s)");
    runCommand("Taskkill /F /T /FI \"Imagename eq msedgedriver*\"");
    runCommand("Taskkill /F /T /FI \"Imagename eq msedge.exe*\"");
  }

    public static String getComputerName() {
        String name = "";
        try {
            name = InetAddress.getLocalHost().getHostName();
        }
        catch (UnknownHostException e) {
            System.out.println("Could not fetch computername!");
        }
        return name;
    }
}
