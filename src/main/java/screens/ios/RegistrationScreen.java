package screens.ios;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.springframework.format.annotation.DateTimeFormat;
import screens.IOSBaseScreen;

import java.util.List;

/**
 * Created by matt-hfc on 12/21/16.
 */
public class RegistrationScreen extends IOSBaseScreen {

    @iOSFindBy(id = "Full Name")
    private IOSElement fullNameField;

    @iOSFindBy(id = "Company")
    private IOSElement companyField;

    @iOSFindBy(id = "Phone Number")
    private IOSElement phoneNumberField;

    @iOSFindBy(id = "Street address")
    private IOSElement streetAddressField;

    @iOSFindBy(id = "-City-")
    private IOSElement cityField;

    @iOSFindBy(id = "-Country-")
    private IOSElement countryField;

    @iOSFindBy(id = "User name (your email)")
    private IOSElement emailField;

    @iOSFindBy(id = "Retype Your Email")
    private IOSElement confirmEmailField;

    // Password fields are nested, get lowest-level element (2nd element, index 1)
    @iOSFindBy(id = "Password")
    private List<IOSElement> passwordField;

    @iOSFindBy(id = "Retype Your Password")
    private List<IOSElement> confirmPasswordField;

    @iOSFindBy(id = "Register")
    private IOSElement registerButton;

    @iOSFindBy(id = "JBT Member?")
    private IOSElement jbtMemberText;

    public RegistrationScreen(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public IOSElement getFullNameField() {
        return fullNameField;
    }

    public IOSElement getCompanyField() {
        return companyField;
    }

    public IOSElement getPhoneNumberField() {
        return phoneNumberField;
    }

    public IOSElement getStreetAddressField() {
        return streetAddressField;
    }

    public IOSElement getCityField() {
        return cityField;
    }

    public IOSElement getCountryField() {
        return countryField;
    }

    public IOSElement getEmailField() {
        return emailField;
    }

    public IOSElement getConfirmEmailField() {
        return confirmEmailField;
    }

    public List<IOSElement> getPasswordField() {
        return passwordField;
    }

    public List<IOSElement> getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public IOSElement getRegisterButton() {
        return registerButton;
    }

    public IOSElement getJbtMemberText() {
        return jbtMemberText;
    }

    public void scrollRegistrationForm() {
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(jbtMemberText).moveTo(phoneNumberField).release().perform();
    }

    // Fill with default values - quick shorthand method
    public void fillRegistrationForm(String email) {
        dismissBugReportNotification();

        fullNameField.sendKeys("Automated Tester");
        companyField.sendKeys("Automated Testing, Inc.");
        phoneNumberField.sendKeys("2128675309");
        streetAddressField.sendKeys("80 John Street");

        cityField.sendKeys("Brooklyn");
        countryField.click();
        countryField.sendKeys("USA");

        driver.hideKeyboard();
        emailField.sendKeys(email);
        confirmEmailField.sendKeys(email);

        RemoteWebElement passwordElement = passwordField.get(passwordField.size() - 1);
        passwordElement.sendKeys("happiness4U");

        passwordElement = confirmPasswordField.get(confirmPasswordField.size() - 1);
        passwordElement.sendKeys("happiness4U");
        registerButton.click();
    }
}
