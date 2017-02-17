package screens.ios;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;
import screens.IOSBaseScreen;

/**
 * Created by matt-hfc on 12/21/16.
 */
public class SearchScreen extends IOSBaseScreen {

    // Grab the coords of these since the ID changes when you tap into them
    public final String fromValueInputID = "$0";
    @iOSFindBy(id = fromValueInputID)
    private IOSElement fromValueInput;

    @iOSFindBy(id = "Max")
    private IOSElement toValueInput;

    @iOSFindBy(id = "to")
    private IOSElement toText;

    @iOSFindBy(id = "Shape")
    private IOSElement shapeWheelButton;

    @iOSFindBy(id = "Heart")
    private IOSElement heartShapeSelectionButton;

    @iOSFindBy(id = "icon hamburger")
    private IOSElement hamburgerButton;

    @iOSFindBy(id = "Lab")
    private IOSElement labButton;

    @iOSFindBy(id = "Type cert number")
    private IOSElement certNumberField;

    @iOSFindBy(id = "Color")
    private IOSElement colorButton;

    @iOSFindBy(id = "line down")
    private IOSElement lineDownButton;

    @iOSFindBy(id = "More")
    private IOSElement moreButton;

    @iOSFindBy(id = "Search")
    private IOSElement searchTextBar; // For Location & Supplier

    @iOSFindBy(id = "Israel")
    private IOSElement israelLocationSelection;

    @iOSFindBy(id = "DONE")
    private IOSElement locationSupplierDoneButton;

    @iOSFindBy(id = "SUPPLIER")
    private IOSElement supplierTabButton;

    @iOSFindBy(id = "moved to row 1 of 1")
    private IOSElement parentRowSupplierSearchResult; // Single result

    @iOSFindBy(id = "Pol/ Sym")
    private IOSElement polishSymmetrySearchButton;

    @iOSFindBy(id = "Polish")
    private IOSElement polishTabButton;

    @iOSFindBy(id = "Symmetry")
    private IOSElement symmetryTabButton;

    @iOSFindBy(id = "Cut Grade")
    private IOSElement cutGradeTabButton;

    @iOSFindBy(id = "lab search")
    private IOSElement labSearchButton; // Search button in Lab / cert screen text field

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

    public IOSElement getToText() {
        return toText;
    }

    public IOSElement getShapeWheelButton() {
        return shapeWheelButton;
    }

    public IOSElement getHeartShapeSelectionButton() {
        return heartShapeSelectionButton;
    }

    public IOSElement getHamburgerButton() {
        return hamburgerButton;
    }

    public IOSElement getLabButton() {
        return labButton;
    }

    public IOSElement getCertNumberField() {
        return certNumberField;
    }

    public IOSElement getColorButton() {
        return colorButton;
    }

    public IOSElement getLineDownButton() {
        return lineDownButton;
    }

    public IOSElement getMoreButton() {
        return moreButton;
    }

    public IOSElement getSearchTextBar() {
        return searchTextBar;
    }

    public IOSElement getIsraelLocationSelection() {
        return israelLocationSelection;
    }

    public IOSElement getLocationSupplierDoneButton() {
        return locationSupplierDoneButton;
    }

    public IOSElement getSupplierTabButton() {
        return supplierTabButton;
    }

    public IOSElement getParentRowSupplierSearchResult() {
        return parentRowSupplierSearchResult;
    }

    public IOSElement getPolishSymmetrySearchButton() {
        return polishSymmetrySearchButton;
    }

    public IOSElement getPolishTabButton() {
        return polishTabButton;
    }

    public IOSElement getSymmetryTabButton() {
        return symmetryTabButton;
    }

    public IOSElement getCutGradeTabButton() {
        return cutGradeTabButton;
    }

    public IOSElement getLabSearchButton() {
        return labSearchButton;
    }

    public SearchResultScreen tapSearchButton()
    {
        int height = driver.manage().window().getSize().getHeight();
        int centerX = driver.manage().window().getSize().getWidth() / 2;
        int fraction = height - (height / 15);

        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(centerX, fraction).release().perform();

        return new SearchResultScreen(driver);
    }
}
