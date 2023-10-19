package com.ajsw.barGeolocation.utils;

public class HelperLocation {
    public static double CalcularDistancia(double lat1, double lon1, double lat2, double lon2){
        // Radio promedio de la Tierra en kilómetros
        double radioTierra = 6371;

        // Conversión de grados a radianes
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        // Diferencias en latitud y longitud
        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        // Fórmula haversine
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distancia = radioTierra * c;

        return distancia;
    }
}
