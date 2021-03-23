package xuanhieu.project3.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInventory;

    @Column(name = "total_inventory", nullable = false)
    private Integer totalInventory;
    @Column(name = "total_sales", nullable = false)
    private Integer totalSales;

    @Column(name = "created_on", nullable = false, length = 255)
    private String createdOn;

    @Column(name = "modified_on",nullable = false)
    private String modifiedOn;


    @Column(name = "expiry_date",length = 255,nullable = false)
    private String expiryDate;

    @Column
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_branchInventory")
    private BranchInventory branchInventory;



    @ManyToOne
    @JoinColumn(name = "id_product")
    private Products products;

    public Inventory() {
    }

    public Inventory(Integer totalInventory, Integer totalSales, String createdOn, String modifiedOn, String expiryDate, String status) {
        this.totalInventory = totalInventory;
        this.totalSales = totalSales;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.expiryDate = expiryDate;
        this.status = status;
    }

    public Integer getIdInventory() {
        return idInventory;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getNameProducts() {
        return products.getNameProduct();
    }

    public Integer getTotalInventory() {
        return totalInventory;
    }

    public Integer getTotalSales() {
        return totalSales;
    }

    public BranchInventory getBranchInventory() {
        return branchInventory;
    }

    public void setBranchInventory(BranchInventory branchInventory) {
        this.branchInventory = branchInventory;
    }

    public void setProducts(Products products) {
        this.products = products;
    }



    public void setTotalInventory(Integer totalInventory) {
        this.totalInventory = totalInventory;
    }

    public void setTotalSales(Integer totalSales) {
        this.totalSales = totalSales;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
