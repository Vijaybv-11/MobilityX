package com.Trading.DeltaExchange.Screens.android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class HomeScreen extends BaseScreen {

    private AndroidDriver driver;

    public HomeScreen(AndroidDriver driver) {
        super(driver);
        this.driver=driver;
    }

    @AndroidFindBy(uiAutomator = "new UiSelector().text('Home')")
    private WebElement home_navigation_button;

    @AndroidFindBy(xpath = "//android.view.View[@text='Markets']")
    private WebElement markets_navigation_button;

    @AndroidFindBy(xpath = "//android.view.View[@text='Trade']")
    private WebElement trade_navigation_button;

    @AndroidFindBy(xpath = "//android.view.View[@text='Chart/ Book']")
    private WebElement chart_navigation_button;

    @AndroidFindBy(xpath = "//android.view.View[@text='Portfolio']")
    private WebElement portfolio_navigation_button;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='More']")
    private WebElement more_button;



    public void selectFilterTab(String tabName){
        driver.findElement(AppiumBy.xpath("//android.view.View[@text='"+tabName+"']")).click();
        System.out.println("Selected Filter Tab "+tabName);
    }

    public void selectTradeType(String tradeType){
        driver.findElement(AppiumBy.xpath("//android.view.View[@text='"+tradeType+"']")).click();
        System.out.println("Selected trade type as  "+tradeType);
    }

    public void tapOnMoreButton(){
        more_button.click();
        System.out.println("Tapped on More Button");
    }



}
