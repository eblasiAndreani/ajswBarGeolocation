package com.ajsw.barGeolocation.service.impl;

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

@Service
public class BarService implements IBarService {
    @Autowired
    private IBarRepository _barRepository;

    public List<BarEntity> getAll(){
        return _barRepository.findAll();
    }
    public BarEntity getById(Integer id){
        return _barRepository.findById(id).orElse(null);
    }
    public BarEntity CreateBar(RequestBarPostDto barDto){

        BarEntity bar = new BarEntity();
        bar.setName(barDto.name);
        bar.setDescription(barDto.description);
        bar.setLogo(barDto.logo);
        bar.setLatitud(barDto.latitude);
        bar.setLongitud(barDto.longitude);

        return _barRepository.save(bar);

    }
    public List<BarEntity> GetByLocation(double latitude, double longitude, double radio ){

        List<BarEntity> baresDB = _barRepository.findAll();
        List<BarEntity> baresCercanos = new ArrayList<>();

        for (BarEntity lugar : baresDB) {
            double distance = HelperLocation.CalcularDistancia(latitude, longitude, lugar.getLatitud(), lugar.getLongitud());//calcularDistanciaHaversine(latitud, longitud, lugar.getLatitud(), lugar.getLongitud());
            if (distance <= radio) {
                baresCercanos.add(lugar);
            }
        }
        return  baresCercanos;
    }
    public BarEntity PutBar(RequestBarPutDto barInput){

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

            return _barRepository.save(barFromDb);

        }else{
            return  null;
        }
    }
}
