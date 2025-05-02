package Constants;

import io.appium.java_client.android.options.UiAutomator2Options;
import utilities.datautil.PropertyUtil;

import java.util.HashMap;
import java.util.Map;

public class AppCapabilities {

    public static UiAutomator2Options getAppCapabilities(String appName){
        AppCapabilities capabilities= new AppCapabilities();
        Map<String ,UiAutomator2Options> appConfig=new HashMap<>();
        appConfig.put("deltaexchange",capabilities.getDeltaExchangeCapabilities());
        return appConfig.getOrDefault(appName,capabilities.getDeltaExchangeCapabilities());
    }


    public UiAutomator2Options getDeltaExchangeCapabilities(){
        Map<String, String> caps=PropertyUtil.loadPropertiesAsMaps(FilePaths.deltaExchangeConfig);
       return new UiAutomator2Options().setPlatformName(caps.get("platformName"))
               .setAppPackage(caps.get("appPackage")).setAppActivity(caps.get("appActivity")).setAutomationName(caps.get("automationName")).setNoReset(Boolean.parseBoolean(caps.get("noReset")));
    }
}
