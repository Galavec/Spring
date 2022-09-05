package com.galavec.findstrings.controller;

import javax.validation.Valid;

import com.galavec.findstrings.services.SearchResultsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galavec.findstrings.dto.RequestFindStrings;
import com.galavec.findstrings.dto.ResponseWillExist;

import java.util.UUID;

@RestController
@RequestMapping(value = "/findStrings")
public class FindStringsController {
    static final Logger logger = LogManager.getLogger(FindStringsController.class.getName());

    @Qualifier("searchResultsServiceImpl")
    @Autowired
    SearchResultsService searchResultsService;

    @PostMapping(value = "/willExist")
    public ResponseEntity<ResponseWillExist> verifyExistence(@Valid @RequestBody RequestFindStrings request) {
        var sThreadIdWillExist = UUID.randomUUID().toString();
        ThreadContext.put("sid", sThreadIdWillExist);

        logger.info("------------------------------------------------------------");
        logger.info("Received from 'willExist': {}", request);

        ResponseWillExist responseWillExist;

        responseWillExist = searchResultsService.groupResults(request, false);
        return new ResponseEntity<>(responseWillExist, responseWillExist.getResponseCode() != 0 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/detailedSearch")
    public ResponseEntity<ResponseWillExist> detailedSearch(@Valid @RequestBody RequestFindStrings request) {
        var sThreadIdWillExist = UUID.randomUUID().toString();
        ThreadContext.put("sid", sThreadIdWillExist);

        logger.info("------------------------------------------------------------");
        logger.info("Received from 'detailedSearch': {}", request);

        ResponseWillExist responseWillExist;

        responseWillExist = searchResultsService.groupResults(request, true);
        return new ResponseEntity<>(responseWillExist, responseWillExist.getResponseCode() != 0 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
