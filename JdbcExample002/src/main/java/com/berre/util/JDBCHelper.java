package com.berre.util;

import com.berre.repository.PersonRepository;
import com.berre.util.constant.JDBCConstant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCHelper {

    public static Connection getConnection(){
        Connection connection=null;
        try {
            connection= DriverManager.getConnection(JDBCConstant.DATABASE_URL,JDBCConstant.DATABASE_USERNAME,JDBCConstant.DATABASE_PASSWORD);
            System.out.println("Bağlantı başarılı");

        } catch (SQLException e) {
            System.out.println("Connection oluşturulurken hata meydana geldi!");
        }
        return connection;
    }

    public static void closeConnection(Connection connection){
        if (connection != null){
            try {
                connection.close();
                System.out.println("Connection kapandı");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement){
        if (preparedStatement != null){
            try {
                preparedStatement.close();
                System.out.println("preparedStatement kapandı");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
