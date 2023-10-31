package com.ajsw.barGeolocation.service;

import com.ajsw.barGeolocation.domain.dto.Bar;
import com.ajsw.barGeolocation.domain.dto.RequestBarPostDto;
import com.ajsw.barGeolocation.domain.dto.RequestBarPutDto;
import com.ajsw.barGeolocation.domain.entity.BarEntity;

import java.util.List;

public interface IBarService {
    List<Bar> getAll();
    Bar getById(Integer id);
    Bar CreateBar(RequestBarPostDto barDto);
    List<Bar> GetByLocation(double latitude, double longitude, double radio);
    Bar PutBar(RequestBarPutDto barInput);
}
