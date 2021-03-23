package xuanhieu.project3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xuanhieu.project3.entity.Supplier;
import xuanhieu.project3.service.SupplierService;
import java.util.*;
@RestController
@RequestMapping(value = "/suppliers")
public class SupplierController {

    @Autowired
    SupplierService supplierService;



    @GetMapping("/")
    public List<Supplier>getAllSupplier(){
        return supplierService.findAllSupplier();
    }

    @PostMapping("/create-supplier")
    public Supplier saveSupplier(@RequestBody Supplier supplier){
        return  supplierService.saveSupplier(supplier);
    }


    @PutMapping("/update-supplier")
    public Supplier updateSupplierById(@PathVariable("id")Integer id,@RequestBody Supplier supplier){
        if(supplierService.findSupplierByIdSupplier(id)!=null){
            return supplierService.updateSupplier(id,supplier);
        }
        else{
            return null;
        }
    }

    @DeleteMapping("/delete-supplier")
    public String deleteSupplierById(@PathVariable("id")Integer id){
        supplierService.deleteSupplierById(id);
        return "Xóa thành công!";
    }
}
