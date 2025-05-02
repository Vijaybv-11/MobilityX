package utilities.uiutils;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import static serviceManager.AndroidDriverManager.getAndroidDriver;


public class AndroidUtil {


    public static void switchContext(String contextName){
        getAndroidDriver().context(contextName);
        Allure.step("Switched to Context "+contextName);

    }

    public void acceptAlert() {
        getAndroidDriver().switchTo().alert().accept();
        Allure.step("Accepted Alert");

    }
    public void dismissAlert() {
        getAndroidDriver().switchTo().alert().dismiss();
        Allure.step("Dismissed Alert");

    }

    public static void performSwipe(FingerGestureUtils.Direction direction, int distance){
        FingerGestureUtils gestureUtils=new FingerGestureUtils(getAndroidDriver());
        gestureUtils.swipe(direction,distance);
        Allure.step("Performed Swipe in the direction"+direction);
    }

    public static void swipeUntilElementIsVisible(FingerGestureUtils.Direction direction, WebElement target,int distance,int maxSwipes){
        FingerGestureUtils gestureUtils=new FingerGestureUtils(getAndroidDriver());
        int attempt=0;

        while (attempt<maxSwipes){
            try {
                if (target.isDisplayed()){
                    break;
                }
            } catch (NoSuchElementException e) {
                gestureUtils.swipe(direction,distance);
                attempt++;
            }
            if (attempt>=maxSwipes){
                System.out.println("Max Attempts reached");
            }
        }
    }

    public static void waitUntilElementIsVisible(WebElement element,int timeout){
        WebDriverWait wait= new WebDriverWait(getAndroidDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static String getScreenShot(String testName){
        TakesScreenshot ts = (TakesScreenshot) getAndroidDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String destination = System.getProperty("user.dir") + "/screenshots/" + testName + " - " + timeStamp + ".png";
        try {
            File finalDestination = new File(destination);
            finalDestination.getParentFile().mkdirs();
            Files.copy(source.toPath(), finalDestination.toPath());
        } catch (IOException ignored) {

        }
        return destination;
    }

    public static String getScreenShotAsBase64() {
        TakesScreenshot ts = (TakesScreenshot) getAndroidDriver();
        return ts.getScreenshotAs(OutputType.BASE64);
    }
    public static void rotateToLandscape() {
        getAndroidDriver().rotate(ScreenOrientation.LANDSCAPE);
        Allure.step("Screen Orientation set to Landscape");

    }
    public static void rotateToPortrait() {
        getAndroidDriver().rotate(ScreenOrientation.PORTRAIT);
        Allure.step("Screen Orientation set to Portrait");
    }

}
