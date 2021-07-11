package com.example.classmatesmemories_xxd_35_20191213.bean;

public class PersonInfo {
    private long id_xxd;
    private String name_xxd;
    private String gender_xxd;
    private String address_xxd;
    private String phone_xxd;
    private String QQ_xxd;
    private String email_xxd;

    public PersonInfo(Long id_xxd,String name_xxd, String gender_xxd, String address_xxd, String phone_xxd, String QQ_xxd, String email_xxd) {

        this.id_xxd = id_xxd;
        this.name_xxd = name_xxd;
        this.gender_xxd = gender_xxd;
        this.address_xxd = address_xxd;
        this.phone_xxd = phone_xxd;
        this.QQ_xxd = QQ_xxd;
        this.email_xxd = email_xxd;
    }

    public PersonInfo(String name_xxd, String gender_xxd, String address_xxd, String phone_xxd, String QQ_xxd, String email_xxd) {
        this.name_xxd = name_xxd;
        this.gender_xxd = gender_xxd;
        this.address_xxd = address_xxd;
        this.phone_xxd = phone_xxd;
        this.QQ_xxd = QQ_xxd;
        this.email_xxd = email_xxd;
    }




    public long getId_xxd() {
        return id_xxd;
    }

    public void setId_xxd(long id_xxd) {
        this.id_xxd = id_xxd;
    }

    public String getName_xxd() {
        return name_xxd;
    }

    public void setName_xxd(String name_xxd) {
        this.name_xxd = name_xxd;
    }

    public String getGender_xxd() {
        return gender_xxd;
    }

    public void setGender_xxd(String gender_xxd) {
        this.gender_xxd = gender_xxd;
    }

    public String getAddress_xxd() {
        return address_xxd;
    }

    public void setAddress_xxd(String address_xxd) {
        this.address_xxd = address_xxd;
    }

    public String getPhone_xxd() {
        return phone_xxd;
    }

    public void setPhone_xxd(String phone_xxd) {
        this.phone_xxd = phone_xxd;
    }

    public String getQQ_xxd() {
        return QQ_xxd;
    }

    public void setQQ_xxd(String QQ_xxd) {
        this.QQ_xxd = QQ_xxd;
    }

    public String getEmail_xxd() {
        return email_xxd;
    }

    public void setEmail_xxd(String email_xxd) {
        this.email_xxd = email_xxd;
    }

    public String toString(){return "[序号: " + id_xxd + ", 姓名: " + name_xxd + ", 性别: " + gender_xxd + ", qq: " + QQ_xxd + ", 手机号码: " + phone_xxd + ", 电子邮件: " + email_xxd + ", 地址: " + address_xxd + "]";}
}
