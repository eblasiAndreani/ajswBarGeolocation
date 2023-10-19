package com.ajsw.barGeolocation.service;

import com.ajsw.barGeolocation.domain.dto.RequestBarPostDto;
import com.ajsw.barGeolocation.domain.dto.RequestBarPutDto;
import com.ajsw.barGeolocation.domain.entity.BarEntity;

import java.util.List;

public interface IBarService {
    List<BarEntity> getAll();
    BarEntity getById(Integer id);
    BarEntity CreateBar(RequestBarPostDto barDto);
    List<BarEntity> GetByLocation(double latitude, double longitude, double radio);
    BarEntity PutBar(RequestBarPutDto barInput);
}
