package screens.android;

import api.User;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import screens.AndroidBaseScreen;

/**
 * Created by matt-hfc on 12/21/16.
 */
public class LoginScreen extends AndroidBaseScreen {

    private final String defaultPassword = "happiness4U";

    // Taken from 'resource id' value in Appium inspector
    @AndroidFindBy(id = "com.vdbapp.android:id/sign_up")
    private AndroidElement signUpButton;

    public final String usernameEditTextID = "com.vdbapp.android:id/username_edit_text";
    @AndroidFindBy(id = usernameEditTextID)
    private AndroidElement usernameEditText;

    @AndroidFindBy(id = "com.vdbapp.android:id/password_edit_text")
    private AndroidElement passwordEditText;

    @AndroidFindBy(id = "com.vdbapp.android:id/save_checkbox")
    private AndroidElement saveCredentialsCheckbox;

    @AndroidFindBy(id = "com.vdbapp.android:id/login_button")
    private AndroidElement loginButton;

    @AndroidFindBy(id = "com.vdbapp.android:id/forgot_pwd_button")
    private AndroidElement forgotPasswordButton;

    public LoginScreen(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public AndroidElement getSignUpButton() {
        return signUpButton;
    }

    public AndroidElement getUsernameEditText() {
        return usernameEditText;
    }

    public AndroidElement getPasswordEditText() {
        return passwordEditText;
    }

    public AndroidElement getSaveCredentialsCheckbox() {
        return saveCredentialsCheckbox;
    }

    public AndroidElement getLoginButton() {
        return loginButton;
    }

    public AndroidElement getForgotPasswordButton() {
        return forgotPasswordButton;
    }

    public void defaultLogin() {
        usernameEditText.sendKeys("mfinn@happyfuncorp.com");
        hideKeyboard();
        passwordEditText.sendKeys(defaultPassword);
        hideKeyboard();
        loginButton.click();
    }

    public TermsAndConditionsScreen loginNewUser(User user)
    {
        usernameEditText.sendKeys(user.getEmail());
        hideKeyboard();
        passwordEditText.sendKeys(defaultPassword);
        hideKeyboard();
        loginButton.click();
        return new TermsAndConditionsScreen(driver);
    }
}
