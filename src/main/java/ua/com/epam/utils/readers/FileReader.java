package ua.com.epam.utils.readers;

import lombok.extern.log4j.Log4j2;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Log4j2
public class FileReader {
    public static <T> List<T> readListFile(String path, Class<T> tClass) {
        List<T> list = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, tClass);
            list = mapper.readValue(new File(path), listType);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return list;
    }

    public static <T> T readFile(String path, Class<T> tClass) {
        T object =null;
        try {
            object = new ObjectMapper().readValue(new File(path), tClass);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return object;
    }
}
