//✅ Реализовать обработку ошибок и проверку входных данных
//🔹 Добавить проверки if и/или try-catch для предохранения кода от сбоев.
//🔹 Метод должен уметь роверять входные данные перед выполнением теста на валидность.
//🔹 Перехватывать несколько исключений (NoSuchElementException, IllegalArgumentException).
//
//📌 Шаги:
//1️⃣ Проверять, существует ли элемент, перед тем как с ним взаимодействовать.
//2️⃣ Выбрасывать ошибку с пояснением, если передано некорректное значение.
//3️⃣ Обрабатывать несколько Exception, выводя осмысленные сообщения.

package demoqa.pages;

import demoqa.core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class PracticeFormPage extends BasePage {
    public PracticeFormPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    @FindBy(id = "firstName")
    WebElement firstName;
    @FindBy(id = "lastName")
    WebElement lastName;
    @FindBy(id = "userEmail")
    WebElement userEmail;
    @FindBy(id = "userNumber")
    WebElement userNumber;

    public PracticeFormPage enterPersonalData(String name, String surName, String email, String number) {
        type(firstName, name);
        type(lastName, surName);
        type(userEmail, email);
        type(userNumber, number);
        System.out.printf("✅ Personal data: [%s], [%s], [%s], [%s]%n", name, surName, email, number);
        return this;
    }

    public PracticeFormPage selectGender(String gender) {

        try {
            String xpathGender = "//label[.='" + gender + "']";
            WebElement genderLocator = driver.findElement(By.xpath(xpathGender));
            click(genderLocator);
            System.out.printf("✅ Gender: [%s]%n", gender);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("⛔ Gender element not found: [" + gender + "]. " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("❌ Error selecting gender: [" + gender + "]. " + e);
        }
        return this;
    }

    @FindBy(id = "dateOfBirthInput")
    WebElement dateOfBirthInput;

    public PracticeFormPage chooseDateAsString(String date) {
        //type(dateOfBirthInput, date);
        click(dateOfBirthInput);

        if (System.getProperty("os.name").contains("Mac")) {
            dateOfBirthInput.sendKeys(Keys.COMMAND, "a");
        } else {
            dateOfBirthInput.sendKeys(Keys.CONTROL, "a");
        }
        dateOfBirthInput.sendKeys(date);
        dateOfBirthInput.sendKeys(Keys.ENTER);
        System.out.printf("✅ Date: [%s]%n", date);
        return this;
    }

    @FindBy(id = "subjectsInput")
    WebElement subjectsInput;

    public PracticeFormPage enterSubjects(String[] subjects) {

        for (String subject : subjects) {
            type(subjectsInput, subject);
            subjectsInput.sendKeys(Keys.ENTER);
            System.out.printf("✅ Subject: [%s]%n", subject);
        }
        return this;
    }

    public PracticeFormPage chooseHobbies(String[] hobbies) {
        for (String hobby : hobbies) {
            By hobbyLocator = By.xpath("//label[.='" + hobby + "']");
            WebElement element = driver.findElement(hobbyLocator);
            click(element);
            System.out.printf("✅ Hobby: [%s]%n", hobby);
        }
        return this;
    }
// ✅ Добавить проверку успешной загрузки файла в метод uploadPicture()
//🔹 Когда файл загружен, рядом с кнопкой появляется имя файла.
//🔹 Нужно реализовать проверку, что отображается именно тот файл, который был загружен.
//
//📌 Шаги:
//1️⃣ Отправить путь к файлу в uploadPicture.sendKeys(imgPath).
//2️⃣ Добавить метод getAttribute("value") для получения загруженного файла.
//3️⃣ Проверить, что имя загруженного файла совпадает с переданным.
//
//💡 Подсказка:
//Используйте shouldHaveText() для проверки имени файла.

    @FindBy(id = "uploadPicture")
    WebElement uploadPicture;

    public PracticeFormPage uploadPicture(String imgPath) {
        // Проверка на null
        if (imgPath == null) {
            throw new IllegalArgumentException("⛔ Image path can't be null.");
        }

        // Проверка на пустую строку
        if (imgPath.isEmpty()) {
            throw new IllegalArgumentException("⛔ Image path can't be empty.");
        }

        // Проверка, существует ли файл
        File file = new File(imgPath);
        if (!file.exists()) {
            throw new IllegalArgumentException("⛔ File not found at path: " + imgPath);
        }

        try {
            uploadPicture.sendKeys(imgPath);

            // Проверка имени загруженного файла
            String uploadedFileName = uploadPicture.getAttribute("value");
            uploadedFileName = uploadedFileName.substring(uploadedFileName.lastIndexOf("\\") + 1);

            if (!uploadedFileName.equals(imgPath.substring(imgPath.lastIndexOf("\\") + 1))) {
                throw new IllegalArgumentException("⛔ Uploaded file name does not match: Expected ["
                        + imgPath.substring(imgPath.lastIndexOf("\\") + 1)
                        + "], but found [" + uploadedFileName + "]");
            }
            System.out.printf("✅ Image uploaded successfully: [%s]%n", uploadedFileName);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("⛔ Upload element not found: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("❌ Error uploading image: " + e);
        }

        return this;
    }
// ✅ Реализовать выбор даты через Select
//🔹 Создать метод .chooseDate("04", "May", "1965").
//🔹 Использовать Select для выбора месяца и года.
//🔹 Для выбора дня месяца сформировать параметризованный локатор и кликать по нему.
//
//📌 Шаги:
//1️⃣ Выбрать год через Select.
//2️⃣ Выбрать месяц через Select.
//3️⃣ Выбрать день через клик по динамическому XPath //div[contains(@class,'year') and contains(@class,'month') and contains(@class,'day')]. ⚠️ Это только пример
//
//💡 Подсказка:
//🔹 Параметризируйте локатор таким образом, чтобы не была выбрана дата из соседнего месяца.

    @FindBy(className = "react-datepicker__month-select")
    WebElement monthSelect;
    @FindBy(className = "react-datepicker__year-select")
    WebElement yearSelect;

    public PracticeFormPage chooseDate(String day, String month, String year) {
        // Проверка дней
        if (day == null || day.isEmpty() || !day.matches("\\d{1,2}")) {
            throw new IllegalArgumentException("⛔ Invalid day: " + day);
        }
        int dayInt = Integer.parseInt(day);
        if (dayInt < 1 || dayInt > 31) {
            throw new IllegalArgumentException("⛔ Day out of range: " + day);
        }

        // Проверка месяцев
        List<String> validMonths = Arrays.asList("January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December");
        if (month == null || month.isEmpty() || !validMonths.contains(month)) {
            throw new IllegalArgumentException("⛔ Invalid month: " + month);
        }

        // Проверка года
        if (year == null || !year.matches("\\d{4}")) {
            throw new IllegalArgumentException("⛔ Invalid year: " + year);
        }
        int yearInt = Integer.parseInt(year);
        int currentYear = LocalDate.now().getYear();
        if (yearInt < 1900 || yearInt > currentYear) {
            throw new IllegalArgumentException("⛔ Year out of range: " + year);
        }

        try {
            click(dateOfBirthInput);

            // Выбираем месяц
            WebElement monthDropdown = driver.findElement(By.className("react-datepicker__month-select"));
            monthDropdown.click();
            WebElement monthOption = driver.findElement(By.xpath("//select[@class='react-datepicker__month-select']/option[text()='" + month + "']"));
            monthOption.click();

            // Выбираем год
            WebElement yearDropdown = driver.findElement(By.className("react-datepicker__year-select"));
            yearDropdown.click();
            WebElement yearOption = driver.findElement(By.xpath("//select[@class='react-datepicker__year-select']/option[text()='" + year + "']"));
            yearOption.click();

            // Локатор дня
            String dayLocator = String.format("//div[contains(@class,'react-datepicker__day--0%s') and not(contains(@class, 'outside-month'))]", day);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement dayElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dayLocator)));
            dayElement.click();

            System.out.printf("✅ Date selected: [%s %s %s]%n", day, month, year);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("⛔ Date element not found: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("❌ Error selecting date: " + e);
        }

        return this;
    }

    public PracticeFormPage openUploadedPictureInNewTab(String imgPath) {

        String filePath = "file:///" + imgPath.replace("\\", "/");

        ((JavascriptExecutor) driver).executeScript("window.open('" + filePath + "', '_blank');");

        System.out.printf("🎨 Image displayed in new browser tab: [%s]%n", filePath);

        return this;
    }

    // Ввод адреса
    @FindBy(id = "currentAddress")
    WebElement currentAddress;

    public PracticeFormPage enterCurrentAddress(String address) {
        // Проверка на null и пустоту
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("⛔ Address can't be null or empty.");
        }

        currentAddress.clear();
        currentAddress.sendKeys(address);
        System.out.printf("✅ Current Address: [%s]%n", address);
        return this;
    }

    // Выбор штата
    @FindBy(id = "react-select-3-input")
    WebElement stateInput;

    public PracticeFormPage enterState(String state) {
        // Проверка на null и пустоту
        if (state == null || state.isEmpty()) {
            throw new IllegalArgumentException("⛔ State can't be null or empty.");
        }

        stateInput.sendKeys(state);
        stateInput.sendKeys(Keys.ENTER);
        System.out.printf("✅ State selected: [%s]%n", state);
        return this;
    }

    // Выбор города
    @FindBy(id = "react-select-4-input")
    WebElement cityInput;

    public PracticeFormPage enterCity(String city) {
        // Проверка на null и пустоту
        if (city == null || city.isEmpty()) {
            throw new IllegalArgumentException("⛔ City can't be null or empty.");
        }

        cityInput.sendKeys(city);
        cityInput.sendKeys(Keys.ENTER);
        System.out.printf("✅ City selected: [%s]%n", city);
        return this;
    }

    // Кнопку Submit
    @FindBy(id = "submit")
    WebElement submitButton;

    public PracticeFormPage submitForm() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
        System.out.println("🚀 Form submitted");
        return this;
    }
    //@FindBy(id = "submit")
    //WebElement submitButton;
    //
    //public PracticeFormPage submitForm() {
    //    click(submitButton);
    //    return this;
    //}

    // Проверка успешной регистрации
    public PracticeFormPage verifySuccessRegistration(String message) {
        WebElement successMessage = driver.findElement(By.xpath("//div[contains(@class,'modal-title') and text()='" + message + "']"));
        if (successMessage.isDisplayed()) {
            System.out.printf("🎉 Success message displayed: [%s]%n", message);
        } else {
            throw new AssertionError("⛔ Success message not displayed");
        }
        return this;
    }
    // закрытие формы в модальном окне
    public PracticeFormPage closeSuccessModal() {
        WebElement closeButton = driver.findElement(By.id("closeLargeModal"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeButton);
        System.out.println("✅ Success modal closed");

        // Проверка, что модальное окно закрылось
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("closeLargeModal")));

        // Выводим Alert с сообщением "Проект закончен!"
        ((JavascriptExecutor) driver).executeScript("alert('🎉 Проект закончен!');");
        System.out.println("🎉 Проект закончен!");
        return this;
    }


}

