package com.berre.entity;

import com.berre.entity.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private  String identity;
    @Embedded
    private Information information;
    @ManyToMany(mappedBy = "customers")
    private List<Product> products;


    @Enumerated(EnumType.STRING)
    @Builder.Default
    private AccountType accountType=AccountType.NORMAL;
}
