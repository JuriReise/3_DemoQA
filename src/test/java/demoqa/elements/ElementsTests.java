package demoqa.elements;

import demoqa.core.TestBase;
import demoqa.pages.ButtonsPage;
import demoqa.pages.HomePage;
import demoqa.pages.SidePanel;
import demoqa.utils.RetryAnalyser;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ElementsTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver, app.wait).getElements().hideAds();
        new SidePanel(app.driver, app.wait).selectButtonsMenu().hideAds();
    }

    @Test
    public void doubleClickButtonsPositiveTest(){
        new ButtonsPage(app.driver, app.wait)
                .doubleClick()
                .verifyDoubleClickMessage("You have done a double click")
                ;
    }

    @Test(retryAnalyzer = RetryAnalyser.class)
    public void rightClickButtonsPositiveTest(){
        new ButtonsPage(app.driver, app.wait)
                .rightClickButton()
                .verifyRightClickMessage("You have done a right click")
        ;
    }
}
