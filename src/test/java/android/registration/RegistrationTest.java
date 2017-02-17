package android.registration;

import base.AndroidBaseTest;
import junit.framework.Assert;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import screens.android.SearchScreen;
import screens.android.TermsAndConditionsScreen;

/**
 * Created by matt-hfc on 12/14/16.
 */
public class RegistrationTest extends AndroidBaseTest {

    public RegistrationTest(DesiredCapabilities capabilities) {
        super(capabilities);
    }

    @Test
    public void registrationTest() throws InterruptedException {
        waitForBugReportPromptToClose();
        registerActivateNewUser();

        TermsAndConditionsScreen termsAndConditionsScreen = loginScreen.loginNewUser(user);
        SearchScreen searchScreen = termsAndConditionsScreen.acceptTerms();
        Assert.assertTrue(searchScreen.getSearchButton().isDisplayed());
    }
}