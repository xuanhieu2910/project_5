package xuanhieu.project3.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.*;
@Entity
@Table(name = "products")
public class Products  implements Serializable {

    @Id
    private Integer idProduct;
    @Column(name = "name_product", nullable = false, length = 250)
    private String nameProduct;
    @Column(name = "import_price", nullable = false)
    private Float importPrice;
    @Column(name = "wholesale_prices", nullable = false)
    private Float wholesalePrices;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Column(name = "price", nullable = false)
    private Float price;
    @Column(name = "status_product", nullable = false)
    private String statusProduct;
    @Column(name = "created_on", nullable = false)
    private String createdOn;
    @Column(name = "modified_on",nullable = false)
    private String modifiedOn;

    @Column(name = "date_of_manufacture", nullable = false, length = 255)
    private String dateOfManufacture;
    @Column(name = "expiration_date", nullable = false, length = 255)
    private String expirationDate;

    @Column(name = "date_delete")
    private String dateDelete;


    @OneToMany(
            mappedBy = "productsDetails",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<OrderDetails> orderDetails = new ArrayList<>();


    @OneToMany(
            mappedBy = "products",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Inventory> inventories = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_supplier")
    private Supplier supplier;



    public Products() {
    }

    public Products(String nameProduct, Float importPrice, Float wholesalePrices,
                    Integer quantity, Float price, String statusProduct, String createdOn,
                    String modifiedOn, String dateOfManufacture,
                    String expirationDate, String dateDelete) {
        this.nameProduct = nameProduct;
        this.importPrice = importPrice;
        this.wholesalePrices = wholesalePrices;
        this.quantity = quantity;
        this.price = price;
        this.statusProduct = statusProduct;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.dateOfManufacture = dateOfManufacture;
        this.expirationDate = expirationDate;
        this.dateDelete = dateDelete;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(Float importPrice) {
        this.importPrice = importPrice;
    }

    public Float getWholesalePrices() {
        return wholesalePrices;
    }

    public void setWholesalePrices(Float wholesalePrices) {
        this.wholesalePrices = wholesalePrices;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getStatusProduct() {
        return statusProduct;
    }

    public void setStatusProduct(String statusProduct) {
        this.statusProduct = statusProduct;
    }


    public String getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(String dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getDateDelete() {
        return dateDelete;
    }

    public void setDateDelete(String dateDelete) {
        this.dateDelete = dateDelete;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
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

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void setInventories(List<Inventory> inventories) {
        this.inventories = inventories;
    }



    public List<Inventory> getInventories() {
        return inventories;
    }

    @Override
    public String toString() {
        return "Products{" +
                "idProduct=" + idProduct +
                ", nameProduct='" + nameProduct + '\'' +
                ", importPrice=" + importPrice +
                ", wholesalePrices=" + wholesalePrices +
                ", quantity=" + quantity +
                ", price=" + price +
                ", statusProduct='" + statusProduct + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", modifiedOn='" + modifiedOn + '\'' +
                ", dateOfManufacture='" + dateOfManufacture + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", dateDelete='" + dateDelete + '\'' +
                '}';
    }
}