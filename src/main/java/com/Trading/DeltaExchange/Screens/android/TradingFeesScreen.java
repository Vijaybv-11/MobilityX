package com.Trading.DeltaExchange.Screens.android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TradingFeesScreen extends BaseScreen {

    private final AndroidDriver driver;

    public TradingFeesScreen(AndroidDriver driver) {
        super(driver);
        this.driver=driver;
    }


    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Fees\").instance(0)")
    private WebElement fees_header;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@text=\"Buy Price\"]/..//android.widget.EditText)[1]")
    private WebElement buyPrice_Textbox;
    @AndroidFindBy(xpath = "(//android.widget.TextView[@text='Sell Price']/..//android.widget.EditText)[2]")
    private WebElement sellPrice_Textbox;
    @AndroidFindBy(xpath = "(//android.widget.TextView[@text='Qty (#']/..//android.widget.EditText)[1]")
    private WebElement qty_Textbox;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Notional\"]/following-sibling::android.widget.TextView[contains(@text,'$')]")
    private WebElement notionalValue_Text;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Trading Fees\"]/following-sibling::android.widget.TextView[contains(@text,'$')]")
    private WebElement tradingFees_Text;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Total Fees')]/following-sibling::android.widget.TextView[contains(@text,'$')]")
    private WebElement totalFees_Text;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@text=\"Net P&L\"]/following-sibling::android.widget.TextView)[2]")
    private WebElement netPandL_Text;

    public void selectFilter(String filterType){
        driver.findElement(By.xpath("//android.view.View[@content-desc='"+filterType+"']")).click();
        System.out.println("Selected Filter type as "+filterType);
    }

    public void selectSubFilters(String subFilterName){
        driver.findElement(By.xpath("//android.view.View[@content-desc='"+subFilterName+"']")).click();
        System.out.println("Selected Sub-Filter type as "+subFilterName);

    }



    public void enterBuyPrice(String buyPrice){
        buyPrice_Textbox.clear();
        buyPrice_Textbox.sendKeys(buyPrice);
        System.out.println("Entered Buy Price "+buyPrice);
    }
    public void enterSellPrice(String sellPrice){
        sellPrice_Textbox.clear();
        sellPrice_Textbox.sendKeys(sellPrice);
        System.out.println("Entered Sell Price "+sellPrice);
    }
    public void enterQty(String qty){
        qty_Textbox.clear();
        qty_Textbox.sendKeys(qty);
        System.out.println("Entered Qty "+qty);
    }

    //By Default maker will be enabled
    public void selectMakerOrTaker(String BuyOrSell ){
    if(BuyOrSell.equalsIgnoreCase("Buy")){
        driver.findElement(AppiumBy.xpath("(//android.widget.TextView[@text='Maker']/following-sibling::android.view.View)[1]")).click();
    }else {
        driver.findElement(AppiumBy.xpath("(//android.widget.TextView[@text='Taker']/following-sibling::android.view.View)[2]")).click();
    }
    }

    public void enterOptionValues(String valueType,String value){
        int index = valueType.equalsIgnoreCase("buy") ? 1
                : valueType.equalsIgnoreCase("sell") ? 2
                : valueType.equalsIgnoreCase("qty") ? 3
                : 0;
        driver.findElement(AppiumBy.xpath("(//android.widget.TextView[@text=\"Buy Premium\"]/..//android.widget.EditText)["+index+"]")).clear();
        driver.findElement(AppiumBy.xpath("(//android.widget.TextView[@text=\"Buy Premium\"]/..//android.widget.EditText)["+valueType+"]")).sendKeys(value);
        System.out.println("Entered "+value+" to "+valueType+" Textfield");
    }

    public void selectContract(String contractType){
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='"+contractType+"']")).click();
        System.out.println("selected contract as "+contractType);
    }

}
