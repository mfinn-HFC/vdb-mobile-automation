package screens.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import screens.AndroidBaseScreen;

/**
 * Created by matt-hfc on 1/20/17.
 * A shorthand way of storing part of the Terms & Conditions text to validate that some of it is visible. Validating
 * all text would be a bit time consuming and possibly brittle, but this can be expanded later if necessary.
 */
public class TermsAndConditionsScreen extends AndroidBaseScreen {

    public final String terms = "Welcome to Virtual Diamond Boutique. The VDB.com website, (the “Website”), " +
            "the VDB mobile application and associated services (the “Mobile App”) and other services offered by VDB" +
            " are owned and operated by [Virtual Diamond Boutique, Inc.] (“VDB” “we” or “us”). The Website and " +
            "Mobile App (the “Platform”) provide a means to enable persons or enterprises (“you” or the “Customer”) " +
            "who wish to examine and purchase diamonds and other gemstones (“Services”) to be matched with sellers " +
            "of the same. ";

    @AndroidFindBy(id = "com.vdbapp.android:id/buttonDefaultPositive")
    private AndroidElement acceptButton;

    @AndroidFindBy(id = "com.vdbapp.android:id/content")
    private AndroidElement termsText;

    public TermsAndConditionsScreen(AndroidDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public AndroidElement getAcceptButton()
    {
        return acceptButton;
    }

    public SearchScreen acceptTerms()
    {
        waitForElement(acceptButton);
        acceptButton.click();
        return new SearchScreen(driver);
    }

}
