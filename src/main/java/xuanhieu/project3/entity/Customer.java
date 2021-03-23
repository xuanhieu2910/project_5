package xuanhieu.project3.entity;
import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "customer")
public class Customer {

    @Id
    private Integer idCustomer;
    @Column(name = "name_customer",nullable = false)
    private String nameCustomer;
    @Column(name = "phone_customer",nullable = false)
    private String phoneCustomer;
    @Column(name = "address_customer",nullable = false)
    private String addressCustomer;
    @Column(name = "created_on",nullable = false)
    private String createdOn;
    @Column(name = "modified_on",nullable = false)
    private String modifiedOn;


    @OneToOne(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Orders orders;


    public Customer() {
    }

    public Customer(Integer idCustomer, String nameCustomer,
                    String phoneCustomer, String addressCustomer,
                    String createdOn, String modifiedOn) {
        this.idCustomer = idCustomer;
        this.nameCustomer = nameCustomer;
        this.phoneCustomer = phoneCustomer;
        this.addressCustomer = addressCustomer;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public String getPhoneCustomer() {
        return phoneCustomer;
    }

    public String getAddressCustomer() {
        return addressCustomer;
    }


    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public void setPhoneCustomer(String phoneCustomer) {
        this.phoneCustomer = phoneCustomer;
    }

    public void setAddressCustomer(String addressCustomer) {
        this.addressCustomer = addressCustomer;
    }
//
//    public List<Orders> getOrders() {
//        return orders;
//    }

//
//    public Orders getOrders() {
//        return orders;
//    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
