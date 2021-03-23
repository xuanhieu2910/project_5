package xuanhieu.project3.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xuanhieu.project3.dao.ProductsDao;
import xuanhieu.project3.entity.*;
import xuanhieu.project3.service.BranchInventoryService;
import xuanhieu.project3.service.InventoryService;
import xuanhieu.project3.service.ProductsService;
import xuanhieu.project3.service.SupplierService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {

    public static List<Products> productsList;
    public static Inventory inventory;
    public static Supplier supplier;
    public static Date dateModifiedOn;
    public static final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    private static List<Inventory> inventories;
    private static List<Inventory>getInventories;
    @Autowired
    SupplierService supplierService;

    @Autowired
    ProductsDao productsDao;

    @Autowired
    BranchInventoryService branchInventoryService;


    @Autowired
    InventoryService inventoryService;


    private boolean checkBranchInventory(List<Inventory> inventoryList) {
        for (int i = 0; i < inventoryList.size(); i++) {
            int id = inventoryList.get(i).getBranchInventory().getIdBranchInventory();
            if (branchInventoryService.findBranchInventoryById(id) != null) {
                return true;
            }
        }
        return false;
    }

    private boolean checkQuantity(Products products) {
        List<Inventory> inventoryList = products.getInventories();
        int quantity = 0;
        for (int i = 0; i < inventoryList.size(); i++) {
            quantity += inventoryList.get(i).getTotalInventory();
        }
        if (quantity > products.getQuantity()) {
            return false;
        }
        return true;
    }

    @Override
    public Products saveProducts(Products products) {
        if (checkBranchInventory(products.getInventories())&&checkQuantity(products)) {
            inventories = products.getInventories();
            getInventories = new ArrayList<>();
            for (int i = 0; i < products.getInventories().size(); i++) {
                inventory = new Inventory();
                inventory.setCreatedOn(products.getCreatedOn());
                inventory.setModifiedOn(products.getCreatedOn());
                inventory.setStatus(products.getStatusProduct());
                inventory.setExpiryDate(products.getExpirationDate());
                inventory.setTotalSales(inventories.get(i).getTotalSales());
                inventory.setTotalInventory(inventories.get(i).getTotalInventory());
                inventory.setProducts(products);
                inventory.setBranchInventory(inventories.get(i).getBranchInventory());
                getInventories.add(inventory);
            }
            products.setInventories(getInventories);
            productsList = new ArrayList<>();
            productsList.add(products);
            supplier = products.getSupplier();
            supplier.setProductsList(productsList);
            products.setSupplier(supplier);
            supplierService.saveSupplier(supplier);
            return products;
        }
        return productsDao.saveProducts(products);
    }


    @Override
    public Products findProductById(Integer id) {
        return productsDao.findProductById(id);
    }

    @Override
    public List<Products> findProductByName(String name) {
        return productsDao.findProductByName(name);
    }

    @Override
    public List<Products> findAllProducts() {
        return productsDao.findAllProducts();
    }

    @Override
    public Products updateProducts(Products products) {
        dateModifiedOn = new Date();
        String modifiedOn = format.format(dateModifiedOn);
            products.setModifiedOn(modifiedOn);
        return productsDao.updateProducts(products);
    }

    @Override
    public void deleteProductById(Integer id) {
        productsDao.deleteProductById(id);
    }
}
