package com.galavec.findstrings.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.galavec.findstrings.dto.*;
import com.galavec.findstrings.util.FileManagement;
import com.galavec.findstrings.util.StringStorage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.galavec.findstrings.services.SearchResultsService;

@Service
public class SearchResultsServiceImpl implements SearchResultsService {
    static final Logger logger = LogManager.getLogger(SearchResultsServiceImpl.class.getName());

    private List<String> lNonRepeatingText;

    private void initializeVarSearchResults() {
        this.lNonRepeatingText = new ArrayList<>();
    }

    @Override
    public ResponseWillExist groupResults(RequestFindStrings requestFindStrings, Boolean detailedSearch) {
        logger.info("Start in SearchResults.groupResults.");

        initializeVarSearchResults();

        var fileManagement = new FileManagement();
        var stringStorage = new StringStorage();

        FileManagementDataDto fileManagementDataDto;

        List<String> lTextsNotFound;
        List<String> lRepeatedTexts = new ArrayList<>();

        for (String dataToSearch : stringStorage.separatedByCommas(requestFindStrings.getStringToSearch())) {
            lRepeatedTexts = getRepeatedAndNonRepeatingData(dataToSearch, lRepeatedTexts);
        }

        fileManagementDataDto = fileManagement.readFile(requestFindStrings.getFileWhereToLook(), requestFindStrings.getLineToAvoid(),
                this.lNonRepeatingText);

        if (fileManagementDataDto.getResponseCode() != 1)
            return errorInProcess(fileManagementDataDto.getResponseMessage());

        lTextsNotFound = stringStorage.storeTextNotFound(this.lNonRepeatingText, fileManagementDataDto.getAddFoundText());

        logger.info("Ends in SearchResults.groupResults.");

        return successfulProcess(fileManagementDataDto, lTextsNotFound, lRepeatedTexts, detailedSearch);
    }

    private List<String> getRepeatedAndNonRepeatingData(String dataToSearch, List<String> lRepeatedTexts) {
        var stringStorage = new StringStorage();

        if (Boolean.TRUE.equals(verifyAndStoreNonRepeatingData(dataToSearch)))
            lRepeatedTexts = stringStorage.storeRepeatedData(dataToSearch, lRepeatedTexts);

        return lRepeatedTexts;
    }

    private Boolean verifyAndStoreNonRepeatingData(String data) {
        if (!(this.lNonRepeatingText.contains(data) || data.equals("")))
            this.lNonRepeatingText.add(data);
        else
            return true;

        return false;
    }

    private ResponseWillExist errorInProcess(String message) {
        return new ResponseWillExist(0, message);
    }

    private ResponseWillExist successfulProcess(FileManagementDataDto fileManagementDataDto, List<String> lTextsNotFound, List<String> lRepeatedTexts,
                                                Boolean detailedSearch) {
        var recordsFoundDto = new RecordsFoundDto();
        var recordsNotFoundDto = new RecordsNotFoundDto();
        var repeatedRecordsDto = new RepeatedRecordsDto();

        recordsFoundDto.setQuantityFound(fileManagementDataDto.getAddFoundText().size());

        if (Boolean.TRUE.equals(detailedSearch))
            recordsFoundDto.setDetailedSearch(fileManagementDataDto.getLDetailedSearchDto());
        else
            recordsFoundDto.setFoundTexts(fileManagementDataDto.getAddFoundText().toArray(new String[0]));

        recordsNotFoundDto.setQuantityNotFound(lTextsNotFound.size());
        recordsNotFoundDto.setTextNotFound(lTextsNotFound.toArray(new String[0]));

        repeatedRecordsDto.setAmountOfRepeatedText(lRepeatedTexts.size());
        repeatedRecordsDto.setRepeatedText(lRepeatedTexts.toArray(new String[0]));

        return new ResponseWillExist(1, "Successful", recordsFoundDto, recordsNotFoundDto, repeatedRecordsDto);
    }

}
