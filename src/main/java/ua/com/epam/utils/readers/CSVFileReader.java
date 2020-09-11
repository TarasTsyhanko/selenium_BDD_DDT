package ua.com.epam.utils.readers;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import ua.com.epam.config.ConfigProperties;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class CSVFileReader {


    public static Object[][] readUsers() {
        Object[][] date = new Object[5][3];
        try (
                Reader reader = Files.newBufferedReader(Paths.get(ConfigProperties.getUserCsvFilePath()));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim());
        ) {
            int i = 0;
            for (CSVRecord csvRecord : csvParser) {
                date[i][0] = csvRecord.get(0);
                date[i][1] = csvRecord.get(1);
                date[i][2] = csvRecord.get(2);
                i++;
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return date;
    }

}
