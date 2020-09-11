package ua.com.epam.guice;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;

import com.google.inject.AbstractModule;
import lombok.extern.log4j.Log4j2;
import ua.com.epam.api.service.GmailService;
import ua.com.epam.api.service.GmailServiceImpl;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Log4j2
public class GmailApiModule extends AbstractModule {

    @Override
    protected void configure() {
        try {
            bind(GmailService.class).toInstance(new GmailServiceImpl(GoogleNetHttpTransport.newTrustedTransport()));
        } catch (GeneralSecurityException | IOException e) {
            log.error(e.getStackTrace());
        }
    }
}
