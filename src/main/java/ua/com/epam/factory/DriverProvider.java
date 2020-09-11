package ua.com.epam.factory;

import org.openqa.selenium.SearchContext;

@FunctionalInterface
public interface DriverProvider {
    SearchContext get();
}

