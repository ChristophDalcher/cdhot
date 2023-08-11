package com.baloise.qa.st.hot.framework.environments;

import com.baloise.testautomation.taf.base.types.TafId;

public class HOTProdEnv implements IHOTEnvironment {

  @Override
  public String getUrlPart() {
    return "prod-";
  }

  @Override
  public void setTafGlobalMandant() {
    TafId.SetGlobalMandant("prod");
  }

  

}
