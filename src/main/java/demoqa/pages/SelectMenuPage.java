package demoqa.pages;

import demoqa.core.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class SelectMenuPage extends BasePage {
    public SelectMenuPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(id = "oldSelectMenu")
    WebElement oldSelectMenu;

    public SelectMenuPage selectOldStyleMenu(String color) {
        Select select = new Select(oldSelectMenu);
        scrollTo(500);
        select.selectByVisibleText(color);
        return this;
    }

    @FindBy(id = "react-select-4-input")
    WebElement inputSelect;

    public SelectMenuPage multiSelect(String[] colors) {
        scrollTo(500);
        for (String color : colors) {
            inputSelect.sendKeys(color, Keys.ENTER);
        }
        inputSelect.sendKeys(Keys.ESCAPE);
        return this;
    }

    @FindBy(className = "css-1rhbuit-multiValue")
    List<WebElement> selectedColorElements;

    public SelectMenuPage verifyColorSelected(String[] colors) {
        for (String color : colors) {
            boolean isColorSelected = false;
            for (WebElement element : selectedColorElements) {
                if(element.getText().equals(color)){
                    System.out.println(color);
                    isColorSelected = true;
                    break;
                }
            }
            Assert.assertTrue(isColorSelected);
        }
        return this;
    }

    @FindBy(id = "cars")
    WebElement cars;

    public SelectMenuPage standardMultiSelectByCars(String[] autos) {
        Select select = new Select(cars);
        scrollTo(500);
        if(select.isMultiple()){
            for(String element : autos){
                select.selectByVisibleText(element);
            }
        }
        return this;
    }

    public SelectMenuPage verifyMultiSelectByCars(String[] expected) {
        Select select = new Select(cars);
        // Опции для выбранных автомобилей
        List<WebElement> selectedOptions = select.getAllSelectedOptions();

        // Массив со списком выбранных автомобилей
        List<String> selectedText = new ArrayList<>();

        //Добавляем в новый массив выбранные автомобили стрингами
        for(WebElement option : selectedOptions){
            selectedText.add(option.getText());
        }

        // Автомобили, которые мы передаём для сравнения
        List<String> expectedText = Arrays.asList(expected);

        // Переменная, которая хранит результат сравнения
        boolean isCarSelected = new HashSet<>(selectedText).containsAll(expectedText);

        Assert.assertTrue(isCarSelected);
        return this;
    }
}
