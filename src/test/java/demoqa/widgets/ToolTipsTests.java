package demoqa.widgets;

import demoqa.core.TestBase;
import demoqa.pages.HomePage;
import demoqa.pages.SidePanel;
import demoqa.pages.ToolTipsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ToolTipsTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver, app.wait).getWidgets().hideAds();
        new SidePanel(app.driver, app.wait).selectToolTipsMenu().hideAds();
    }

    @Test
    public void toolTipsPositiveTest(){
        new ToolTipsPage(app.driver, app.wait)
                .hoverToolTip()
                .verifyToolTipsText("You hovered over the Contrary")
                ;
    }

}
