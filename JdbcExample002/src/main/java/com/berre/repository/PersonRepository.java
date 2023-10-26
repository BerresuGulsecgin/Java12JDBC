package com.berre.repository;

import com.berre.entity.Person;
import com.berre.util.JDBCHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonRepository {

    public void save(Person person){

        Connection connection=JDBCHelper.getConnection();
        String query="INSERT INTO persons(first_name, last_name, email) VALUES (?, ?, ?);";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.executeUpdate();
            System.out.println("Ekleme işlemi başarılı");
            connection.close();
        } catch (SQLException e) {
            System.out.println("Ekleme işlemi sırasında hata"+e.getMessage());
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void register(Person person){
        String sql="INSERT INTO persons(first_name, last_name, email) VALUES (?, ?, ?);";
        Connection connection=null;
        PreparedStatement preparedStatement=null;

        connection=JDBCHelper.getConnection();
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.executeUpdate();
            System.out.println("Ekleme işlemi başarılı");


        } catch (SQLException e) {
            throw new RuntimeException("Bağlantı hatası"+e);
        }finally {
                JDBCHelper.closeConnection(connection);
                JDBCHelper.closePreparedStatement(preparedStatement);
            }
        }
        public void findAll(){
            String sql="SELECT * FROM persons";
            Connection connection=JDBCHelper.getConnection();
            PreparedStatement preparedStatement=null;
            List<Person> personList=new ArrayList<>();
            try {
                preparedStatement=connection.prepareStatement(sql);
                ResultSet resultSet=preparedStatement.executeQuery();

                while (resultSet.next()){
                    int id=resultSet.getInt("id");
                    String firstName=resultSet.getString("first_name");
                    String lastName=resultSet.getString("last_name");
                    String email=resultSet.getString("email");
                    Date joinedDate=resultSet.getDate("joined_date");
                    personList.add(new Person(id,firstName,lastName,email,joinedDate));

                }
                personList.forEach(p-> System.out.println(p));


            } catch (SQLException e) {
                System.out.println("Arama işlemi sırasında bir hata");


            }finally {
                JDBCHelper.closeConnection(connection);
                JDBCHelper.closePreparedStatement(preparedStatement);
            }


        }

        public void update(Person person){
        String sql = "update persons set email=? where id=? ";
            Connection connection=JDBCHelper.getConnection();
            PreparedStatement preparedStatement=null;

            try {
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,person.getEmail());
                preparedStatement.setInt(2,person.getId());
                preparedStatement.executeUpdate();
                System.out.println("Güncelleme işlemi başarılı");

            } catch (SQLException e) {
                System.out.println("Güncelleme işlemi sırasında hata");
            }finally {
                JDBCHelper.closeConnection(connection);
                JDBCHelper.closePreparedStatement(preparedStatement);
            }

        }

        public Person findById(int idargs){
        String sql="select * from persons where id="+idargs;
            Connection connection=JDBCHelper.getConnection();
            PreparedStatement preparedStatement=null;
            try {
                preparedStatement=connection.prepareStatement(sql);
                ResultSet resultSet=preparedStatement.executeQuery();

                resultSet.next();
                int id=resultSet.getInt("id");
                String firstName=resultSet.getString("first_name");
                String lastName=resultSet.getString("last_name");
                String email=resultSet.getString("email");
                Date joinedDate=resultSet.getDate("joined_date");

                Person person=new Person(id,firstName,lastName,email,joinedDate);
                System.out.println(person);
                return person;

            } catch (SQLException e) {
                System.out.println("Id ile arama sırasında bir hata oluştu");
                return null;
            }finally {
                JDBCHelper.closeConnection(connection);
                JDBCHelper.closePreparedStatement(preparedStatement);
            }

        }

        public void deletePersonById(Person person){
            String sql="delete from persons where id=?";
            Connection connection=JDBCHelper.getConnection();
            PreparedStatement preparedStatement=null;

            try {
                if (person!=null){
                    preparedStatement=connection.prepareStatement(sql);

                    preparedStatement.setInt(1,person.getId());
                    preparedStatement.executeUpdate();
                    System.out.println("Silme işlemi başarılı");
                }else {
                    System.out.println("Aranan müşteri bulunamadı");
                }

            } catch (SQLException e) {
                System.out.println("Silme işlemi sırasında hata");
            }finally {
                JDBCHelper.closeConnection(connection);
                JDBCHelper.closePreparedStatement(preparedStatement);
            }
        }

        public void deleteAll(){
            String sql="delete from persons";
            Connection connection=JDBCHelper.getConnection();
            PreparedStatement preparedStatement=null;

            try {
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
                System.out.println("Tüm verileri silme işlemi başarılı");
            } catch (SQLException e) {
                System.out.println("Tüm verileri silme işlemi sırasında hata");
            }finally {
                JDBCHelper.closeConnection(connection);
                JDBCHelper.closePreparedStatement(preparedStatement);
            }

        }

    }


