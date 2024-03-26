package com.developer.Hotel_Management_System.Controller;

import com.developer.Hotel_Management_System.Entity.Items;
import com.developer.Hotel_Management_System.Entity.User_Info;
import com.developer.Hotel_Management_System.Repository.Item_Repository;
import com.developer.Hotel_Management_System.Service.Item_Service;
import com.developer.Hotel_Management_System.Service.Order_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Item_Controller {

    @Autowired
    private Item_Service service;

    @Autowired
    private Order_Service order_service;

    // URL:  http://localhost:5082/items/new_item
    @GetMapping("/items/new_item")
    public String createItemForm(Model model) {
        Items items = new Items();
        model.addAttribute("items", items);
        return "add_item";

    }

    // URL:  http://localhost:5082/items/save_item
    @PostMapping("/items/save_item")
    public String saveItem(@ModelAttribute("items") Items items) {
        try {
            service.addItems(items);
            return "item_saved";
        } catch (Exception e) {
            e.printStackTrace();
            return "item_not_saved";
        }

    }

    // URL:  http://localhost:5082/items/getAllItems
    @GetMapping("/items/getAllItems")
    public String getAll(Model model) {
        model.addAttribute("menu", service.getAllItems());
        return "place_order";
    }

}
