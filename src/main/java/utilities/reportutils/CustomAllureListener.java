package utilities.reportutils;

import io.qameta.allure.Allure;
import org.openqa.selenium.NoSuchElementException;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.uiutils.AndroidUtil;

public class CustomAllureListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        Allure.step("Starting test: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Allure.step("Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Allure.step("Test failed: " + result.getName());

        Throwable throwable = result.getThrowable();
        if (throwable != null) {
            Allure.addAttachment("Exception", throwable.toString());
            Allure.addAttachment("Stacktrace", getStackTrace(throwable));
        }

        if (throwable instanceof NoSuchElementException) {
            Allure.addAttachment("Screenshot", AndroidUtil.getScreenShot(result.getTestName()));
            Allure.addAttachment("Base 64 Image", AndroidUtil.getScreenShotAsBase64());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Allure.step("Test skipped: " + result.getName());
    }

    private String getStackTrace(Throwable throwable) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : throwable.getStackTrace()) {
            sb.append(element.toString()).append("\n");
        }
        return sb.toString();
    }
}
