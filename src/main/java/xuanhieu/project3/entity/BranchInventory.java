package xuanhieu.project3.entity;

import javax.persistence.*;
import java.util.*;
@Entity
@Table(name = "branch_inventory")
public class BranchInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBranchInventory;

    @Column(name = "name_branch",nullable = false,length = 255)
    private String nameBranchInventory;
    @Column(name = "create_on",nullable = false)
    private String createdOn;
    @Column(name = "modified_on",nullable = false)
    private String modifiedOn;
    @Column(name = "address_branch",nullable = false,length = 255)
    private String addressBranchInventory;

    @OneToMany(
            mappedBy = "branchInventory",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Inventory>inventories = new ArrayList<>();



    public BranchInventory() {
    }

    public BranchInventory(String nameBranchInventory, String createdOn, String modifiedOn, String addressBranchInventory) {
        this.nameBranchInventory = nameBranchInventory;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.addressBranchInventory = addressBranchInventory;
    }

    public Integer getIdBranchInventory() {
        return idBranchInventory;
    }

    public String getNameBranchInventory() {
        return nameBranchInventory;
    }

    public void setNameBranchInventory(String nameBranchInventory) {
        this.nameBranchInventory = nameBranchInventory;
    }

    public String getAddressBranchInventory() {
        return addressBranchInventory;
    }

    public void setAddressBranchInventory(String addressBranchInventory) {
        this.addressBranchInventory = addressBranchInventory;
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

    public void setInventories(List<Inventory> inventories) {
        this.inventories = inventories;
    }

}
