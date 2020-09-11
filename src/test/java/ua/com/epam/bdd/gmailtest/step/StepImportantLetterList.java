package ua.com.epam.bdd.gmailtest.step;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import ua.com.epam.bdd.gmailtest.BaseTest;

import java.io.IOException;

import static ua.com.epam.constant.MessageConstant.SUCCESSFUL_DELETION_MESSAGE;
import static ua.com.epam.constant.MessageConstant.SUCCESSFUL_MOVING_MESSAGE;
import static ua.com.epam.constant.URLConstants.BASE_PAGE_URL;

public class StepImportantLetterList extends BaseTest {

    @Before
    public void before() {
        setUp();
    }

    @After
    public void after() throws IOException {
        tearDown();
    }

    @When("^Log in to Gmail account with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void log_in_to_Gmail_account_with_and(String arg1, String arg2)  {
        loginAction.logInToGmailAccount(arg1, arg2);
    }

    @And("^verify gmail main page is open$")
    public void verify_gmail_main_page_is_open() {
        Assert.assertTrue(loginAction.isBasePage(BASE_PAGE_URL));
    }

    @Then("^mark (\\d+) letter as important$")
    public void mark_letter_as_important(int arg1)  {
        importantListAction.waitLetterToBeLoaded(arg1);
        importantListAction.moveNLettersToImportantList(arg1);
    }

    @And("^Verify letter was moved to important list$")
    public void verify_letter_was_moved_to_important_list() {
        Assert.assertTrue(importantListAction.isDisplayedMessage());
        letterAsserter.assertMessage(importantListAction.getMessageText(), SUCCESSFUL_MOVING_MESSAGE);
    }

    @Then("^Open important letters list$")
    public void open_important_letters_list() {
        importantListAction.openImportantLetterList();
    }

    @And("^Mark (\\d+) letters and delete it$")
    public void mark_letters_and_delete_it(int arg1) {
        importantListAction.markNImportantLettersAndDelete(arg1);
    }

    @And("^Verify letter was deleted$")
    public void verify_letter_was_deleted()  {
        Assert.assertTrue(importantListAction.isDisplayedMessage());
        letterAsserter.assertMessage(importantListAction.getMessageText(), SUCCESSFUL_DELETION_MESSAGE);
    }
}
