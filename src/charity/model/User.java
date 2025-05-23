/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package charity.model;

import java.sql.Date;

/**
 *
 * @author phaml
 */
public class User {
    private int accountId;
    private int id;
    private String name;
    private String address;
    private String phone;
    private String gender;
    private Date birthday;

    public User() {
    }

    public User(int id, String name, String address, String phone, String gender, Date birthday) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.birthday = birthday;
    }

    public User(int accountId, int id, String name, String address, String phone, String gender, Date birthday) {
        this.accountId = accountId;
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.birthday = birthday;
    }

    public User(int accountId, String name, String address, String phone, Date birthday) {
        this.accountId = accountId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.birthday = birthday;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" + "accountId=" + accountId + ", id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", gender=" + gender + ", birthday=" + birthday + '}';
    }
    
    
}
