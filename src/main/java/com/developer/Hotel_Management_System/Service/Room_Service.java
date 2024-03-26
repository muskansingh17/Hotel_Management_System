package com.developer.Hotel_Management_System.Service;

import com.developer.Hotel_Management_System.Entity.Booking_Rooms;
import com.developer.Hotel_Management_System.Entity.Orders;
import com.developer.Hotel_Management_System.Entity.Room_Info;
import com.developer.Hotel_Management_System.Repository.Booking_Repository;
import com.developer.Hotel_Management_System.Repository.Order_Repository;
import com.developer.Hotel_Management_System.Repository.Room_Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Room_Service {
    
    @Autowired
    private Room_Repository repository;
    
   @Autowired
    private Booking_Repository book_repo;
    
    @Autowired
    private Order_Repository order_repository;
   
   
    
    public List<Room_Info> getAllRoom() {
        List<Room_Info> lst = new ArrayList<>();

        try {
            repository.findAll().forEach(lst::add);
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            lst.clear();
            return lst;
        }

    }
    
    public List<Room_Info> getAllAvailableRoom() {
        List<Room_Info> lst = new ArrayList<>();

        try {
          lst=repository.getAvailableRoomInfo();
         
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            lst.clear();
            return lst;
        }

    }
    
    public boolean book_rooms(int room_id,int customer_id,int no_of_days)            // Book the room and set final room details for billling
    {
        try{
        Booking_Rooms room=new Booking_Rooms();
        room.setCustomer_id(customer_id);
        room.setRoom_id(room_id);
        
        String room_id1=room_id+"";
        
        
        Optional <Room_Info> opt=repository.findById(room_id);
        
         Room_Info room_info=opt.get();       // Set Room Information for final biiling in orders
         
         Orders order=new Orders();
         order.setCustomer_id(customer_id);
         order.setItem_id(room_id1);
         order.setItem_name(room_info.getDesciption());
         order.setPrice(room_info.getRoom_price());
         order.setQuantity(no_of_days);
         int total=(room_info.getRoom_price())*no_of_days;
         order.setTotal(total);
         
         room_info.setStatus("Not Available");
         repository.save(room_info);
       
       book_repo.save(room);
       order_repository.save(order);
       
        return true;
        
        }catch(Exception e)
        {
        e.printStackTrace();
        return false;
        }
    
    }
    
    
     public Room_Info getRoomInfo(int id)
     {
            try {
            Optional<Room_Info> opt
                    = repository.findById(id);

            if (opt.isPresent()) {
                Room_Info room = opt.get();
                return room;
            } else {
                return null;
            }
        } catch (Exception exc) {
            exc.printStackTrace();
            return null;
        }
     
     }
     
     
      public boolean check_out(int id)
     {
            try {
            Optional<Room_Info> opt
                    = repository.findById(id);

            
            if (opt.isPresent()) {
                Room_Info room = opt.get();
                Booking_Rooms room_detail=book_repo.getDetails(id);
                
                
                
                room.setStatus("Available");
                
                book_repo.delete(room_detail);
                repository.save(room);
                
                return true;
            } else {
                return false;
            }
        } catch (Exception exc) {
            exc.printStackTrace();
            return false;
        }
     
     }
      
      
       public List<Room_Info> getCustomerRoomDetails(int user_id) {
        List<Room_Info> lst = new ArrayList<>();

        try {
            int count=0;
           List<Booking_Rooms> rooms=book_repo.getCustomerRooms(user_id);
           for(Booking_Rooms p:rooms)
           {
              Optional<Room_Info> opt=repository.findById(p.getRoom_id());
               Room_Info room1=opt.get();
              lst.add(room1);
              
               System.out.println(count);
           
               count++;
           }
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            lst.clear();
            return lst;
        }

    }
    
      
      
      
     
     
     
    
  

    
    
}