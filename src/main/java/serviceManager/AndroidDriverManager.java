package serviceManager;

import Constants.AppCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.qameta.allure.Allure;
import org.testng.annotations.*;

import java.time.Duration;

public class AndroidDriverManager {
    private AppiumDriverLocalService service;
    private static final ThreadLocal<AndroidDriver> driverThreadLocal = new ThreadLocal<>();

    @BeforeSuite
    public void startService(){
        service=AppiumServiceManager.composeService().driverName("uiautomator2").composed().buildService();
        service.start();
    }

    @BeforeClass
    @Parameters({"appName"})
    public void launchApp(@Optional("deltaExchange") String appName){
        UiAutomator2Options options = AppCapabilities.getAppCapabilities(appName);
        try {
            AndroidDriver driver = new AndroidDriver(service.getUrl(), options);
            Allure.step("Successfully Launched the application "+appName);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            Allure.addAttachment("Application Capabilities are",driver.getCapabilities().asMap().toString());
            driverThreadLocal.set(driver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public void tearDownApp(){
        AndroidDriver driver = driverThreadLocal.get();
        if(driver!=null ){
            driver.quit();
            driverThreadLocal.remove();
            Allure.step("Closing the Application");
        }
    }

    @AfterSuite
    public void stopService(){
        if (service.isRunning()){
            service.stop();
        }

    }

    public static AndroidDriver getAndroidDriver(){
        return driverThreadLocal.get();
    }
}
