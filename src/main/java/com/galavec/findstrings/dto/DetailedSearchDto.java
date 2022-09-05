package com.galavec.findstrings.dto;

import lombok.Data;

@Data
public class DetailedSearchDto {
    private String foundText;
    private Integer lineNumber;
    private String showLine;

    public DetailedSearchDto(String foundText, Integer lineNumber, String showLine) {
        this.foundText = foundText;
        this.lineNumber = lineNumber;
        this.showLine = showLine;
    }

    public DetailedSearchDto() {
        super();
    }
}
