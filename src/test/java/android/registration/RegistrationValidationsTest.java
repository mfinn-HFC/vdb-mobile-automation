package android.registration;

import base.AndroidBaseTest;
import enums.SwipeDirection;
import io.appium.java_client.TouchAction;
import junit.framework.Assert;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import screens.android.RegistrationScreen;

/**
 * Created by matt-hfc on 12/14/16.
 */
public class RegistrationValidationsTest extends AndroidBaseTest {

    public RegistrationValidationsTest(DesiredCapabilities capabilities) {
        super(capabilities);
    }

    @Test
    public void registrationValidationsTest() throws InterruptedException {
        waitForBugReportPromptToClose();

        loginScreen.getSignUpButton().click();
        RegistrationScreen registrationScreen = new RegistrationScreen(driver);
        hideKeyboard();

        try {
            registrationScreen.swipeByPercent(40, SwipeDirection.DOWN);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            TouchAction touchAction = new TouchAction(driver);
            touchAction.press(registrationScreen.getStreetAddressField()).moveTo(registrationScreen.getFullNameField()).release().perform();
        }

        registrationScreen.getSignUpButton().click();
        Assert.assertFalse(loginScreen.getLoginButton().isDisplayed());
    }
}