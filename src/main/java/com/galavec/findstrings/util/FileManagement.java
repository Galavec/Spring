package com.galavec.findstrings.util;

import com.galavec.findstrings.dto.DetailedSearchDto;
import com.galavec.findstrings.dto.FileManagementDataDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManagement {
    static final Logger logger = LogManager.getLogger(FileManagement.class.getName());

    private List<String> lAddFoundText;
    private List<DetailedSearchDto> lDetailedSearchDto;
    private DetailedSearchDto detailedSearchDto;

    private void initialize() {
        this.lAddFoundText = new ArrayList<>();
        this.lDetailedSearchDto = new ArrayList<>();
        this.detailedSearchDto = new DetailedSearchDto();
    }

    public FileManagementDataDto readFile(String fileWhereToLook, String contentInlineToAvoid, List<String> nonRepeatingTexts) {
        initialize();

        var fileManagementDataDto = new FileManagementDataDto();

        String sLineContent;

        FileReader frGetTheFile = null;

        int numLine;

        for (String nonRepeatingText : nonRepeatingTexts) {
            numLine = 0;

            try {
                frGetTheFile = new FileReader(fileWhereToLook);
            } catch (FileNotFoundException e) {
                logger.error("File not found error: ", e);
                return errorInProcess("File not found.");
            }

            try (var brFile = new BufferedReader(frGetTheFile)) {
                while ((sLineContent = brFile.readLine()) != null) {
                    numLine++;

                    checkLine(sLineContent, contentInlineToAvoid, nonRepeatingText, numLine);
                }
            } catch (IOException e) {
                logger.error("Error reading lines from file: ", e);
                return errorInProcess("Error reading lines from file.");
            }


        }

        fileManagementDataDto.setResponseCode(1);
        fileManagementDataDto.setAddFoundText(this.lAddFoundText);
        fileManagementDataDto.setLDetailedSearchDto(this.lDetailedSearchDto);

        return fileManagementDataDto;
    }

    private void checkLine(String sLineContent, String contentInlineToAvoid, String nonRepeatingText, int numLine) {
        var stringStorage = new StringStorage();

        if ((contentInlineToAvoid.equals("") || !(sLineContent.contains(contentInlineToAvoid)))
                && sLineContent.contains(nonRepeatingText)) {
            this.detailedSearchDto = new DetailedSearchDto(nonRepeatingText, numLine, sLineContent);

            lDetailedSearchDto.add(this.detailedSearchDto);

            this.lAddFoundText = stringStorage.storeFoundText(nonRepeatingText, this.lAddFoundText);
        }
    }

    private FileManagementDataDto errorInProcess(String message) {
        return new FileManagementDataDto(0, message);
    }


}
