package xuanhieu.project3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xuanhieu.project3.entity.Orders;
import xuanhieu.project3.service.OrdersService;

import java.util.*;
@RestController
@RequestMapping(value = "/orders")
public class OrdersController {


    @Autowired
    OrdersService ordersService;

    @GetMapping(value = "/")
    public List<Orders>getAllOrders(){
        return ordersService.findAllOrders();
    }

//    @GetMapping(value = "/")
//    public Orders findOrdersById(@PathVariable("id")Integer id){
//        return ordersService.findOrdersById(id);
//    }

    @PostMapping(value = "/create-orders")
    public Orders addOrders(@RequestBody Orders orders){
        return ordersService.saveOrders(orders);
    }

}
