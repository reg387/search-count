package com.inteliment.searchcount.service;

import com.inteliment.searchcount.exception.SearchCountException;

import java.util.List;
import java.util.Map;

public interface SearchCountService {

    // Search the list of texts, which will return the counts respectively.
     List<Map> getCountText(List<String> searchWords) throws SearchCountException;


   //Find top number (based on path variable value) of texts which has highest counts
     String getTopCountText(int topTextCount) throws SearchCountException;



}
