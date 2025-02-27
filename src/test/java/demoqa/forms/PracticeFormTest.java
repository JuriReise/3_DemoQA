package demoqa.forms;

import demoqa.core.TestBase;
import demoqa.pages.HomePage;
import demoqa.pages.PracticeFormPage;
import demoqa.pages.SidePanel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

public class PracticeFormTest extends TestBase {
    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver, app.wait).getForms().hideAds();
        new SidePanel(app.driver, app.wait).selectPracticeFormMenu().hideAds();
    }

    @Test
    public void practiceFormPositiveTest() {
        new PracticeFormPage(app.driver, app.wait)
                .enterPersonalData("Beth", "Gibbons", "portishead@gmail.com", "1234567890")
                .selectGender("Female")
                .chooseDate("04", "May", "1965")
                .enterSubjects(new String[]{"English"})
                .chooseHobbies(new String[]{"Sports", "Music"})
                .uploadPicture("C:\\JAVA\\IdeaProjects\\AIT-TR-Java-Course-QA-2024\\QA_Project\\3_DemoQA\\img\\ckydysh.jpg")
                .openUploadedPictureInNewTab("C:\\JAVA\\IdeaProjects\\AIT-TR-Java-Course-QA-2024\\QA_Project\\3_DemoQA\\img\\ckydysh.jpg")
                .enterCurrentAddress("Portishead, Bristol, UK")
                .enterState("NCR")
                .enterCity("Delhi")
                .submitForm()
                .verifySuccessRegistration("Thanks for submitting the form")
                .closeSuccessModal(); // Эффектное завершение
    }

}



