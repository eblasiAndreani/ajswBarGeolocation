package com.ajsw.barGeolocation.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseBaresDto {
    private List<Bar> body;
    private Errors errors;
}
