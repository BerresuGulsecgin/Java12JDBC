package com.berre;

import java.sql.*;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        /*
        DenemeGit
        //database oluşturlaım school
        //student tablomuz olsun

        Databaseye bağlan bunu bi kontrol edelim bağlandık mı diye
        bağlandıysa konsola bağlantı başarılı değilse başarısız yazdıralım

        execute dite metod yazalım.Bu metod aldığı sql parametresini çalıştırdın

        createStudent(connection,student);
        updateStudent(connection,student,id);

        findStudentByCityName(String cityName, Connection) :

        deleteById();

         */

        String url="jdbc:postgresql://localhost:5432/school_Java12";
        String username="postgres";
        String password="1234";

        Connection  connection = null;
        try {
            connection= DriverManager.getConnection(url,username,password);
            System.out.println("Bağlantı başarılı");

            String sql="insert into student(name, surname, city) values ('Mehmet','Yardimci','Ankara')";
            //execute(connection,sql);

            Student student=new Student("Ali","Yardımcı","İzmir");
            //createStudent(connection,student);
            //updateStudent(connection,student,2);
            //findStudentByCityName(connection,"ank");
            deleteById(connection,3);

        } catch (SQLException e) {
            System.out.println("Bağlantı başarısız"+e);

        }finally {
            try {
                if (connection != null){
                    connection.close();
                    System.out.println("Kapatma işlemi başarılı");
                }

            } catch (SQLException e) {
                System.out.println("bağlantı kapatma hatası");
            }
        }


    }

    public static void execute(Connection connection, String sql){

        try {
            //parametre almayacaksa bunu kullan
            //parametre alaccaksa PreparedStatment bullan
            Statement statement=connection.createStatement();
            statement.execute(sql);
            System.out.println("İşlem başarılı");
        } catch (SQLException e) {
            System.out.println("Sorgu hatası");
        }

    }

    public static void createStudent(Connection connection,Student student){
        String sqlQuery ="INSERT INTO student(name, surname, city) VALUES (?, ?, ?)";
        try {

            PreparedStatement preparedStatement=connection.prepareStatement(sqlQuery);

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getCity());
            preparedStatement.executeUpdate();
            System.out.println("Kayıt başarılı");

        } catch (SQLException e) {
            System.out.println("Kayıt başarısız"+e);
        }
    }

    public static void updateStudent(Connection connection, Student student, int id){
        String sqlQuery="UPDATE student SET name=?, surname=?, city=? WHERE id=?";

        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getCity());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
            System.out.println("Güncelleme başarılı");
        } catch (SQLException e) {
            System.out.println("Güncelleme başarısız"+e);
        }


    }

    public static void findStudentByCityName(Connection connection, String cityName){
        String sqlQuery = "SELECT * FROM student where city ilike ?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,"%"+cityName+"%");
            ResultSet resultSet=preparedStatement.executeQuery();


            while(resultSet.next()){
                int id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                String surname=resultSet.getString("surname");
                String city=resultSet.getString("city");

                System.out.println(id+" "+name+" "+surname+" "+city);
            }





        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public static void deleteById(Connection connection, int id){
        String sqlQuery = "DELETE FROM student WHERE id=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,id);
            int affectedRow=preparedStatement.executeUpdate();
            if (affectedRow>0) System.out.println("Silindi");
            else System.out.println("id hatalı");
        } catch (SQLException e) {
            System.out.println("Silme işleminde hata");
        }

    }
}