/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author User
 */
public class ProductList {
    
    private ArrayList<Product> Products;
    
    public ProductList(){
        Edit(new ArrayList());
    }
    
    private void Edit(ArrayList<Product> Products){
        this.Products=Products;
    }
    
    public void Display(DefaultListModel src){
        for(Product AddedProduct:Products){
            AddedProduct.Display(src);
        }
    }
    
    public void DisplayProductsForReOrdering(DefaultListModel src){
        for(Product AddedProduct:Products){
            if(AddedProduct.getAmmountOfAvailableStock()==0){
                AddedProduct.Display(src);
            }
        }
    }
    
    public Product Search(String ID){
        for(Product FoundProduct:Products){
            if(Integer.valueOf(ID)==FoundProduct.getID()){
                return FoundProduct;
            }
        }
        return null;
    }
    
    public boolean LoadFromFile(BufferedReader bin){
        try {
            String NewLine=bin.readLine();
            Product InstanceProduct;
            while(NewLine!=null){
                if (NewLine.equals("##")){
                    InstanceProduct=new Product();
                    InstanceProduct.LoadFromFile(bin);
                    Products.add(InstanceProduct);
                    NewLine=bin.readLine();
                }
            }
        } catch (IOException ex) {
            return false;
        }
        return true;
    }
    
    private boolean SaveToFile(Product ProductToBeSaved,FileWriter Writer){
        boolean IsWritten;
        ProductToBeSaved.SaveToFile(Writer);
        IsWritten=true;
        return IsWritten;
    }
    
    public boolean SaveToFile(FileWriter Writer){
        
        boolean Saved=false;
        
        for(Product TempProduct:Products)
        {
            Saved=SaveToFile(TempProduct,Writer);
        }
        return Saved;
    }
    
    public boolean ClearFile(FileWriter Writer){
        boolean IsWritten=false;
        try{
            Writer.write("");
            IsWritten=true;
        }catch(IOException e){
            IsWritten=false;
        }
        return IsWritten;
    }
    
    public void AddNewProduct(Product ProductToBeAdded){
        if((Search(String.valueOf(ProductToBeAdded.getID())))==null){
            Products.add(ProductToBeAdded);
        }else{
            throw new IllegalArgumentException("Cannot have two or more products with the same ID");
        }
    }
    
}
