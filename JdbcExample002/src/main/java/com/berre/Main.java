package com.berre;

import com.berre.util.Menu;

/*
perdonDb oluşturalım -> id, firstName, lastName, joinedDate

2- Util paketinde JDBCHelper diye class oluştur
getConnection() bu metodda bize connection nesnesi dönecek

3- buradaki database bilgileri bunlarıda farklı bir sınıftan çekelim
yine util paketi içinde JDBCconstant sınıfından çekelim

4-register işlemi :
    kullanıcıdan isim soyisim e mail al ve databaseye kaydet
    ilk repository katmanını yazıyoruz
 */

public class Main {
    public static void main(String[] args) {

        Menu menu = new Menu();
        menu.menu();
    }
}