package com.developer.Hotel_Management_System.Entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_info")
public class User_Info {
   @Id
   int user_id;
   String sname;
   String address;
   int mobile_no;
   String password;

    public User_Info(int user_id, String sname, String address, int mobile_no, String password) {
        this.user_id = user_id;
        this.sname = sname;
        this.address = address;
        this.mobile_no = mobile_no;
        this.password = password;
    }

   

   
    
    public User_Info()
    {
    
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

   

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(int mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
   
   
   
         
}