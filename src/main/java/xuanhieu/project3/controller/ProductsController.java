package xuanhieu.project3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xuanhieu.project3.entity.*;
import xuanhieu.project3.service.ProductsService;

import java.util.*;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {

    @Autowired
    ProductsService productsService;


    @GetMapping
    public List<Products> findProductByNameProduct(@RequestParam(value = "name",defaultValue = "list")String name){
        if(!name.equals("list")){
            return (List<Products>) productsService.findProductByName(name);
        }
        return productsService.findAllProducts();
    }

    @PostMapping(value = "/create-product")
    public Products createProducts(@RequestBody Products products) {
        return productsService.saveProducts(products);
    }

    @PutMapping(value = "/update-product")
    public Products updateProduct(@PathVariable("id") Integer id, @RequestBody Products products) {
        if (productsService.findProductById(id) != null) {
            return productsService.updateProducts(products);
        } else {
            return null;
        }
    }


    @DeleteMapping("/delete-products")
    public String deleteProductById(@PathVariable("id") Integer id) {
        productsService.deleteProductById(id);
        return "Xóa thành công!";
    }

}
