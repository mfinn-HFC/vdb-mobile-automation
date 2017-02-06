package screens.ios;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;
import screens.IOSBaseScreen;

import java.util.Arrays;
import java.util.List;

/**
 * Created by matt-hfc on 1/20/17.
 * A shorthand way of storing part of the Terms & Conditions text to validate that some of it is visible. Validating
 * all text would be a bit time consuming and possibly brittle, but this can be expanded later if necessary.
 */
public class TermsAndConditionsScreen extends IOSBaseScreen {

    public final List<String> terms = Arrays.asList(
            "Welcome to Virtual Diamond Boutique. The VDB.com website, (the “Website”), the VDB mobile application and associated services (the “Mobile App”) and other services offered by VDB are owned and operated by [Virtual Diamond Boutique, Inc.] (“VDB” “we” or “us”). The Website and Mobile App (the “Platform”) provide a means to enable persons or enterprises (“you” or the “Customer”) who wish to examine and purchase diamonds and other gemstones (“Services”) to be matched with sellers of the same.",
            "THE FOLLOWING AGREEMENT DESCRIBES THE TERMS OF SERVICE UPON WHICH VDB OFFERS YOU ACCESS TO AND USAGE OF THE PLATFORM AND SERVICES. ",
            "These terms of service (these “Terms”) constitute a legal agreement between you and VDB. In order to use the Platform and Services you must agree to these Terms. By using the Platform or receiving any Services, including downloading and installing the Mobile App, you hereby expressly acknowledge and agree to be bound by these Terms, and any future amendments and additions to these Terms as provided herein."
    );

    @iOSFindBy(id = "Accept These Terms")
    private IOSElement acceptButton;

    public TermsAndConditionsScreen(IOSDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public IOSElement getAcceptButton()
    {
        return acceptButton;
    }

    public boolean validatePartialTermsAndConditionsText()
    {
        boolean result = false;
        for(String s : terms)
        {
            if(terms.contains(s)) result = true;
            else return false;
        }
        return result;
    }

    public SearchScreen acceptTerms()
    {
        acceptButton.click();
        return new SearchScreen(driver);
    }

}
