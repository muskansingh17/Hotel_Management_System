package com.developer.Hotel_Management_System.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="Room_Info")
public class Room_Info {
   @Id
   int room_no;
   int room_capacity;
   String room_type;
   String desciption;
   int room_price;
   String status;

    public Room_Info(int room_no, int room_capacity, String room_type, String desciption, int room_price, String status) {
        this.room_no = room_no;
        this.room_capacity = room_capacity;
        this.room_type = room_type;
        this.desciption = desciption;
        this.room_price = room_price;
        this.status = status;
    }

    

    public Room_Info()
    {
    
    }

    public int getRoom_no() {
        return room_no;
    }

    public void setRoom_no(int room_no) {
        this.room_no = room_no;
    }

    public int getRoom_capacity() {
        return room_capacity;
    }

    public void setRoom_capacity(int room_capacity) {
        this.room_capacity = room_capacity;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public int getRoom_price() {
        return room_price;
    }

    public void setRoom_price(int room_price) {
        this.room_price = room_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
   
  
  
   
   
   
}