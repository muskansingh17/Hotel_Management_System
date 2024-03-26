package com.developer.Hotel_Management_System.Controller;

import com.developer.Hotel_Management_System.Entity.Billing;
import com.developer.Hotel_Management_System.Entity.Booking_Rooms;
import com.developer.Hotel_Management_System.Entity.Room_Info;
import com.developer.Hotel_Management_System.Entity.User_Info;
import com.developer.Hotel_Management_System.Repository.Booking_Repository;
import com.developer.Hotel_Management_System.Service.Billing_Service;
import com.developer.Hotel_Management_System.Service.Room_Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Manager_Controller {

    @Autowired
    private Room_Service room_service;
    
    @Autowired
    private Billing_Service billing_service;

    // URL:  http://localhost:5082/available_rooms
    @GetMapping("/available_rooms")
    public String getAllAvailableRooms(Model model) {
        model.addAttribute("room_info", room_service.getAllAvailableRoom());
        return "room_details";
    }

    // URL:  http://localhost:5082/get_all_rooms
    @GetMapping("/get_all_rooms")
    public String getAllRooms(Model model) {
        model.addAttribute("room_info", room_service.getAllRoom());
        return "all_rooms";
    }

    // URL:  http://localhost:5082/book_rooms_/101
    @GetMapping("/book_rooms/{room_no}")
    public String bookRooms(@PathVariable("room_no") String room_id1, Model model) {
        int room_no = Integer.parseInt(room_id1);
        model.addAttribute("room_info", room_service.getRoomInfo(room_no));
        return "booking_status";

    }

    // URL:  http://localhost:5082/final_check/101
    @PostMapping("/book_rooms/final_check/{room_no}")
    public String bookRoomFinalCheck(@PathVariable("room_no") int room_id1,
            @RequestParam("customer_id") String customer_id1,
            @RequestParam("no_of_days") String days) {
        int room_no = room_id1;
        int customer_id = Integer.parseInt(customer_id1);
        int no_of_days = Integer.parseInt(days);

        System.out.println("Room ID: " + room_no);
        System.out.println("Customer ID: " + customer_id);

        boolean status = room_service.book_rooms(room_no, customer_id, no_of_days);

        if (status) {
            return "booking_success";
        } else {
            return "booking_error";
        }
    }

    // URL:  http://localhost:5082/check_out/101
    @GetMapping("/book_rooms/check_out/{room_no}")
    public String check_out(@PathVariable("room_no") String room_id1, Model model) {
        int room_no = Integer.parseInt(room_id1);

        boolean status = room_service.check_out(room_no);

        if (status) {
            return "customer_check_out_successfull";
        } else {
            return "customer_check_out_error";
        }

    }

    // URL:  http://localhost:5082/customer/view_room/2055
    @GetMapping("/customer/view_room/{customer_id}")
    public String view_room(@PathVariable("customer_id") String customer_id1, Model model) {
        System.out.println("Customer Id" + customer_id1);
        int customer_id = Integer.parseInt(customer_id1);
        model.addAttribute("room_info", room_service.getCustomerRoomDetails(customer_id));
        return "customer_room";

    }

    
    
    // URL:  http://localhost:5082/manager/login
    @GetMapping("/manager/login")
    public String manager_login() {
        
        return "manager_login";

    }
    
    
    
    
    // URL:  http://localhost:5082/manager/login/check_credential
    @GetMapping("/manager/login/check_credential")
    public String manager_login(@RequestParam("manager_id") String manager_id, 
                                @RequestParam("password") String password) {
        if(manager_id.equals("admin")&& password.equals("admin"))
        {
         return "manager_dashboard";
        
        }
        else{
        
            return "login_failed";
            
        }
    }
    
    
     // URL:  http://localhost:5082/manager/billing
    @GetMapping("/manager/billing")
    public String getBillingDetails(Model model) {
        
        List<Billing> bills=billing_service.viewBillings();
        int total=0;
        for(Billing bill:bills)
        {
          total=total+bill.getTotal_bill();
          
        }
        
        model.addAttribute("bills", bills);
        model.addAttribute("total",total);
        return "manager_billing";
    }
    
    
    
    

    
    
    
}
