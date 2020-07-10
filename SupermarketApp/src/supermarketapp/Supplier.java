/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketapp;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import javax.swing.JTextArea;

/**
 *
 * @author User
 */
public class Supplier {
    private String Name;
    private int DeliveryPolicy;
    private Date DeliveryDate;
    private Double Price;
    public Supplier(){
        Edit("null","0","0");
    }
    public void Edit(String Name,String DeliveryPolicy,String Price){
        setName(Name);
        setDeliveryPolicy(DeliveryPolicy);
        setPrice(Price);
    }
    
    public void setName(String Name){
        if(!Name.equals("")){
            this.Name=Name;
        }else{
            throw new IllegalArgumentException("Invalid Supplier name");
        }
    }
    
    public void setDeliveryPolicy(String DeliveryPolicy){
        try{
            this.DeliveryPolicy=Integer.valueOf(DeliveryPolicy);
        }catch(NumberFormatException nfe){
            throw new IllegalArgumentException("Invalid delivery policy");
        }
    }
    
    public void calculateDeliveryDate(){
        this.DeliveryDate=new Date();
        this.DeliveryDate.setDate(DeliveryDate.getDate()+DeliveryPolicy);
    }
    
    public void setPrice(String Price){
        try{
            this.Price=Double.valueOf(Price);
        }catch(NumberFormatException nfe){
            throw new IllegalArgumentException("Invalid delivery price");
        }
    }
    
    public boolean LoadFromFile(BufferedReader bin)   
    {
        boolean IsRead;
        try{
            String NewLine;
            String[] Values;
            NewLine=bin.readLine();
            while(!((NewLine).equals("--"))){
                Values=(NewLine.split(":"));
                switch (Values[0]){
                    case "Supplier Name":
                        this.Name=(Values[1]);
                        break;
                    case "Supplier Delivery Policy":
                        setDeliveryPolicy(Values[1]);
                        break;
                    case "Supplier Delivery Price":
                        setPrice(Values[1]);
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
    public String toString(){
        return String.format("Supplier Name:%s\nSupplier Delivery Policy:%s\nSupplier Delivery Price:%s\n",
                Name,DeliveryPolicy,Price);
    }
    
    public void Display(JTextArea src){
        src.append(String.valueOf(this));
    }
    
    boolean SaveToFile(FileWriter Writer){
        boolean IsWritten;
        try {
            Writer.write("~~\n");
            Writer.write(String.valueOf(this));
            IsWritten=true;
        } catch (IOException ex) {
            IsWritten=false;
        }
        return IsWritten;
    }
    
    public Date getDeliveryDate(){
        return DeliveryDate;
    }
    
    public double getPrice(){
        return Price;
    }
    
    public String getSupplierName(){
        return Name;
    }

    public String getDeliveryPolicy(){
        return String.valueOf(DeliveryPolicy);
    }
    
}

