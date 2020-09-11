package ua.com.epam.guice;

import com.google.inject.AbstractModule;
import ua.com.epam.asserters.ImportantLetterAsserter;

public class AsserterModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ImportantLetterAsserter.class);
    }
   }
