package org.example.repository;

import org.example.entity.User;
import org.example.util.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepository {

    //Login metodu yazalım.User dönsün

    public User login(String email, String password){
        //
        String query="select * from users where email=? and password=?";
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=DbConnection.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                int existedId= resultSet.getInt("id");
                String existedName = resultSet.getString("name");
                String existedEmail = resultSet.getString("email");

                User user=User.builder()
                        .id(existedId)
                        .name(existedName)
                        .email(existedEmail)
                        .build();
                return user;

            }else {
                throw new RuntimeException("Kullanıcı bulunamadı");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
