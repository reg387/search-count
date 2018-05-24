package com.inteliment.searchcount.service;

import com.inteliment.searchcount.common.FileReader;
import com.inteliment.searchcount.exception.SearchCountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchCountServiceImpl implements SearchCountService{


    private static final Logger logger =
            LoggerFactory.getLogger(SearchCountServiceImpl.class);

    @Autowired
    FileReader fileReader;

    @Override
    public List<Map> getCountText(List<String> searchWords) throws SearchCountException{

        logger.info("Entering getCountText method ..");

        List<String> textLines = fileReader.getTextLines();

        //Check if content is present
        if(textLines.size() == 0) {

            throw new SearchCountException("File contents not found");
        }

        List<Map> searchWordList = new ArrayList<Map>();

        for(String searchWord: searchWords){

            HashMap<String, Long> searchWordMap = new HashMap<String, Long>();

            //Count the occurrences for each word
            long occurrences = textLines
                    .stream()
                    .filter(word -> word.equals(searchWord.toLowerCase()))
                    .count();

            searchWordMap.put(searchWord, occurrences);

            searchWordList.add(searchWordMap);
        }


        logger.info("Exiting getCountText method ..");

        return searchWordList;
    }

    @Override
    public String getTopCountText(int topTextCount) throws SearchCountException {

        logger.info("Entering getTopCountText method ..");

        List<String> textLines = fileReader.getTextLines();

        //Check if content is present
        if(textLines.size() == 0) {

            throw new SearchCountException("File contents not found");
        }


        Map<String, Long> wordMap = new HashMap<String, Long>();

        wordMap = textLines.stream()
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));

        List<Map.Entry<String, Long>> searchResultList = wordMap.entrySet().stream()
                .sorted((e1, e2) -> e1.getValue() == e2.getValue() ? e1.getKey().compareTo(e2.getKey()) : e2.getValue().intValue() - e1.getValue().intValue())
                .limit(topTextCount)
                .collect(Collectors.toList());

        StringBuilder searchResult = new StringBuilder();

        //Generate the result into string object
        for(Map.Entry<String,Long> result : searchResultList) {
            searchResult.append(result.getKey());
            searchResult.append("|");
            searchResult.append(result.getValue());
            searchResult.append(",");
        }

        logger.info("Entering getTopCountText method ..");

        return searchResult.toString();
    }


}
