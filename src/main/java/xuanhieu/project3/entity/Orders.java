package xuanhieu.project3.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.*;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    private Integer id;
    @Column(name = "created_on", nullable = false)
    private String createdOn;
    @Column(name = "total_quantity")
    private Integer totalQuantity;
    @Column(name = "total_price")
    private Float totalPrice;
    @Column(name = "status")
    private String status;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;


    @OneToMany(
            mappedBy = "orders",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<OrderDetails> orderDetailsList = new ArrayList<>();


    public Orders() {
    }

    public Orders(Integer id, String createdOn, Integer totalQuantity, Float totalPrice, String status) {
        this.id = id;
        this.createdOn = createdOn;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getId() {
        return id;
    }


    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }


    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderDetails> getOrderDetailsList() {
        return orderDetailsList;
    }

    public void setOrderDetailsList(List<OrderDetails> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
