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
public class FavoriteScreen extends IOSBaseScreen {

    @iOSFindBy(id = "bookmarkCheck")
    private List<IOSElement> bookmarkChecks;

    @iOSFindBy(id = "icon trash lrg")
    private IOSElement trashButton;

    @iOSFindBy(id = "Yes")
    private IOSElement yesButtonDelete;

    public FavoriteScreen(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public List<IOSElement> getBookmarkChecks() {
        return bookmarkChecks;
    }

    public IOSElement getTrashButton() {
        return trashButton;
    }

    public IOSElement getYesButtonDelete() {
        return yesButtonDelete;
    }
}
