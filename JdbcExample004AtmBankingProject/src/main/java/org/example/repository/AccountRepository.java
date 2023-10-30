package org.example.repository;

import org.example.entity.Account;
import org.example.entity.TransferDekont;
import org.example.entity.User;
import org.example.util.DbConnection;
import org.example.util.RandomGenerateAccountNo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {

    public String createAccount(User user){
        String query="insert into accounts(user_id, account_no) values (?,?)";
        PreparedStatement preparedStatement=null;
        Account account = Account.builder()
                .userId(user.getId())
                .accountNo(RandomGenerateAccountNo.generateAccountNo())
                .build();
        try {
            preparedStatement= DbConnection.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setInt(1,account.getUserId());
            preparedStatement.setString(2, account.getAccountNo());
            if(preparedStatement.executeUpdate()>0){
                return account.getAccountNo();
            }else {
                throw new RuntimeException("hesap oluşmadı!!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //List<Account> getAccountByEmail(String email)
    //transferMoney(int amount, String accountNo)

    public List<Account> getAccountByEmail(String email){
        String query="select a.* from users as u inner join accounts as a on a.user_id=u.id where u.email=?";
        PreparedStatement preparedStatement=null;
        List<Account> accountList=new ArrayList<>();

        try {
            preparedStatement=DbConnection.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1,email);
            ResultSet resultSet=preparedStatement.executeQuery();


            while (resultSet.next()){
                double balance=resultSet.getDouble("balance");
                String accountNo=resultSet.getString("account_no");
                int id = resultSet.getInt("id");
                Date creationDate = resultSet.getDate("creation_date");

                Account account=Account.builder()
                        .balance(balance)
                        .accountNo(accountNo)
                        .id(id)
                        .creationDate(creationDate)
                        .build();

                accountList.add(account);


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return accountList;
    }

    public TransferDekont transferMoney(int amount, String accountNo, User user){
      String query="update accounts set balance = balance + ? where account_no=?";
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=DbConnection.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setDouble(1,amount);
            preparedStatement.setString(2,accountNo);
            preparedStatement.executeUpdate();

            String receiverName=findUserNameByAccountNo(accountNo);

            TransferDekont transferDekont=TransferDekont.builder()
                    .transactionDate(new java.util.Date())
                    .sendAmountTotal(amount)
                    .senderName(user.getName())
                    .receiverName(receiverName)
                    .build();
            return transferDekont;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public String findUserNameByAccountNo(String accountNo){
        String query="select users.name from users inner join accounts on accounts.user_id=users.id where account_no=?";
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=DbConnection.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1,accountNo);

            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                return resultSet.getString("name");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "";

    }
}
