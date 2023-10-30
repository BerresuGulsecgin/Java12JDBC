package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.util.RandomGenerateAccountNo;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    private int id;
    private int userId;
    private double balance;
    private String accountNo;
    private Date creationDate;


    public Account(int userId) {
        this.userId = userId;
        this.accountNo= RandomGenerateAccountNo.generateAccountNo();
    }


}
