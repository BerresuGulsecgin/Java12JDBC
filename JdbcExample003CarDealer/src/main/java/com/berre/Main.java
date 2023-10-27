package com.berre;

import com.berre.repository.CarRepository;
import com.berre.util.Constant;
import com.berre.util.FileUtils;

public class Main {
    /*
    1- DataBase oluşturmamız lazım
    2- Entity leri oluştur
    3- Dosya okuma işlemi için gerekli yapıyı oluştur
    4- Singleton bir connection oluşturalım
    5- verileri databaseye yaz
    6- Interface oluşturalım
     */
    public static void main(String[] args) {

        CarRepository carRepository=new CarRepository();

        carRepository.saveAll(FileUtils.getCarList(FileUtils.readFile(Constant.CAR_FILE)));

    }
}