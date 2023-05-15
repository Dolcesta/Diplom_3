package com.stellarburger;

import java.util.Random;

public class User {
    private String email = "but" + new Random().nextInt(10000) + "@yandex.ru";
    private String password = "sunni" + new Random().nextInt(10000);
    private String name = "moone" + new Random().nextInt(10000);

    public String getRandomName() {
        return this.name;
    }

    public  String getRandomEmail() {
        return this.email;
    }

    public  String getRandomPassword() {
        return this.password;
    }


}

