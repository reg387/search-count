package com.inteliment.searchcount.common;

import com.inteliment.searchcount.controller.PropertyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FileReader {

    private static final Logger logger =
            LoggerFactory.getLogger(FileReader.class);

    @Autowired
    PropertyManager propertyManager;

    private List<String> textLines = new ArrayList<String>();

    @PostConstruct
    public void loadData(){

        this.setTextLines(this.readTextLines());

    }

    public List<String> readTextLines(){

        logger.info("Entering readTextLines method ..");

        List<String> list = new ArrayList<String>();

        try (Stream<String> stream = Files.lines(Paths.get(propertyManager.getFilename()))) {

            list = stream
                    .flatMap((String line) -> Stream.of(line.split("[\\p{Punct}\\s]+")))
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());

        } catch (IOException ioE) {

            logger.error("Error while reading the file..");
            ioE.printStackTrace();

        } catch(Exception ex){

            logger.error("Unexpected error occurred..");
            ex.printStackTrace();
        }

        logger.info("Exiting readTextLines method ..");

        return list;
    }


    public List<String> getTextLines() {
        return textLines;
    }

    public void setTextLines(List<String> textLines) {
        this.textLines = textLines;
    }

}
