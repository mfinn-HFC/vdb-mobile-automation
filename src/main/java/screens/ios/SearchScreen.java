package screens.ios;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;
import screens.AndroidBaseScreen;
import screens.IOSBaseScreen;

/**
 * Created by matt-hfc on 12/21/16.
 */
public class SearchScreen extends IOSBaseScreen {

    // Grab the coords of these since the ID changes when you tap into them
    @iOSFindBy(id = "$0")
    private IOSElement fromValueInput;

    @iOSFindBy(id = "Max")
    private IOSElement toValueInput;

    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[11]")
    private IOSElement searchButton;

    @iOSFindBy(id = "to")
    private IOSElement toText;

    public SearchScreen(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public IOSElement getFromValueInput() {
        return fromValueInput;
    }

    public IOSElement getToValueInput() {
        return toValueInput;
    }

    public IOSElement getSearchButton() {
        return searchButton;
    }

    public IOSElement getToText() {
        return toText;
    }

    public void tapSearchButton() {
        int height = driver.manage().window().getSize().getHeight();
        int centerX = driver.manage().window().getSize().getWidth() / 2;
        int fraction = height - (height / 15);
        driver.tap(1, centerX, fraction, 1);
    }
}
