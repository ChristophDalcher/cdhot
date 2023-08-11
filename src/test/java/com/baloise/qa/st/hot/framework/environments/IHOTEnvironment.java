package com.baloise.qa.st.hot.framework.environments;

import org.junit.Assert;

public interface IHOTEnvironment extends IEnvironment {

  public static IHOTEnvironment getEnvironment() {
    String environment = System.getProperty("environment");
    if (environment == null) {
      return null;
    }
    environment = environment.toLowerCase().trim();
    switch (environment) {
     
      case "prod":
        return (IHOTEnvironment)production();
      
//      case "acc":
//        return (IHOTEnvironment)acceptance();
//      
//      case "int":
//        return (IHOTEnvironment)integration();
//   
//      case "acc":
//        return (IHOTEnvironment)acceptance();
         
      default:
        break;
    }
    Assert.fail("Umgebung konnte nicht identifiziert werden, daher auch kein Environment allozierbar");
    return null;
  }
  
//  public static HOTIntEnv integration() {
//    return new HOTIntEnv();
//  }
  
  public static HOTProdEnv production() {
    return new HOTProdEnv();
  }
  
}
