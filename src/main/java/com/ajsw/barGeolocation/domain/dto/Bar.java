package com.ajsw.barGeolocation.domain.dto;

import lombok.Data;

@Data
public class Bar {
    private int id;
    private String name;
    private String description;
    private String logo;
    private Double latitude;
    private Double longitude;
}
