package com.ajsw.barGeolocation.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Errors {
    private Integer code;
    private String message;
    private String trace;
}
