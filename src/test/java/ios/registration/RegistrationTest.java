package ios.registration;

import base.iOSBaseTest;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import screens.ios.RegistrationScreen;

/**
 * Created by matt-hfc on 1/17/17.
 */
public class RegistrationTest extends iOSBaseTest {

    @Test(dataProvider = "ios")
    public void registrationTest(IOSDriver driver) throws InterruptedException {
        setUp(driver);
        loginScreen.acceptNotificationsButton();

        // Make sure thank you text element does not have a weird visible state when not displayed
        Assert.assertFalse(loginScreen.getThankYouText().isDisplayed());
        loginScreen.getSignUpButton().click();

        RegistrationScreen registrationScreen = new RegistrationScreen(driver);
        registrationScreen.fillRegistrationForm(generateEmail());

        wait.until(ExpectedConditions.visibilityOf(loginScreen.getUsernameEditText()));
        Assert.assertTrue(loginScreen.getThankYouText().isDisplayed());
    }
}
