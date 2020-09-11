package ua.com.epam.decorator.elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ua.com.epam.factory.DriverContainer;
import ua.com.epam.utils.Wait;
import ua.com.epam.decorator.WrapperFactory;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Log4j2
public class PageElementCollectionImpl<T> implements PageElementCollection<T> {
    private List<T> webElements;
    private final By locator;
    private final Class<T> tClass;

    public PageElementCollectionImpl(By locator, Class<T> tClass) {
        this.locator = locator;
        this.tClass = tClass;
    }

    private List<T> getElements() {
        List<WebElement> list = DriverContainer.getDriver().findElements(locator);
        addList(list);
        return webElements;
    }

    public void addList(List<WebElement> elementList) {
        webElements = new ArrayList<>();

        for (int i = 0;i<elementList.size();i++){
            By singleElement = By.xpath("("+locator.toString().substring(10)+")["+(i+1)+"]");
            webElements.add(WrapperFactory.createInstance(tClass, elementList.get(i), singleElement));
        }
      //  elementList.forEach(element -> webElements.add(WrapperFactory.createInstance(tClass, element, locator)));
    }

    @Override
    public <S> List<S> map(Function<? super T, ? extends S> mapper) {
        return getElements().stream().map(mapper).collect(Collectors.toList());
    }

    @Override
    public T get(int index) {
        return getElements().get(index);
    }

    @Override
    public List<T> getWithLimit(int limit) {
        return getElements().stream().limit(limit).collect(Collectors.toList());
    }

    @Override
    public PageElementCollection<T> waitUntilVisible() {
        addList(Wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)));
        return this;
    }

    @Override
    public PageElementCollection<T> waitUntilPresent() {
        addList(Wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator)));
        return this;
    }

    @Override
    public int size() {
        return getElements().size();
    }

    @Override
    public boolean isEmpty() {
        return getElements().isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return getElements().contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return new PageElementIterator();
    }

    private class PageElementIterator implements Iterator<T> {
        Iterator<T> iterator = getElements().iterator();

        @Override
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override
        public T next() {
            return this.iterator.next();
        }

        @Override
        public void remove() {
            this.iterator.remove();
        }
    }

    @Override
    public Object[] toArray() {
        return getElements().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return getElements().toArray(a);
    }

    @Override
    public boolean add(T element) {
        return webElements.add(element);
    }

    @Override
    public boolean remove(Object o) {
        return getElements().remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return getElements().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return getElements().addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return getElements().removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return getElements().retainAll(c);
    }

    @Override
    public void clear() {
        getElements().clear();
    }
}
