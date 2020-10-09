package com.example.dietapp;

public class admin1 {

    private String Name, Age, Height, Gender, Email, Password, Mobile_Number;



    public admin1() {

    }

    public admin1(String name, String age, String height, String gender, String email, String password, String mobile_Number) {
        Name = name;
        Age = age;
        Height = height;
        Gender = gender;
        Email = email;
        Password = password;
        Mobile_Number = mobile_Number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getMobile_Number() {
        return Mobile_Number;
    }

    public void setMobile_Number(String mobile_Number) {
        Mobile_Number = mobile_Number;
    }
}