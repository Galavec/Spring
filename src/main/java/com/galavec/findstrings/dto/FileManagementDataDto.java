package com.galavec.findstrings.dto;

import lombok.Data;

import java.util.List;

@Data
public class FileManagementDataDto {
    private Integer responseCode;
    private String responseMessage;
    private List<String> addFoundText;
    private List<DetailedSearchDto> lDetailedSearchDto;

    public FileManagementDataDto(Integer responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public FileManagementDataDto() {
        super();
    }
}
