package com.developer.Hotel_Management_System.Service;

import com.developer.Hotel_Management_System.Entity.Billing;
import com.developer.Hotel_Management_System.Entity.Items;
import com.developer.Hotel_Management_System.Repository.Billing_Repository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Billing_Service {
          
    @Autowired
    private Billing_Repository billing_repository;
    
    
    public List<Billing> viewBillings()
    {
     List<Billing> lst = new ArrayList<>();

        try {
            billing_repository.findAll().forEach(lst::add);
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            lst.clear();
            return lst;
        }
    
    }
}
