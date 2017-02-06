package screens.ios;

import api.User;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import screens.AndroidBaseScreen;
import screens.IOSBaseScreen;

/**
 * Created by matt-hfc on 12/21/16.
 */
public class LoginScreen extends IOSBaseScreen {

    public final String defaultPassword = "happiness4U";

    public final String usernameEditTextId = "Username or Email Address";
    @iOSFindBy(id = usernameEditTextId)
    private IOSElement usernameEditText;

    @iOSFindBy(id = "Password")
    private IOSElement passwordEditText;

    @iOSFindBy(id = "LOGIN")
    private IOSElement loginButton;

    @iOSFindBy(id = "SIGN UP")
    private IOSElement signUpButton;

    @iOSFindBy(id = "Allow")
    private IOSElement allowNotificationsButton;

    @iOSFindBy(id = "OK")
    private IOSElement OKNotificationsButton; // For iOS < 10

    @iOSFindBy(id = "Staging") // In case you need to switch to staging, most tests should be staging only
    private IOSElement stagingButton;

    @iOSFindBy(id = "Thank you!")
    private IOSElement thankYouText;

    public LoginScreen(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public String getUsernameEditTextId() {
        return usernameEditTextId;
    }

    public IOSElement getStagingButton() {
        return stagingButton;
    }

    public IOSElement getOKNotificationsButton() {
        return OKNotificationsButton;
    }

    public IOSElement getAllowNotificationsButton() {
        return allowNotificationsButton;
    }

    public IOSElement getLoginButton() {
        return loginButton;
    }

    public IOSElement getPasswordEditText() {
        return passwordEditText;
    }

    public IOSElement getUsernameEditText() {
        return usernameEditText;
    }

    public IOSElement getSignUpButton() {
        return signUpButton;
    }

    public IOSElement getThankYouText() {
        return thankYouText;
    }

    // The notifications button ID changes from "OK" to "Allow" from iOS 9 - iOS 10
    public void acceptNotificationsButton() {
        try {
            allowNotificationsButton.click();
        } catch (NoSuchElementException e) {
            OKNotificationsButton.click();
        }
    }

    public TermsAndConditionsScreen newUserLogin(User user) {
        usernameEditText.sendKeys(user.getEmail());
        passwordEditText.sendKeys(defaultPassword);
        loginButton.click();
        return new TermsAndConditionsScreen(driver);
    }

    public SearchScreen defaultLogin() {
        usernameEditText.sendKeys("mfinn@happyfuncorp.com");
        passwordEditText.sendKeys(defaultPassword);
        loginButton.click();
        return new SearchScreen(driver);
    }
}
