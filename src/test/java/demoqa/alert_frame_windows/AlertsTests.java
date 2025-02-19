package demoqa.alert_frame_windows;

import demoqa.core.TestBase;
import demoqa.pages.AlertsPage;
import demoqa.pages.HomePage;
import demoqa.pages.SidePanel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AlertsTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver, app.wait).getAlertFrameWindows().hideAds();
        new SidePanel(app.driver, app.wait).selectAlerts().hideAds();
    }

    @Test
    public void waitAlertTest() {
        new AlertsPage(app.driver, app.wait).clickOnAlertWithTimer();
    }

    @Test
    public void alertWithSelectedTextPositiveTest(){
        new AlertsPage(app.driver, app.wait)
                .clickOnConfirmButton()
                .confirmResult("Cancel")
                .verifyResult("Cancel")
                ;
    }

    @Test
    public void sendMessageToAlertPositiveTest(){
        new AlertsPage(app.driver, app.wait)
                .sendMessageToAlert("123")
                .verifyMessage("123")
                ;
    }
}
