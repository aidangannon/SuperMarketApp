/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketapp;

import java.io.BufferedReader;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JTextArea;

/**
 *
 * @author User
 */
abstract public class User implements UserRoles{
    protected String UserName;
    protected String Password;
    
    User(){
        this("","");
    }
    
    User(String UserName, String Password){
        Edit(UserName,Password);
    }
    
    public String toString(){
        return "UserName:"+UserName+"\n";
    }
    
    public void Edit(String UserName,String Password){
        this.UserName=UserName;
        this.Password=Password;
    }
    
    public boolean LoadFromFile(BufferedReader bin){
        boolean IsRead;
        try{
            String NewLine;
            String[] Values;
            NewLine=bin.readLine();
            while(!((NewLine).equals("--"))){
                Values=(NewLine.split(":"));
                switch (Values[0]){
                    case "User Name":
                        UserName=Values[1];
                        break;
                    case "Password":
                        Password=Values[1];
                        break;
                }
                NewLine=bin.readLine();
            }
            IsRead=true;
        }catch(IOException e){
            IsRead=false;
        }
        return IsRead;
    }
    
    @Override
    public void AddProduct(String ID, String Name, String Price, String Weight,
            String ExpiryDate, String ImageFile, String Category,
            String MaximumLevelStock, String MinimumLevelStock,
            ProductList Products,String SupplierName,String SupplierDeliveryPolicy,
            String SupplierPrice){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void ViewProducts(ProductList Products, DefaultListModel src) {
        Products.Display(src);
    }
    
    @Override
    public void ViewProductsForOrdering(ProductList Products,DefaultListModel src){
        Products.Display(src);
    }
    
    public String GetUserName(){return UserName;}
    
    public String GetPassword(){return Password;}
    
    public boolean IsCustomer(){
        boolean IsCustomer=true;
        try{
            User Customer = ((Customer)this);
        }catch(ClassCastException cce){
            IsCustomer=false;
        }
        return IsCustomer;
    }
    
    public boolean IsOwner(){
        boolean IsOwner=true;
        try{
            User Owner = ((ShopOwner)this);
        }catch(ClassCastException cce){
            IsOwner=false;
        }
        return IsOwner;
    }
    
    public boolean IsEmployee(){
        boolean IsEmployee=true;
        try{
            User Owner = ((ShopOwner)this);
        }catch(ClassCastException cce){
            IsEmployee=false;
        }
        return IsEmployee;
    }
        
}
