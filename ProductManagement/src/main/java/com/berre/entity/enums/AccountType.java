package com.berre.entity.enums;

import lombok.Getter;

@Getter
public enum AccountType {
    NORMAL(10), GOLD(50), PREMIUM(100);

    private final double indirimMiktari;

    AccountType(double indirimMiktari){
        this.indirimMiktari=indirimMiktari;
    }


    //İNDİRİM MİKTARI
    //NORMAL 10 TL
    //GOLD 50 TL
    //PERMİUM 100 TL
}
