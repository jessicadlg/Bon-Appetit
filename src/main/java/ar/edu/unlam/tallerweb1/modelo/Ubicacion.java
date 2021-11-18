package ar.edu.unlam.tallerweb1.modelo;

public class Ubicacion {

    private Double lat;

    private Double lon;

    public Ubicacion(){

    }

    public Ubicacion(Double lon, Double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

}
