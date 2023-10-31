package com.ajsw.barGeolocation.service.impl;

import com.ajsw.barGeolocation.domain.dto.Bar;
import com.ajsw.barGeolocation.domain.dto.RequestBarPostDto;
import com.ajsw.barGeolocation.domain.dto.RequestBarPutDto;
import com.ajsw.barGeolocation.domain.entity.BarEntity;
import com.ajsw.barGeolocation.repository.IBarRepository;
import com.ajsw.barGeolocation.service.IBarService;
import com.ajsw.barGeolocation.utils.HelperLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BarService implements IBarService {
    @Autowired
    private IBarRepository _barRepository;

    public List<Bar> getAll(){

        List<BarEntity> barEntityList = _barRepository.findAll();
        List<Bar> barList = new ArrayList<>();

        for (BarEntity barEntity: barEntityList) {
            barList.add(BarEntityToBar(barEntity));
        }

        return barList;
    }
    public Bar getById(Integer id){
        Optional<BarEntity> barEntity = _barRepository.findById(id);
        Bar bar = null;

        if(barEntity.isPresent()){
            bar = BarEntityToBar(barEntity.get());
        }

        return bar;
    }
    public Bar CreateBar(RequestBarPostDto barDto){

        BarEntity barEntity = new BarEntity();
        barEntity.setName(barDto.name);
        barEntity.setDescription(barDto.description);
        barEntity.setLogo(barDto.logo);
        barEntity.setLatitud(barDto.latitude);
        barEntity.setLongitud(barDto.longitude);

        _barRepository.save(barEntity);

        return BarEntityToBar(barEntity);
    }
    public List<Bar> GetByLocation(double latitude, double longitude, double radio ){

        List<BarEntity> baresDB = _barRepository.findAll();
        List<Bar> baresCercanos = new ArrayList<>();

        for (BarEntity lugar : baresDB) {
            double distance = HelperLocation.CalcularDistancia(latitude, longitude, lugar.getLatitud(), lugar.getLongitud());//calcularDistanciaHaversine(latitud, longitud, lugar.getLatitud(), lugar.getLongitud());
            if (distance <= radio) {
                baresCercanos.add(BarEntityToBar(lugar));
            }
        }

        return  baresCercanos;
    }
    public Bar PutBar(RequestBarPutDto barInput){

        BarEntity barFromDb = _barRepository.findById(barInput.IdBar).orElse(null);

        if (barFromDb != null){

            if (barInput.name != null)
                barFromDb.setName(barInput.name);
            if (barInput.description != null)
                barFromDb.setDescription(barInput.description);
            if (barInput.logo != null)
                barFromDb.setLogo(barInput.logo);
            if (barInput.longitude != 0)
                barFromDb.setLongitud(barInput.longitude);
            if (barInput.latitude != 0)
                barFromDb.setLatitud(barInput.latitude);

            _barRepository.save(barFromDb);

            return BarEntityToBar(barFromDb);

        }else{
            return  null;
        }
    }
    private Bar BarEntityToBar(BarEntity barEntity){

        Bar bar = new Bar();
        bar.setDescription(barEntity.getDescription());
        bar.setId(barEntity.getId());
        bar.setName(barEntity.getName());
        bar.setLogo(barEntity.getLogo());
        bar.setLongitude(barEntity.getLongitud());
        bar.setLatitude(barEntity.getLatitud());

        return bar;
    }
}
