package xuanhieu.project3.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xuanhieu.project3.dao.OrdersDao;
import xuanhieu.project3.entity.Inventory;
import xuanhieu.project3.entity.OrderDetails;
import xuanhieu.project3.entity.Orders;
import xuanhieu.project3.repository.OrdersRepository;
import xuanhieu.project3.service.InventoryService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderDaoImpl implements OrdersDao {
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    InventoryService inventoryService;

    @Override
    public List<Orders> findAllOrders() {
        return ordersRepository.findAll();
    }

    @Override
    public Orders findOrdersById(Integer id) {
        Optional<Orders>orders = ordersRepository.findById(id);
        if(orders.isPresent()){
            return orders.get();
        }
        return null;
    }

    private void updateInventory(List<OrderDetails> orderDetailsLis, List<Integer> integers) {
        for (int i = 0; i < orderDetailsLis.size(); i++) {
            Integer id = orderDetailsLis.get(i).getProductsDetails().getIdProduct();
            List<Inventory> inventories = inventoryService.findInventoryByIdProduct(id);
            for (int j = 0; j < inventories.size(); j++) {
                if (inventories.get(i).getIdInventory().equals(integers.get(i))) {
                    Inventory inventory = inventories.get(i);
                    inventory.setTotalSales(inventory.getTotalSales() - orderDetailsLis.get(i).getQuantity());
                    inventoryService.saveInventory(inventory);
                }
            }
        }
    }

    // Check thời gian, ưu tiên sản phẩm nhập trước
    private List<Integer> checkTimeProductIntoInventory(List<OrderDetails> orderDetails) throws ParseException {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < orderDetails.size(); i++) {
            int id = orderDetails.get(i).getProductsDetails().getIdProduct();
            List<Inventory> inventoriesList = inventoryService.findInventoryByIdProduct(id);
            Date date = simpleDateFormat.parse(inventoriesList.get(0).getCreatedOn());
            for (int j = 1; j < inventoriesList.size(); i++) {
                Date date1 = simpleDateFormat.parse(inventoriesList.get(j).getCreatedOn());
                if (date.compareTo(date1) > 0) {
                    date = date1;
                }
            }
            String minTime = simpleDateFormat.format(date);
            for (int h = 0; h < inventoriesList.size(); h++) {
                if (minTime.equals(inventoriesList.get(i).getCreatedOn())) {
                    list.add(inventoriesList.get(i).getIdInventory());
                }
            }
        }
        return list;
    }
    @Override
    public Orders saveOrders(Orders orders) {
        try {
            updateInventory(orders.getOrderDetailsList(), checkTimeProductIntoInventory(orders.getOrderDetailsList()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return   ordersRepository.save(orders);
    }

    @Override
    public String deleteOrdersById(Integer id) {
        Optional<Orders>orders = ordersRepository.findById(id);
        if(orders.isPresent()){
            return "Xoa thanh cong!";
        }
        return null;
    }
}
