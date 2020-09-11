package ua.com.epam.decorator;

import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import ua.com.epam.factory.DriverProvider;

import java.lang.reflect.Field;


public class PageElementLocatorFactoryImpl implements ElementLocatorFactory {
  private final DriverProvider provider;

    public PageElementLocatorFactoryImpl(DriverProvider provider) {
        this.provider = provider;
    }

    @Override
    public ElementLocator createLocator(Field field) {
        return new PageElementLocatorImpl(this.provider,field);
    }
}
