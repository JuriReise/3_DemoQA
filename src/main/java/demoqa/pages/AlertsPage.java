package demoqa.pages;

import demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertsPage extends BasePage {
    public AlertsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(id = "timerAlertButton")
    WebElement timerAlertButton;

    public AlertsPage clickOnAlertWithTimer() {
        click(timerAlertButton);
        wait.until(ExpectedConditions.alertIsPresent()).accept();
        return this;
    }

    @FindBy(id = "confirmButton")
    WebElement confirmButton;

    public AlertsPage clickOnConfirmButton() {
        click(confirmButton);
        return this;
    }

    public AlertsPage confirmResult(String confirm) {
        if (confirm == null) {
            return this;
        }
        switch (confirm.toLowerCase()) {
            case "ok":
                System.out.println("Button OK is pressed.");
                driver.switchTo().alert().accept();
                break;
            case "cancel":
                System.out.println("Button CANCEL is pressed.");
                driver.switchTo().alert().dismiss();
                break;
            default:
                System.out.println("Error name of confirm");
                break;
        }
        return this;
    }

    @FindBy(id = "confirmResult")
    WebElement confirmResult;

    public AlertsPage verifyResult(String result) {
        shouldHaveText(confirmResult, result, 5000);
        return this;
    }

    @FindBy(id = "promtButton")
    WebElement promtButton;

    public AlertsPage sendMessageToAlert(String message) {
        click(promtButton);
        driver.switchTo().alert().sendKeys(message);
        driver.switchTo().alert().accept();
        return this;
    }

    @FindBy(id = "promptResult")
    WebElement promptResult;

    public AlertsPage verifyMessage(String message) {
        shouldHaveText(promptResult, message,5000);
        //assert promptResult.getText().contains(message);
        return this;
    }
}
