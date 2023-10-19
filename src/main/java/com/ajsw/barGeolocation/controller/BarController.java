package com.ajsw.barGeolocation.controller;

import com.ajsw.barGeolocation.domain.dto.RequestBarPostDto;
import com.ajsw.barGeolocation.domain.dto.RequestBarPutDto;
import com.ajsw.barGeolocation.domain.entity.BarEntity;
import com.ajsw.barGeolocation.service.IBarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/barLocation")
public class BarController {
    @Autowired
    private IBarService _barService;
    private static final Logger LOGGER = LoggerFactory.getLogger(BarController.class);

    @GetMapping("/getAll")
    public ResponseEntity<List<BarEntity>> getAllPayment(){
        try{
            List<BarEntity> a = _barService.getAll();
            return ResponseEntity.ok(a);
        }catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/getById")
    public ResponseEntity<BarEntity> getById(@RequestParam Integer id){
        try{
            BarEntity bar = _barService.getById(id);
            if (bar != null){
                return ResponseEntity.ok(bar);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

        }catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("getByLocation")
    public  ResponseEntity<List<BarEntity>> getByLocation(double latitude, double longitude, double distance){

        try {
            List<BarEntity> barList = _barService.GetByLocation(latitude, longitude, distance);
            if (barList.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return  ResponseEntity.status(HttpStatus.OK).body(barList);
        }catch (Exception ex){
            LOGGER.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<BarEntity> postBar(@RequestBody RequestBarPostDto body){

        try{

            return  ResponseEntity.ok(_barService.CreateBar(body));

        }catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<BarEntity> putBar(@RequestBody RequestBarPutDto barInput){

        try{
            BarEntity bar = _barService.PutBar(barInput);
            if (bar != null){
                return ResponseEntity.ok(bar);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

        }catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}