package com.developer.Hotel_Management_System.Service;

import com.developer.Hotel_Management_System.Entity.Items;
import com.developer.Hotel_Management_System.Entity.Orders;
import com.developer.Hotel_Management_System.Repository.Item_Repository;
import com.developer.Hotel_Management_System.Repository.Order_Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Order_Service {
    
    @Autowired
    private Order_Repository repository;
   
    @Autowired
    private Item_Repository repo;
    
        public List<Orders> customer_order(int customer_id)
        { 
           List<Orders> lst=repository.getAllCustomerOrder(customer_id);
           if(lst.size()>0)
           {
             return lst;
           }
           else
           {
             return null;
           }
        }
        
        public boolean placeOrder(int customer_id,String item_id,int qty)
        {
            Optional<Items> opt=repo.findById(item_id);
            Items item=opt.get();
            if(opt.isPresent())
            {
            
            Orders order=new Orders();
            order.setCustomer_id(customer_id);
            order.setItem_id(item_id);
            order.setItem_name(item.getItem_name());
            order.setQuantity(qty);
            order.setPrice(item.getItem_price());
             
            int total=(item.getItem_price()*qty);
            order.setTotal(total);
            repository.save(order);
            return true;
            }
            else{
              return false;
            }
            
        
            
            
        }
      
      
}
