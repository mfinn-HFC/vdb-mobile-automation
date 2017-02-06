package android.registration;

import base.AndroidBaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import screens.android.RegistrationScreen;

/**
 * Created by matt-hfc on 12/14/16.
 */
public class RegistrationValidationsTest extends AndroidBaseTest {

    @Test(dataProvider = "drivers")
    public void registrationValidationsTest(DesiredCapabilities capabilities) throws InterruptedException {
        setUp(capabilities, this.getClass());
        waitForBugReportPromptToClose();

        loginScreen.getSignUpButton().click();
        RegistrationScreen registrationScreen = new RegistrationScreen(driver);

        hideKeyboard();
        registrationScreen.getSignUpButton().click();
        Thread.sleep(1000);
    }
}