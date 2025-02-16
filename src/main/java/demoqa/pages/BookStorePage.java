package demoqa.pages;

import demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookStorePage extends BasePage {
    public BookStorePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(id = "searchBox")
    WebElement searchBox;

    public BookStorePage typeInSearchFieldInput(String text) {
        type(searchBox, text);
        return this;
    }

    @FindBy(css = ".mr-2>a")
    WebElement getSearchBox;

    public BookStorePage verifyText(String text) {
        assert getSearchBox.getText().contains(text);
        return this;
    }
}
