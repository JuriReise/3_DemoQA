package demoqa.pages;

import demoqa.core.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutoCompletePage extends BasePage {
    public AutoCompletePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(id = "autoCompleteMultipleInput")
    WebElement autoCompleteMultipleInput;

    public AutoCompletePage autoComplete(String letter) {
        //type(autoCompleteMultipleInput, letter);
        autoCompleteMultipleInput.sendKeys(letter);
        autoCompleteMultipleInput.sendKeys(Keys.ENTER);
        return this;
    }

    @FindBy(id = "autoCompleteMultipleContainer")
    WebElement autoCompleteMultipleContainer;

    public AutoCompletePage verifyAutoComplete(String color) {
        shouldHaveText(autoCompleteMultipleContainer, color, 5000);
        return this;
    }

    public AutoCompletePage autoCompleteArray(String[] letters) {
        for (String letter : letters) {
//            autoCompleteMultipleInput.sendKeys(letter);
//            autoCompleteMultipleInput.sendKeys(Keys.ENTER);
            autoComplete(letter);
        }
        return this;
    }

    public AutoCompletePage verifyAutoCompleteArray(String[] colors) {
        for (String color : colors) {
            verifyAutoComplete(color);
            //shouldHaveText(autoCompleteMultipleContainer, color, 5000);
        }
        return this;
    }
}
