package serviceManager;

import Constants.AppCapabilities;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.qameta.allure.Allure;
import org.testng.annotations.*;

import java.time.Duration;

public class IOSDriverManager {

    private AppiumDriverLocalService service;
    private static final ThreadLocal<IOSDriver> driverThreadLocal = new ThreadLocal<>();

    @BeforeSuite
    public void startService(){
        service=AppiumServiceManager.composeService().driverName("XCUITest").composed().buildService();
        service.start();
    }

    @BeforeClass
    @Parameters({"appName"})
    public void launchApp(@Optional("Instagram") String appName){
        UiAutomator2Options options = AppCapabilities.getAppCapabilities(appName);
        try {
            IOSDriver driver = new IOSDriver(service.getUrl(), options);
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
        IOSDriver driver = driverThreadLocal.get();
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

    public static IOSDriver getIosDriver(){
        return driverThreadLocal.get();
    }
}
