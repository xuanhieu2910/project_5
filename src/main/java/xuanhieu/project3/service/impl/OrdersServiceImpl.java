package xuanhieu.project3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xuanhieu.project3.dao.OrdersDao;
import xuanhieu.project3.entity.*;
import xuanhieu.project3.service.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    OrdersDao ordersDao;

    @Autowired
    InventoryService inventoryService;

    @Autowired
    ProductsService productsService;

    @Autowired
    OrdersService ordersService;

    @Autowired
    CustomerService customerService;


    @Autowired
    PaymentService paymentService;

    public static Payment payment;
    public static List<OrderDetails> orderDetailsList;
    public static OrderDetails orderDetails;
    public static Integer idProduct;
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public static Date dateOrder;
    public static Integer idOrder;
    public static int size;
    public static int sizeOrder;


    @Override
    public List<Orders> findAllOrders() {
        return ordersDao.findAllOrders();
    }

    @Override
    public Orders findOrdersById(Integer id) {
        return ordersDao.findOrdersById(id);
    }


    @Override
    public String deleteOrdersById(Integer id) {
        if (ordersDao.deleteOrdersById(id) == null) {
            return "Xoa that bai";
        }
        return "Xoa thanh cong!";
    }


    // Bat dau lam ban nayf

    // Check xem sản phẩm đó có trong kho không
    private boolean checkProductInventory(List<OrderDetails> listOrderDetails) {
        System.out.println(listOrderDetails.size());
        for (int i = 0; i < listOrderDetails.size(); i++) {
            Integer id = listOrderDetails.get(i).getProductsDetails().getIdProduct();
            if (productsService.findProductById(id) == null) {
                return false;
            }
        }
        return true;
    }

    // Check số lượng trong kho xem còn không?
    private boolean checkQuantityInventoryWithIdProduct(List<OrderDetails> orderDetails) {
        int totalOrderDetails;
        int totalQuantityInventory = 0;
        for (int i = 0; i < orderDetails.size(); i++) {
            int id = orderDetails.get(i).getProductsDetails().getIdProduct();
            totalOrderDetails = orderDetails.get(i).getQuantity();
            List<Inventory> inventories = inventoryService.findInventoryByIdProduct(id);
            for (int j = 0; j < inventories.size(); j++) {
                totalQuantityInventory += inventories.get(i).getTotalSales();
            }
            if (totalOrderDetails > totalQuantityInventory) {
                return false;
            }

        }
        return true;
    }

    // Tổng thành tiền trong đơn hàng
    private float priceOfOrder(List<OrderDetails> orderDetails) {

        float priceOfOrder = 0;

        for (int i = 0; i < orderDetails.size(); i++) {
            int idProduct = orderDetails.get(i).getProductsDetails().getIdProduct();
            priceOfOrder += productsService.findProductById(idProduct).getPrice() * orderDetails.get(i).getQuantity();
        }
        return priceOfOrder;
    }

    // Lấy ra tổng số lượng trong đơn hàng
    private int quantityOrder(List<OrderDetails> orderDetails) {
        int quantity = 0;

        for (int i = 0; i < orderDetails.size(); i++) {
            quantity += orderDetails.get(i).getQuantity();
        }
        return quantity;
    }



    // Check thanh toán cho đơn hàng
    private boolean checkPayment(Payment paymentOrder) {
        if (paymentOrder.getEnable() == 1) {
            return true;
        }
        return false;
    }



    @Override
    public Orders saveOrders(Orders orders) {
        sizeOrder = orders.getOrderDetailsList().size();

        if (checkProductInventory(orders.getOrderDetailsList()) && checkQuantityInventoryWithIdProduct(orders.getOrderDetailsList())) {
            System.out.println("HAHAHJA");
            dateOrder = new Date();
            String date = simpleDateFormat.format(dateOrder);
            orders.setCreatedOn(date);
            orders.setPayment(orders.getPayment());
            orders.setCustomer(orders.getCustomer());
            orders.setTotalPrice(priceOfOrder(orders.getOrderDetailsList()));
            orders.setTotalQuantity(quantityOrder(orders.getOrderDetailsList()));
            orderDetailsList = new ArrayList<>();
            for (int i = 0; i < sizeOrder; i++) {
                orderDetails = orders.getOrderDetailsList().get(i);
                orderDetails.setOrders(orders);
                orderDetailsList.add(orderDetails);
            }
            paymentService.savePayment(orders.getPayment());
            customerService.saveCustomer(orders.getCustomer());
            orders.setOrderDetailsList(orderDetailsList);
            return ordersDao.saveOrders(orders);
        }

        return null;
    }
}
