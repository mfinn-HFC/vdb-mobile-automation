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
public class DiamondViewScreen extends IOSBaseScreen {

    // You can check this list to see if the code is for heart shape by ensure size >= 1
    @iOSFindBy(id = "HS")
    private List<IOSElement> heartShapeCode;

    @iOSFindBy(id = "Back")
    private IOSElement backButton;

    @iOSFindBy(id = "btn radial video idle")
    private IOSElement videoButton;

    @iOSFindBy(id = "Click to Play")
    private IOSElement clickToPlayButton;

    @iOSFindBy(id = "Shape")
    private IOSElement shapeRow;

    @iOSFindBy(id = "OK")
    private IOSElement favoriteAddedOKButton;

    @iOSFindBy(id = "Favorite added!")
    private IOSElement favoriteAddedAlert;

    @iOSFindBy(id = "btn radial favorite idle")
    private IOSElement favoriteButton;


    public DiamondViewScreen(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public List<IOSElement> getHeartShapeCode() {
        return heartShapeCode;
    }

    public IOSElement getBackButton() {
        return backButton;
    }

    public IOSElement getVideoButton() {
        return videoButton;
    }

    public IOSElement getClickToPlayButton() {
        return clickToPlayButton;
    }

    public IOSElement getShapeRow() {
        return shapeRow;
    }

    public IOSElement getFavoriteAddedOKButton() {
        return favoriteAddedOKButton;
    }

    public IOSElement getFavoriteAddedAlert() {
        return favoriteAddedAlert;
    }

    public IOSElement getFavoriteButton() {
        return favoriteButton;
    }
}
