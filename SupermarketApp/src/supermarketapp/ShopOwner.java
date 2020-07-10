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
public class ShopOwner extends User{
    ShopOwner(){
        super();
    }
    ShopOwner(String UserName, String Password){
        super(UserName,Password);
    }

    @Override
    public void ViewProductDetails(String ID,ProductList Products, JTextArea src) {
        Products.Search(ID).DisplayStockManagement(src);
    }
    
    @Override
    public void AddProduct(String ID, String Name, String Price, String Weight,
            String ExpiryDate, String ImageFile, String Category,
            String MaximumLevelStock, String MinimumLevelStock,
            ProductList Products,String SupplierName,String SupplierDeliveryPolicy,
            String SupplierPrice) {
        Product AddedProduct=new Product();
        Supplier ProductSupplier=new Supplier();
        ProductSupplier.Edit(SupplierName,SupplierDeliveryPolicy,SupplierPrice);
        AddedProduct.Edit(ID,Name,Price,Weight,ExpiryDate,ImageFile,Category,ProductSupplier,
                MaximumLevelStock,MinimumLevelStock,MinimumLevelStock);
        Products.AddNewProduct(AddedProduct);
    }
    
    @Override
    public void ViewProductsForOrdering(ProductList Products,DefaultListModel src){
        Products.DisplayProductsForReOrdering(src);
    }

    @Override
    public Date Order(String ID,ProductList Products,String Ammount) {
        return Products.Search(ID).ReStock(Ammount);
    }

    @Override
    public void ViewProductDetailsForOrdering(String ID, ProductList Products, JTextArea src) {
        Product SearchProduct = Products.Search(ID);
        if(SearchProduct!=null){
            SearchProduct.DisplayForReOrder(src);
        }else{
            src.append("null");
        }
    }

    @Override
    public Product ViewProductDetailsForEdit(String ID,ProductList Products) {
        return Products.Search(ID);
    }

    @Override
    public void EditProduct(String ID, String Name, String Price, String Weight, String ExpiryDate, String ImageFile, String Category, String MaximumLevelStock, String MinimumLevelStock, ProductList Products, String SupplierName, String SupplierDeliveryPolicy, String SupplierPrice) {
        Supplier NewSupplier=new Supplier();
        NewSupplier.Edit(SupplierName,SupplierDeliveryPolicy,SupplierPrice);
        Products.Search(ID).Edit(ID, Name, Price, Weight, ExpiryDate, ImageFile, Category, NewSupplier,MaximumLevelStock,MinimumLevelStock);
    }
    
}
