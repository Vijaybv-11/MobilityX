import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.testng.annotations.Test;
import serviceManager.AndroidDriverManager;

public class LaunchTest extends AndroidDriverManager {

    @Test
    public void test1(){
        step1();
        step2();
        Allure.step("Hello Boss");
    }

    @Step
    public void step1(){
        Allure.step("Step 1");
    }
    @Step
    public void step2(){
        Allure.step("Step 2");
    }
}
