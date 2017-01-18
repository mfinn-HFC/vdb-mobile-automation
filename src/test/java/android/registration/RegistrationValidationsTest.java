package android.registration;

import base.AndroidBaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Test;
import screens.android.RegistrationScreen;

/**
 * Created by matt-hfc on 12/14/16.
 */
public class RegistrationValidationsTest extends AndroidBaseTest {

    @Test(dataProvider = "drivers")
    public void registrationValidationsTest(AndroidDriver driver) throws InterruptedException {
        setUp(driver);
        waitForBugReportPromptToClose();

        loginScreen.getSignUpButton().click();
        RegistrationScreen registrationScreen = new RegistrationScreen(driver);

        driver.hideKeyboard();
        registrationScreen.getSignUpButton().click();
        Thread.sleep(1000);
    }
}