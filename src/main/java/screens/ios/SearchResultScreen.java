package screens.ios;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;
import screens.IOSBaseScreen;

import java.util.List;

/**
 * Created by matt-hfc on 12/21/16.
 */
public class SearchResultScreen extends IOSBaseScreen {

    @iOSFindBy(id = "btn favorite idle")
    private List<IOSElement> favoriteButtonsIdle;

    public SearchResultScreen(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public List<IOSElement> getFavoriteButtonsIdle() {
        return favoriteButtonsIdle;
    }
}
