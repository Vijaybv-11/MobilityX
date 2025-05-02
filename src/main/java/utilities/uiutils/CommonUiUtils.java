package utilities.uiutils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.Map;

public class CommonUiUtils {


    public static void doubleTap(AppiumDriver driver, WebElement element){
        driver.executeScript("mobile: doubleClickGesture",
                ImmutableMap.of("elementId",((RemoteWebElement)element).getId()));
    }

    public static void longPress(AppiumDriver driver,WebElement element){
        driver.executeScript("mobile: longClickGesture", Map.of("elementId",((RemoteWebElement)element).getId()));
    }

    public static void dragAndDrop(AppiumDriver driver,WebElement source,WebElement Target){
        driver.executeScript("mobile: dragGesture", Map.of(
                "elementId", ((RemoteWebElement) source).getId(),
                "endX", Target.getLocation().getX()/2,
                "endY",Target.getLocation().getY(),
                "speed", 1000));
    }

    public static void performTap(AppiumDriver driver,WebElement element){
        driver.executeScript("mobile: clickGesture",ImmutableMap.of("elementId", ((RemoteWebElement) element).getId()));
    }

    public static void swipeGesture(AppiumDriver driver,WebElement element,String direction,float percent,int speed){
        driver.executeScript("mobile: swipeGesture",ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(),"direction", direction,"percent", percent,"speed", speed));
    }


}
