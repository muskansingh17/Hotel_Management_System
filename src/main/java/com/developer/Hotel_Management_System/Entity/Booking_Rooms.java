package com.developer.Hotel_Management_System.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Room_Bookings")
public class Booking_Rooms {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO) 
   int booking_id; 
   @Column(unique = true)
   int room_id;
   int customer_id;

    public Booking_Rooms(int room_id, int customer_id) {
        super();
        this.room_id = room_id;
        this.customer_id = customer_id;
    }

   public Booking_Rooms()
   {
   }
   
   
    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
   
   
   
   
}
