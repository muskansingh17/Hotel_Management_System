package com.developer.Hotel_Management_System.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="billing")
public class Billing {
   @Id
   int customer_id;    
   String customer_name;
   int total_bill;
   String status;

   
   public Billing(int customer_id, String customer_name, int total_bill, String status) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.total_bill = total_bill;
        this.status = status;
    }
    
     
   public Billing()
   {
   
   }
   

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public int getTotal_bill() {
        return total_bill;
    }

    public void setTotal_bill(int total_bill) {
        this.total_bill = total_bill;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
   
   
   
}
