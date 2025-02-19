package demoqa.alert_frame_windows;

import demoqa.core.TestBase;
import demoqa.pages.BrowserWindowsPage;
import demoqa.pages.HomePage;
import demoqa.pages.SidePanel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BrowserWindowsTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver, app.wait).getAlertFrameWindows().hideAds();
        new SidePanel(app.driver, app.wait).selectBrowserWindows().hideAds();
    }

    @Test
    public void newTabPositiveTest(){
        new BrowserWindowsPage(app.driver, app.wait)
                .switchToNewTab(1)
                .verifyTabTitle("This is a sample page")
                ;
    }
}
