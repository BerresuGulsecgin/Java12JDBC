package com.berre.entity;

public class Car  {

    private int id;
    private String brand;
    private String modelYear;
    private String carModel;
    private int dealershipId;

    public Car() {
    }

    public Car(String brand, String modelYear, String carModel, int dealershipId) {
        this.brand = brand;
        this.modelYear = modelYear;
        this.carModel = carModel;
        this.dealershipId = dealershipId;
    }

    public Car(int id, String brand, String modelYear, String carModel, int dealershipId) {
        this.id = id;
        this.brand = brand;
        this.modelYear = modelYear;
        this.carModel = carModel;
        this.dealershipId = dealershipId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getDealershipId() {
        return dealershipId;
    }

    public void setDealershipId(int dealershipId) {
        this.dealershipId = dealershipId;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", modelYear='" + modelYear + '\'' +
                ", carModel='" + carModel + '\'' +
                '}';
    }
}
