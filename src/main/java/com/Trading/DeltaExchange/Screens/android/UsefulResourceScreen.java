package com.Trading.DeltaExchange.Screens.android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class UsefulResourceScreen extends BaseScreen {

    private final AndroidDriver driver;
    public UsefulResourceScreen(AndroidDriver driver) {
        super(driver);
        this.driver=driver;
    }

    @AndroidFindBy(uiAutomator = "new UiSelector().text('Useful Resources')")
    private WebElement usefulResource_Header;


    public void selectResources(String resourceName){
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='"+resourceName+"']")).click();
        System.out.println("Selected Resource Type "+ resourceName);
    }

    public void verifyUsefulResourceIsDisplayed(){
        assert usefulResource_Header.isDisplayed();
    }

}
