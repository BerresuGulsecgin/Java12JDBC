package com.berre.entity;

import com.berre.entity.enums.EAddressType;
import com.berre.entity.enums.EGender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//name ve surname için information sınıfı oluşturuup çek.

//Cinsiyet tutmak istiyorum

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name="tbl_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Information information;

    @Column(nullable = false,length = 32)
    private String password;

    @Column(unique = true,nullable = false,name="user_name")
    private String username;

    @Column(name="gender")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EGender eGender=EGender.UNSPECIFIED;

    @ElementCollection
    private List<String> interests;

    @ElementCollection
    private Map<EAddressType,Address> addresses;


}
