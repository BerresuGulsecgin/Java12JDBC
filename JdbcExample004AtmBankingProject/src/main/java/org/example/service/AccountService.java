package org.example.service;

import org.example.entity.Account;
import org.example.entity.TransferDekont;
import org.example.entity.User;
import org.example.repository.AccountRepository;

import java.util.List;

public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService() {
        this.accountRepository = new AccountRepository();
    }

    public void createAccount(User user){
        String accountNo = accountRepository.createAccount(user);

            System.out.println("Hesabınız oluşturuldu.Hesap no -> "+accountNo);

    }

    public void getAccountByEmail(String email){
        List<Account> accountList=accountRepository.getAccountByEmail(email);
        accountList.forEach(account -> System.out.println(account.getAccountNo()+" "+account.getBalance()));
        System.out.println(accountList);
    }

    public void transfer(int amount, String accountNo, User user){
        TransferDekont transferDekont=accountRepository.transferMoney(amount,accountNo,user);
        System.out.println(transferDekont);
    }


}
