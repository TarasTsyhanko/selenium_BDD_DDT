package ua.com.epam.decorator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import ua.com.epam.decorator.elements.PageElement;
import ua.com.epam.decorator.elements.PageElementCollection;
import ua.com.epam.decorator.elements.PageElementCollectionImpl;


public class LocatingCustomElementListHandler implements InvocationHandler {
    private final ElementLocator locator;
    private final Class<PageElement> clazz;
    private final Field field;

    public LocatingCustomElementListHandler(ElementLocator locator, Class<PageElement> clazz, Field field) {
        this.locator = locator;
        this.clazz = clazz;
        this.field = field;
    }

    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
        By by = (new Annotations(field)).buildBy();

        PageElementCollection<PageElement> customs = new PageElementCollectionImpl<>(by, clazz);
        customs.addList(locator.findElements());
        try {
            return method.invoke(customs, objects);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw e.getCause();
        }
    }
}