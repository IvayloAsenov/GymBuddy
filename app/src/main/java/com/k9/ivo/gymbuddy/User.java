package com.k9.ivo.gymbuddy;

/**
 * Created by Jonathan on 2017-11-13.
 */

public class User {
    public User(String name_, String email_, String passowrd_, String age_, String gender_) {
        name = name_;
        email = email_;
        passowrd = passowrd_;
        age = age_;
        gender = gender_;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getPassword() {return passowrd; }


    private String name, email, passowrd, age, gender;

}
