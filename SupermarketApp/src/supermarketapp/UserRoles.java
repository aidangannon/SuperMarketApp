/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketapp;

import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JTextArea;

/**
 *
 * @author User
 */
public interface UserRoles {
    public abstract void ViewProductDetails(String ID,ProductList Products,JTextArea src);
    public abstract void ViewProducts(ProductList Products,DefaultListModel src);
    public abstract void AddProduct(String ID, String Name, String Price, String Weight,
            String ExpiryDate, String ImageFile, String Category,
            String MaximumLevelStock, String MinimumLevelStock,
            ProductList Products,String SupplierName,String SupplierDeliveryPolicy,
            String SupplierPrice);
    public abstract void ViewProductsForOrdering(ProductList Products,DefaultListModel src);
    public abstract Date Order(String ID,ProductList Products,String Ammount);
    public abstract void ViewProductDetailsForOrdering(String ID,ProductList Products,JTextArea src);
    public abstract Product ViewProductDetailsForEdit(String ID,ProductList Products);
    public abstract void EditProduct(String ID, String Name, String Price, String Weight,
            String ExpiryDate, String ImageFile, String Category,
            String MaximumLevelStock, String MinimumLevelStock,
            ProductList Products,String SupplierName,String SupplierDeliveryPolicy,
            String SupplierPrice);
}
