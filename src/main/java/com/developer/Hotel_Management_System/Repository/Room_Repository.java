
package com.developer.Hotel_Management_System.Repository;

import com.developer.Hotel_Management_System.Entity.Room_Info;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface Room_Repository extends CrudRepository<Room_Info, Integer>{
    @Query(value = "FROM Room_Info p WHERE p.status='Available' ")
	List<Room_Info> getAvailableRoomInfo();
  
          @Query(value = "FROM Room_Info p WHERE p.room_no=:room_no ")
	List<Room_Info> getRoomDetails(@Param("room_no") Integer room_no);
    
}