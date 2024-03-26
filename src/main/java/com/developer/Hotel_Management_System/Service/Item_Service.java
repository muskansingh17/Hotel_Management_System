package com.developer.Hotel_Management_System.Service;

import com.developer.Hotel_Management_System.Entity.Items;
import com.developer.Hotel_Management_System.Entity.User_Info;
import com.developer.Hotel_Management_System.Repository.Item_Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Item_Service {

    @Autowired
    private Item_Repository repository;

    public List<Items> getAllItems() {
        List<Items> lst = new ArrayList<>();

        try {
            repository.findAll().forEach(lst::add);
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            lst.clear();
            return lst;
        }

    }

    public boolean addItems(Items items) {
        try {
            repository.save(items);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public Items getItem(String item_id) {
        try {
            Optional<Items> opt
                    = repository.findById(item_id);

            if (opt.isPresent()) {
                Items item = opt.get();
                return item;
            } else {
                return null;
            }
        } catch (Exception exc) {
            exc.printStackTrace();
            return null;
        }

    }
    
    public boolean updateItem(Items items)
    {
        try {
            Optional<Items> opt
                    = repository.findById(items.getItem_id());

            if (opt.isPresent()) {
                repository.save(items);
                return true;
                
               
            } else {
                return false;
            }
        } catch (Exception exc) {
            exc.printStackTrace();
            return false;
        }
      
       
    
    }

}
