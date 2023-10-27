package com.berre.repository;

import com.berre.entity.Car;
import com.berre.util.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CarRepository implements ICrud <Car> {

    @Override
    public void saveAll(List<Car> t) {
        if (dataBaseControl()){
            System.out.println("Zaten veriler atıldı");
        }else {

            String query = "insert into cars(brand, car_model, model_year, dealer_id) values(?,?,?,?)";
            PreparedStatement preparedStatement = null;


            try {
                preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(query);
                for (Car car : t) {
                    preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(query);
                    preparedStatement.setString(1, car.getBrand());
                    preparedStatement.setString(2, car.getCarModel());
                    preparedStatement.setString(3, car.getModelYear());
                    preparedStatement.setInt(4, car.getDealershipId());
                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }

    @Override
    public void saveAll(Car car) {

    }

    @Override
    public void update(Car car, int id) {

    }

    @Override
    public List<Car> findAll() {
        return null;
    }

    private boolean dataBaseControl(){
        boolean control = false;

        String sql="select * from cars";
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;

        try {
            preparedStatement=DbConnection.getInstance().getConnection().prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            control=resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
         return control;
    }
}
