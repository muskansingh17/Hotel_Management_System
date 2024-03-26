/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.developer.Hotel_Management_System.Repository;

import com.developer.Hotel_Management_System.Entity.Booking_Rooms;
import com.developer.Hotel_Management_System.Entity.Orders;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author mehul
 */
public interface Booking_Repository extends CrudRepository<Booking_Rooms, Integer> {
        @Query(value = "FROM Booking_Rooms p WHERE p.room_id=:room_id ")
	Booking_Rooms  getDetails(@Param("room_id") Integer room_id);
        
          @Query(value = "FROM Booking_Rooms p WHERE p.customer_id=:id ")
	List<Booking_Rooms> getCustomerRooms(@Param("id") Integer id);
       
        
        
        
        
	
     }
