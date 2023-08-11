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
package com.baloise.qa.st.hot.base.testcasemanager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.baloise.qa.st.hot.framework.applications.IApplication;
import com.baloise.qa.st.hot.framework.browsers.IBrowser;
import com.baloise.qa.st.hot.framework.environments.IEnvironment;
import com.baloise.qa.st.hot.framework.locales.ILocale;
import com.baloise.qa.st.hot.framework.stacks.IStack;

public interface ITestcaseManager {

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.TYPE})
  public @interface TestcaseManager {
    Class<? extends ITestcaseManager> value();
  }

  public IApplication getApplication();

  public IBrowser getBrowser();

  public IEnvironment getEnvironment();

  public ILocale getLocale();

  public IStack getStack();

  public void reset();

  public void setApplication(IApplication application);

  public void setBrowser(IBrowser browser);

  public void setEnvironment(IEnvironment environment);

  public void setLocale(ILocale locale);

  public void setStack(IStack stack);

  public void startApplication();

  public void stopApplication();

}
