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
public class Employee extends User{
    public Employee(String UserName,String Password){
        super(UserName,Password);
    }
    public Employee(){
        super();
    }

    @Override
    public void ViewProductDetails(String ID,ProductList Products, JTextArea src) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void ViewProducts(ProductList Products, DefaultListModel src) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Date Order(String ID, ProductList Products, String Ammount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ViewProductDetailsForOrdering(String ID, ProductList Products, JTextArea src) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void EditProduct(String ID, String Name, String Price, String Weight, String ExpiryDate, String ImageFile, String Category, String MaximumLevelStock, String MinimumLevelStock, ProductList Products, String SupplierName, String SupplierDeliveryPolicy, String SupplierPrice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product ViewProductDetailsForEdit(String ID,ProductList Products) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
