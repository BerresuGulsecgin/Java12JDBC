package com.berre.util;

import com.berre.entity.Car;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class FileUtils {

    /*
    readFile(String path) metodu yazalım ;
    bu metod dosya okuma işlemi yapacak. okuduğu verileri liste şeklinde geri dönecek


    sonuçta elimde Car listesi  olacak şekilde bir metod yazalım
     */

    public static List<String> readFile(String path){
        List<String> data=new ArrayList<>();
        try (BufferedReader reader=new BufferedReader(new FileReader(path))){
            String satir;
            while ((satir=reader.readLine())!=null){
                data.add(satir);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

//    public List<Car> carList(List<String> data){
//        List<Car> carList = new ArrayList<>();
//            for(String veri : data){
//                String[] dizi=veri.split(",");
//                Car car=new Car(Integer.parseInt(dizi[0]),dizi[1],dizi[2],dizi[3],Integer.parseInt(dizi[4]));
//                carList.add(car);
//            }
//
//        return carList;
//    }

    public static List<Car> getCarList(List<String> data){
        List<Car> cars = new ArrayList<>();
        for(String stringCar : data){
           cars.add(convertToCar(stringCar.split(",")));
        }

        return cars;
    }

    public static Car convertToCar(String[] array) {
        Car car=new Car(Integer.parseInt(array[0]),array[1],array[2],array[3],Integer.parseInt(array[4]));
        return car;
    }


}
