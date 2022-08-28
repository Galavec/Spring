package com.galavec.findstrings.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galavec.findstrings.dto.RequestFindStrings;
import com.galavec.findstrings.dto.ResponseWillExist;
import com.galavec.findstrings.services.impl.SearchResultsServiceImpl;

@RestController
@RequestMapping(value = "/findStrings")
public class FindStringsController {

	@Autowired
	SearchResultsServiceImpl searchResultsServiceImpl;

	@PostMapping(value = "/willExist")
	public ResponseEntity<ResponseWillExist> verifyExistence(@Valid @RequestBody RequestFindStrings request) {
		ResponseWillExist responseWillExist;

		responseWillExist = searchResultsServiceImpl.groupResults(request);
		return new ResponseEntity<>(responseWillExist,
				responseWillExist.getResponseCode() != 0 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
