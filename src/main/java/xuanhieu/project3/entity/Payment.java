package xuanhieu.project3.entity;

import javax.persistence.*;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    private Integer idPayment;
    @Column(name = "name_payment",nullable = false,length = 255)
    private String namePayment;
    @Column(name = "enable",nullable = false)
    private Integer enable;
    @Column(name = "created_on",nullable = false,length = 255)
    private String createdOn;
    @Column(name = "modified_on",nullable = false,length = 255)
    private String modifiedOn;

    @OneToOne(
            mappedBy = "payment",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Orders orders;


    public Payment() {
    }

    public Payment(Integer idPayment, String namePayment, Integer enable,
                   String createdOn, String modifiedOn) {
        this.idPayment = idPayment;
        this.namePayment = namePayment;
        this.enable = enable;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
    }

    public Integer getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(Integer idPayment) {
        this.idPayment = idPayment;
    }

    public String getNamePayment() {
        return namePayment;
    }

    public void setNamePayment(String namePayment) {
        this.namePayment = namePayment;
    }


    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
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

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
