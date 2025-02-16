package demoqa.book_store;

import demoqa.core.TestBase;
import demoqa.pages.BookStorePage;
import demoqa.pages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchBooksTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver, app.wait).getBookStore();
    }

    @Test
    public void SearchBooksPositiveTest() {
        new BookStorePage(app.driver, app.wait)
                .typeInSearchFieldInput("Learn")
                .verifyText("Learn");
    }
}

