package android.registration;

import api.ActivateUserClient;
import api.User;
import base.AndroidBaseTest;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import screens.android.RegistrationScreen;
import screens.android.SearchScreen;
import screens.android.TermsAndConditionsScreen;

/**
 * Created by matt-hfc on 12/14/16.
 */
public class RegistrationTest extends AndroidBaseTest {

    @Test(dataProvider = "drivers")
    public void registrationTest(DesiredCapabilities capabilities) throws InterruptedException {
        setUp(capabilities, this.getClass());
        waitForBugReportPromptToClose();
        registerActivateNewUser();

        TermsAndConditionsScreen termsAndConditionsScreen = loginScreen.loginNewUser(user);
        SearchScreen searchScreen = termsAndConditionsScreen.acceptTerms();
        Assert.assertTrue(searchScreen.getSearchButton().isDisplayed());
    }
}