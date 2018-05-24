package com.inteliment.searchcount.controller;

import com.inteliment.searchcount.model.SearchTextModel;
import com.inteliment.searchcount.service.SearchCountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/counter-api")
public class SearchCountController {

    private static final Logger logger =
            LoggerFactory.getLogger(SearchCountController.class);

    @Autowired
    SearchCountService searchCountService;

    /**
     * API to find the number of occurrences for each word in the list
     *
     * @param searchTextModel list of words to be search
     * @return number of occurrences for each word in the list
     * @throws Exception
     *
     */

    @PostMapping( value = "/search", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map getCountText(@RequestBody SearchTextModel searchTextModel) throws Exception{

        logger.info("Entering getCountText API method ..");

        Map<String,List> resultMap =  new HashMap<String, List>();
        resultMap.put("counts", searchCountService.getCountText(searchTextModel.getSearchText()));

        logger.info("Exiting getCountText API method ..");

        return resultMap;
    }


    /**
     * API to find the words with top number of occurrences
     *
     * @param countValue limit of number of words
     * @return list of words with top number of occurrences
     * @throws Exception
     *
     */

    @GetMapping(value="/top/{countValue}")
    public String getTopCountText(@PathVariable int countValue, HttpServletResponse response) throws Exception{

        logger.info("Entering getTopCountText API method ..");

        response.setContentType("text/csv");

        String resultHeaderKey = "Content-Disposition";
        String resultHeaderValue = String.format("attachment; filename=\"%s\"", "result.txt");
        response.setHeader(resultHeaderKey, resultHeaderValue);

        String result = searchCountService.getTopCountText(countValue);

        logger.info("Exiting getTopCountText API method ..");

        return result;
    }

    public SearchCountService getSearchCountService() {
        return searchCountService;
    }

    public void setSearchCountService(SearchCountService searchCountService) {
        this.searchCountService = searchCountService;
    }

}
