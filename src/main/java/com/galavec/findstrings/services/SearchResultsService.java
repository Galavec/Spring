package com.galavec.findstrings.services;

import com.galavec.findstrings.dto.RequestFindStrings;
import com.galavec.findstrings.dto.ResponseWillExist;

public interface SearchResultsService {
	ResponseWillExist groupResults(RequestFindStrings requestFindStrings, Boolean detailedSearch);
}
