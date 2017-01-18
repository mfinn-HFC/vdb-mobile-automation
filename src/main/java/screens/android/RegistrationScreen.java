package screens.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import screens.AndroidBaseScreen;

/**
 * Created by matt-hfc on 12/21/16.
 */
public class RegistrationScreen extends AndroidBaseScreen {

    // Taken from 'resource id' value in Appium inspector
    @AndroidFindBy(id = "com.vdbapp.android:id/form_full_name")
    private AndroidElement fullNameField;

    @AndroidFindBy(id = "com.vdbapp.android:id/form_company")
    private AndroidElement companyNameField;

    @AndroidFindBy(id = "com.vdbapp.android:id/form_phone")
    private AndroidElement phoneNumberField;

    @AndroidFindBy(id = "com.vdbapp.android:id/form_street")
    private AndroidElement streetAddressField;

    @AndroidFindBy(id = "com.vdbapp.android:id/form_city")
    private AndroidElement cityField;

    @AndroidFindBy(id = "com.vdbapp.android:id/form_country")
    private AndroidElement countryField;

    @AndroidFindBy(id = "com.vdbapp.android:id/form_username")
    private AndroidElement emailField;

    @AndroidFindBy(id = "com.vdbapp.android:id/form_retype_username")
    private AndroidElement emailConfirmField;

    @AndroidFindBy(id = "com.vdbapp.android:id/form_password")
    private AndroidElement passwordField;

    @AndroidFindBy(id = "com.vdbapp.android:id/form_retype_password")
    private AndroidElement passwordConfirmField;

    @AndroidFindBy(id = "com.vdbapp.android:id/sign_up")
    private AndroidElement signUpButton;

    public RegistrationScreen(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public AndroidElement getSignUpButton() {
        return signUpButton;
    }

    public AndroidElement getFullNameField() {
        return fullNameField;
    }

    public AndroidElement getCompanyNameField() {
        return companyNameField;
    }

    public AndroidElement getPhoneNumberField() {
        return phoneNumberField;
    }

    public AndroidElement getStreetAddressField() {
        return streetAddressField;
    }

    public AndroidElement getCityField() {
        return cityField;
    }

    public AndroidElement getCountryField() {
        return countryField;
    }

    public AndroidElement getEmailField() {
        return emailField;
    }

    public AndroidElement getEmailConfirmField() {
        return emailConfirmField;
    }

    public AndroidElement getPasswordField() {
        return passwordField;
    }

    public AndroidElement getPasswordConfirmField() {
        return passwordConfirmField;
    }

    public void fillField(AndroidElement e, String s) {
        try
        {
            driver.hideKeyboard();
        } catch (Exception ex) { /** If keyboard isn't visible **/ }
        e.sendKeys(s);
    }

    // Quick android.registration if you don't want to feed custom data
    public void fillRegistrationForm(String email) {
        fillField(fullNameField, "Test User");
        fillField(companyNameField, "Test Co. Incopo");
        fillField(phoneNumberField, "8675309");
        fillField(streetAddressField, "80 John Street");
        fillField(cityField, "Testington");
        fillField(countryField, "United Tests of America");
        fillField(emailField, email);
        fillField(emailConfirmField, email);
        fillField(passwordField, "happiness4U");
        fillField(passwordConfirmField, "happiness4U");
    }
}