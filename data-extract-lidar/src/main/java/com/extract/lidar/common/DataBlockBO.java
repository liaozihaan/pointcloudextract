package com.extract.lidar.common;

public class DataBlockBO {


    private Double distance;
    private Integer Intensity;
    private Integer reflectivity;

    public DataBlockBO(Double distance, Integer intensity, Integer reflectivity) {
        this.distance = distance;
        Intensity = intensity;
        this.reflectivity = reflectivity;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getIntensity() {
        return Intensity;
    }

    public void setIntensity(Integer intensity) {
        Intensity = intensity;
    }

    public Integer getReflectivity() {
        return reflectivity;
    }

    public void setReflectivity(Integer reflectivity) {
        this.reflectivity = reflectivity;
    }

    @Override
    public String toString() {
        return "DataBlockBO{" +
                "distance=" + distance +
                ", Intensity=" + Intensity +
                ", reflectivity=" + reflectivity +
                '}';
    }
}


