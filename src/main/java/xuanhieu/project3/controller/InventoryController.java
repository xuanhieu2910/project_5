package xuanhieu.project3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xuanhieu.project3.entity.Inventory;
import xuanhieu.project3.service.InventoryService;

import java.util.*;
@RestController
@RequestMapping(value = "/inventorys")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping("/")
    public List<Inventory>getAllInventoryAllBranch(){
        return  inventoryService.findAllInventory();
    }
//    @GetMapping("/")
//    public List<Inventory>getAllInventoryByBranch(@PathVariable("idBranch")Integer id){
//        return null;
//    }



}

