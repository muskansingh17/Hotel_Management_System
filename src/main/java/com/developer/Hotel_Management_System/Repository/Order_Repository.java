package com.developer.Hotel_Management_System.Repository;

import com.developer.Hotel_Management_System.Entity.Orders;
import com.developer.Hotel_Management_System.Entity.Room_Info;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface Order_Repository extends CrudRepository<Orders, Integer> {
         @Query(value = "FROM Orders p WHERE p.customer_id=:id ")
	List<Orders> getAllCustomerOrder(@Param("id") Integer id);
}
