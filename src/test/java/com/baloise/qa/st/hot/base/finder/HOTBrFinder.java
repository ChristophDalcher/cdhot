/*
 * Copyright 2018 Baloise Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.baloise.qa.st.hot.base.finder;

import static org.junit.Assert.assertNotNull;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.baloise.testautomation.taf.browser.elements.BrFinder;

public class HOTBrFinder extends BrFinder {

	public HOTBrFinder(WebDriver driver, int timeoutInSeconds) {
		super(driver, timeoutInSeconds);
	}

	public void scrollIntoView(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)", "");
	}

	public Object executeJavascript(String script) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Object result = "";
		try {
			result = js.executeScript(script);
		} catch (Exception e) {
		}
		return result;
	}

	public void switchToFrameWithStartingId(String startingId) {
		assertDriverAssigned();
		getDriver().switchTo().frame(driver.findElement(By.xpath("//*[starts-with(@id, '" + startingId + "')]")));
	}

	public void switchToFrame(By by) {
		driver.switchTo().frame(driver.findElement(by));
	}

	public void switchToFrame(String nameOrId) {
		assertDriverAssigned();
		getDriver().switchTo()
				.frame(driver.findElement(By.xpath("//*[@id='" + nameOrId + "' or @name='" + nameOrId + "']")));
	}

	public boolean isAjaxDone() {
		try {
			return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
		} catch (Exception e) {
			// no jQuery present
			return true;
		}
	}

	public boolean hasNoSpinner() {
		String style = driver.findElement(By.id("loading:loading_start")).getAttribute("style");
		System.out.println(style);
		return style.contains("none");
	}

	// public boolean hasNoSpinner(){
	// return true;
	// }

	public boolean isJSDone() {
		return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
	}

	public void waitForAjaxDone(int seconds) {
		// try {
		// Thread.sleep(50);
		// }
		// catch (InterruptedException e) {}
		// long time = System.currentTimeMillis();
		// boolean done = false;
		// while (!done) {
		// done = hasNoSpinner() && isAjaxDone() && isJSDone();
		// if (System.currentTimeMillis() > time + 1000 * seconds) {
		// Logger logger = LoggerFactory.getLogger(SoBaEBankingBrFinder.class);
		// logger.warn("Ajax NOT completed after " + seconds + " seconds --> further
		// actions may not be correct anymore");
		// done = true;
		// }
		// }
	}

	@Override
	public void waitUntilLoadingComplete() {
		assertNotNull("Driver is NOT assigend --> no waitingUntilLoadingComplete possible", driver);
		waitForAjaxDone(120);
	}

}
