package com.developer.Hotel_Management_System.Controller;

import com.developer.Hotel_Management_System.Entity.Orders;
import com.developer.Hotel_Management_System.Entity.User_Info;
import com.developer.Hotel_Management_System.Service.Order_Service;
import com.developer.Hotel_Management_System.Service.Room_Service;
import com.developer.Hotel_Management_System.Service.User_Service;
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
public class User_Controller {

    @Autowired
    private User_Service service;

    @Autowired
    private Room_Service room_service;

    @Autowired
    private Order_Service order_service;

    // URL:  http://localhost:5082/customer/new_customer
    @GetMapping("/customer/new_customer")
    public String createCustomerForm(Model model) {
        User_Info user = new User_Info();
        model.addAttribute("user", user);
        return "add_customer";

    }

    // URL:  http://localhost:5082/customer/saveCustomer
    @PostMapping("/customer/saveCustomer")
    public String saveCustomer(@ModelAttribute("user") User_Info user) {
        try {
            service.addUser(user);
            return "register_success";
        } catch (Exception e) {
            e.printStackTrace();
            return "register_error";
        }

    }
    

    // URL:  http://localhost:5082/customer/login
    @GetMapping("/customer/login")
    public String login(Model model) {
        User_Info user = new User_Info();
        model.addAttribute("user", user);
        return "user_login";

    }

    // URL:  http://localhost:5082/customer/check_credential
    @GetMapping("/customer/check_credential")
    public String check_user(@ModelAttribute("user") User_Info user, Model model) {
        try {

            User_Info user2 = service.getUser(user.getUser_id());

            String pass = user2.getPassword();

            if (pass.equals(user.getPassword())) {
                model.addAttribute("user", user2);
                return "user_dashboard";

            } else {
                return "login_failed";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "login_error";
        }

    }

    // URL:  http://localhost:5082/customer/getAllCustomer
    @GetMapping("/customer/getAllCustomer")
    public String getAllCustomer(Model model) {
        model.addAttribute("user_info", service.getAllUser());
        return "user_details";
    }

    // URL:  http://localhost:5082/FS201
    @GetMapping("/customer/{order_id}")
    public String newOrder(@RequestParam("user_id") String user_id1, @RequestParam("quantity") String quantity1,
            @PathVariable("order_id") String order_id1, Model model) {

        int user_id = Integer.parseInt(user_id1);
        String order_id = order_id1;
        int quantity = Integer.parseInt(quantity1);
        boolean status = order_service.placeOrder(user_id, order_id, quantity);
        if (status) {
            return "Order_Success";
        } else {
            return "Order_Failed";

        }

    }
    // URL:  http://localhost:5082/customer/dashboard

    @GetMapping("/customer/dashboard")
    public String view() {
        return "redirect:/customer/check_credential";
    }

    // URL:  http://localhost:5082/customer/view_bill/2055
    @GetMapping("/customer/view_bill/{customer_id}")
    public String newBill(@PathVariable("customer_id") int customer_id, Model model) {

        System.out.println("Customer Id" + customer_id);

        List<Orders> orders = order_service.customer_order(customer_id);

        if (orders == null) {
            return "No_Outstanding_Amount";
        }
        int total = 0;
        for (Orders o : orders) {
            total = total + o.getTotal();

        }

        model.addAttribute("orders", orders);
        model.addAttribute("total", total);
        model.addAttribute("customer_id", customer_id);
        return "view_bill";
    }

    // URL:  http://localhost:5082/customer/pay_bill/2055
    @GetMapping("/customer/pay_bill/{customer_id}")
    public String pay_bill(@PathVariable("customer_id") String customer_id1) {
        System.out.println("Customer Id" + customer_id1);
        int customer_id = Integer.parseInt(customer_id1);
        List<Orders> orders = order_service.customer_order(customer_id);

        int total = 0;
        for (Orders o : orders) {
            total = total + o.getTotal();

        }
        boolean status = service.payBill(customer_id, total);

        if (status) {
            return "bill_paid_success";

        } else {

            return "bill_paid_error";
        }
    }
    
    // URL:  http://localhost:5082/hotel/index
    @GetMapping("/hotel/index")
    public String main() {
        return "index";
       
    }

}
