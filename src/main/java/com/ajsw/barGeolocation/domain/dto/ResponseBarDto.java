package com.ajsw.barGeolocation.domain.dto;

import lombok.Data;

@Data
public class ResponseBarDto {
    private Bar body;
    private Errors errors;
}
