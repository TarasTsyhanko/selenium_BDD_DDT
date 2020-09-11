package ua.com.epam.decorator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.Annotations;

import java.lang.reflect.Field;
import java.util.Optional;

public class WrapperFactory {

    public static<T> T createInstance(Class<T> clazz, WebElement element, Field field) {
        By by  = null;
        if(Optional.ofNullable(field).isPresent()){
            by =new Annotations(field).buildBy();
        }
        try {
            return   clazz.getConstructor(WebElement.class, By.class).newInstance(element, by);
        } catch (Exception e) {
            throw new AssertionError("PageElement can't be represented as " + clazz);
        }
    }

    public static<T> T createInstance(Class<T> clazz, WebElement element, By locator) {
        try {
            return   clazz.getConstructor(WebElement.class, By.class).newInstance(element, locator);
        } catch (Exception e) {
            throw new AssertionError("PageElement can't be represented as " + clazz);
        }
    }
}