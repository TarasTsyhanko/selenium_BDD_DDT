package ua.com.epam.guice;

import com.google.inject.AbstractModule;

import ua.com.epam.ui.pages.GmailBasePage;
import ua.com.epam.ui.pages.GmailImportantLettersPage;
import ua.com.epam.ui.pages.GmailLoginPage;

public class PageModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GmailLoginPage.class);
        bind(GmailImportantLettersPage.class);
        bind(GmailBasePage.class);
    }
}
