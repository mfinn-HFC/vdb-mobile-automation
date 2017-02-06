package ios.registration;

import api.ActivateUserClient;
import api.User;
import base.iOSBaseTest;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import screens.ios.RegistrationScreen;
import screens.ios.SearchScreen;
import screens.ios.TermsAndConditionsScreen;

/**
 * Created by matt-hfc on 1/17/17.
 */
public class RegistrationTest extends iOSBaseTest {

    @Test(dataProvider = "ios")
    public void registrationTest(DesiredCapabilities capabilities) throws InterruptedException {
        setUp(capabilities, this.getClass());
        loginScreen.acceptNotificationsButton();

        // Make sure thank you text element does not have a weird visible state when not displayed
        Assert.assertFalse(loginScreen.getThankYouText().isDisplayed());

        registerActivateNewUser();

        TermsAndConditionsScreen termsAndConditionsScreen = loginScreen.newUserLogin(user);
        Assert.assertTrue(termsAndConditionsScreen.validatePartialTermsAndConditionsText());
        SearchScreen searchScreen = termsAndConditionsScreen.acceptTerms();
        Assert.assertTrue(searchScreen.getFromValueInput().isDisplayed());
    }
}
