package com.ajsw.barGeolocation.controller;

import com.ajsw.barGeolocation.domain.dto.*;
import com.ajsw.barGeolocation.domain.entity.BarEntity;
import com.ajsw.barGeolocation.service.IBarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/barLocation")
public class BarController {
    @Autowired
    private IBarService _barService;
    private static final Logger LOGGER = LoggerFactory.getLogger(BarController.class);

    @GetMapping("/getAll")
    public ResponseEntity<ResponseBaresDto> getAllPayment(){
        ResponseBaresDto BaresDto = new ResponseBaresDto();
        try{

            List<Bar> bars = _barService.getAll();

            BaresDto.setBody(bars);

            return ResponseEntity.ok(BaresDto);

        }catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            BaresDto.setErrors(new Errors(500, ex.getMessage(), Arrays.toString(ex.getStackTrace())));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaresDto);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseBarDto> getById(@PathVariable Integer id){
        ResponseBarDto barDto = new ResponseBarDto();
        try{
            Bar bar = _barService.getById(id);

            if (bar != null){
                barDto.setBody(bar);
                return ResponseEntity.ok(barDto);
            }else{
                barDto.setErrors(new Errors(204, "No se encontró Bar",null));
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(barDto);
            }

        }catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            barDto.setErrors(new Errors(500, ex.getMessage(), Arrays.toString(ex.getStackTrace())));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(barDto);
        }
    }

    @GetMapping("getByLocation")
    public  ResponseEntity<ResponseBaresDto> getByLocation(double latitude, double longitude, double distance){

        ResponseBaresDto baresDto = new ResponseBaresDto();
        try {
            List<Bar> barList = _barService.GetByLocation(latitude, longitude, distance);

            if (barList.isEmpty()){
                baresDto.setErrors(new Errors(204, "No se encuentran bares cerca",null));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(baresDto);
            }

            baresDto.setBody(barList);
            return  ResponseEntity.status(HttpStatus.OK).body(baresDto);

        }catch (Exception ex){
            LOGGER.error(ex.getMessage());
            baresDto.setErrors(new Errors(500, ex.getMessage(), Arrays.toString(ex.getStackTrace())));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(baresDto);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseBarDto> postBar(@RequestBody RequestBarPostDto body){

        ResponseBarDto barDto = new ResponseBarDto();

        try{

            Bar bar =  _barService.CreateBar(body);
            barDto.setBody(bar);

            return  ResponseEntity.status(HttpStatus.CREATED).body(barDto);

        }catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            barDto.setErrors(new Errors(500, ex.getMessage(), Arrays.toString(ex.getStackTrace())));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(barDto);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseBarDto> putBar(@RequestBody RequestBarPutDto barInput){

        ResponseBarDto barDto = new ResponseBarDto();
        try{
            Bar bar = _barService.PutBar(barInput);

            if (bar != null){
                barDto.setBody(bar);
                return ResponseEntity.ok(barDto);
            }else{
                barDto.setErrors(new Errors(204, "No se encontró Bar",null));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

        }catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            barDto.setErrors(new Errors(500, ex.getMessage(), Arrays.toString(ex.getStackTrace())));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(barDto);
        }
    }
}