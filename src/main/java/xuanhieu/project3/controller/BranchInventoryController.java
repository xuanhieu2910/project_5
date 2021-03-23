package xuanhieu.project3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xuanhieu.project3.entity.BranchInventory;
import xuanhieu.project3.service.BranchInventoryService;


import java.util.*;
@RestController
@RequestMapping(value = "/branchs")
public class BranchInventoryController {

    @Autowired
    BranchInventoryService branchInventoryService;

    @GetMapping(value = "/")
    public List<BranchInventory>getAllInventory(){
        return branchInventoryService.findAllBranchInventory();
    }

    @PostMapping(value = "/create-branch")
    public BranchInventory saveBranchInventory(@RequestBody BranchInventory branchInventory){
        return branchInventoryService.saveBranchInventory(branchInventory);
    }


    @GetMapping(value = "/branch-inventory/{id}")
    public BranchInventory getBranchInventoryById(@PathVariable("id")Integer id){
        return branchInventoryService.findBranchInventoryById(id);
    }

    @PutMapping(value = "/update/{id}")
    public BranchInventory updateBranchInventoryById(@PathVariable("id")Integer id,@RequestBody BranchInventory branchInventory){
        if(branchInventoryService.findBranchInventoryById(id)!=null){
           return  branchInventoryService.updateBranchInventory(branchInventory);
        }
        else{
            System.out.println("Chưa có chi nhánh!");
            return null;
        }
    }
    @DeleteMapping(value = "/delete/{id}")
    public String deleteBranchInventoryById(@PathVariable("id")Integer id){
        if(branchInventoryService.deleteBranchInventoryById(id).equals("Xóa thành công!")){
           return branchInventoryService.deleteBranchInventoryById(id);
        }
        else{
            return "Xóa thất bại";
        }
    }

}
