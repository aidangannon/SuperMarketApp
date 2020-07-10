/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketapp;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author User
 */
public class Product {
    private int ID;
    private String Name;
    private double Price;
    private double PriceWithVAT;
    private double Weight;
    private Date ExpiryDate;
    private String ProductImage;
    private String ProductCatagory;
    private Integer MaxAmmountOfStock;
    private int MinAmmountOfStock;
    private int AmmountOfStock;
    private boolean OutOfStock;
    private Supplier ProductSupplier;
    private Date EstimatedDateToOrder;
    private Date LastDateOfOrder;
    
    
    public Product(String ID,String Name,String Price,
            String Weight,String ExpiryDate,String ProductImage,
            String ProductCatagory,Supplier ProductSupplier){
        Edit(ID,Name,Price,
            Weight,ExpiryDate,ProductImage,
            ProductCatagory,ProductSupplier);
    }
    
    public Product(String ID,String Name){
        this(ID,Name,"0","0","0/0/0","null","null",new Supplier());
    }
    
    public Product(){
        this("0","null");
    }

    public void Edit(String ID,String Name,String Price,
            String Weight,String ExpiryDate,String ProductImage,
            String ProductCatagory,Supplier ProductSupplier,String MaxAmmountOfStock,String MinAmmountOfStock){
        Edit(ID,Name,Price,
            Weight,ExpiryDate,ProductImage,
            ProductCatagory,ProductSupplier);
        setMaxAmmountOfStock(MaxAmmountOfStock);
        setMinAmmountOfStock(MinAmmountOfStock);;
    }
    
    //Edit method for user date from GUI hence string parameters
    public void Edit(String ID,String Name,String Price,
            String Weight,String ExpiryDate,String ProductImage,
            String ProductCatagory,Supplier ProductSupplier,String MaxAmmountOfStock,String MinAmmountOfStock,
            String AmmountOfStock){
        Edit(ID,Name,Price,
            Weight,ExpiryDate,ProductImage,
            ProductCatagory,ProductSupplier);
        setMaxAmmountOfStock(MaxAmmountOfStock);
        setMinAmmountOfStock(MinAmmountOfStock);
        setAmmountOfStock(AmmountOfStock);
    }
    
    //Edit method for initialising parameters
    private void Edit(String ID,String Name,String Price,
            String Weight,String ExpiryDate,String ProductImage,
            String ProductCatagory,Supplier ProductSupplier){
        setID(ID);
        setName(Name);
        setPrice(Price);
        setWeight(Weight);
        setExpiryDate(ExpiryDate);
        setProductImage(ProductImage);
        setProductCatagory(ProductCatagory);
        this.ProductSupplier=ProductSupplier;
        this.OutOfStock=true;
        this.EstimatedDateToOrder=new Date();
        
    }
    
    public void setID(String ID){
        try{
            this.ID= Integer.valueOf(ID);
        }catch(NumberFormatException nfe){
            throw new IllegalArgumentException("Invalid ID");
        }
    }

    public void setName(String Name){
        if(Name.equals("")){
            throw new IllegalArgumentException("Name can't be left blank");
        }else{
            this.Name=Name;
        }
    }
    
    public void setPrice(String Price){
        try{
            this.Price= Double.valueOf(Price);
            CalculatePriceWithVAT();
        }catch(NumberFormatException nfe){
            throw new IllegalArgumentException("Invalid Price");
        }
    }
    
    public void setWeight(String Weight){
        try{
            this.Weight= Double.valueOf(Weight);
        }catch(NumberFormatException nfe){
            throw new IllegalArgumentException("Invalid Weight");
        }
    }
    
    public void setExpiryDate(String ExpiryDate){
        try {
            this.ExpiryDate= new SimpleDateFormat("dd/MM/yyyy").parse(ExpiryDate);
        } catch (ParseException ex) {
            throw new IllegalArgumentException("Invalid Expiry Date");
        }
    }
    
    public void setProductImage(String ProductImage){
        if(ProductImage.equals("")){
            throw new IllegalArgumentException("Please choose an image");
        }else{
            this.ProductImage=ProductImage;
        }
    }
    
    public void setProductCatagory(String ProductCatagory){
        if(ProductCatagory.equals("")){
            throw new IllegalArgumentException("Product catagory can't be left blank");
        }else{
            this.ProductCatagory=ProductCatagory;
        }
    }
    
    public void setMaxAmmountOfStock(String MaxAmmountOfStock){
        try{
            if((this.MaxAmmountOfStock == null)||(this.MaxAmmountOfStock == Integer.valueOf(MaxAmmountOfStock))){
                int IntMaxAmmountOfStock=Integer.valueOf(MaxAmmountOfStock);
                if(!(IntMaxAmmountOfStock>1)){
                    throw new IllegalArgumentException("Max Stock has to more than 1");
                }else{
                    this.MaxAmmountOfStock=IntMaxAmmountOfStock;
                }
            }else{
                throw new IllegalArgumentException("Max Stock can only be defined once");
            }
        }catch(NumberFormatException nfe){
            throw new IllegalArgumentException("Invalid Value for Max Stock");
        }
    }
    
    public void setMinAmmountOfStock(String MinAmmountOfStock){
        try{
            int IntMinAmmountOfStock=Integer.valueOf(MinAmmountOfStock);
            if(!((IntMinAmmountOfStock>0)&&(IntMinAmmountOfStock<MaxAmmountOfStock))){
                throw new IllegalArgumentException("Min Stock has to be more than 0 and less than the max stock");
            }
            this.MinAmmountOfStock=IntMinAmmountOfStock;
        }catch(NumberFormatException nfe){
            throw new IllegalArgumentException("Invalid Ammount Of Product In Stock");
        }
    }
    
    public void setAmmountOfStock(String AmmountOfStock){
        try{
            int IntAmmountOfStock=Integer.valueOf(AmmountOfStock);
            if(IntAmmountOfStock>MinAmmountOfStock){
                OutOfStock=false;
            }else if (IntAmmountOfStock==MinAmmountOfStock){
                OutOfStock=true;
            }
            if((IntAmmountOfStock>=MinAmmountOfStock)&&(IntAmmountOfStock<=MaxAmmountOfStock)){
                this.AmmountOfStock= Integer.valueOf(AmmountOfStock);
            }else{
                throw new IllegalArgumentException("Quantity must be less than the max stock & more the min stock");
            }
        }catch(NumberFormatException nfe){
            throw new IllegalArgumentException("Invalid Ammount Of Stock");
        }
    }
    
    public void setLastDateOfOrder(String LastDateOfOrder){
        if(LastDateOfOrder.equals("null")){
            this.LastDateOfOrder=null;
        }else{
        try {
                this.LastDateOfOrder= new SimpleDateFormat("dd/MM/yyyy").parse(LastDateOfOrder);
            } catch (ParseException ex) {
                throw new IllegalArgumentException("Invalid Expiry Date");
            }
        }
    }
    
    @Override
    public String toString(){
        SimpleDateFormat DateFormat=new SimpleDateFormat("dd/MM/yyyy");
        return String.format("ID:%s\nName:%s\nPrice:%s\nWeight:%s\nExpiry Date:%s\n"
                + "Image:%s\nProduct Catagory:%s\n"
                + "Max Stock:%s\nMin Stock:%s\nAmmount Of Product In Stock:%s\n"
                + "Last Date Of Order:%s\n",
            ID,Name,Price,Weight,DateFormat.format(ExpiryDate),ProductImage,ProductCatagory,
            MaxAmmountOfStock,MinAmmountOfStock,AmmountOfStock,getLastDateOfOrder());
    }
    
    private String toStringPurchase(){
        SimpleDateFormat DateFormat=new SimpleDateFormat("dd/MM/yyyy");
        return String.format("Name:%s\nPrice:%s\nWeight:%s\nExpiry Date:%s\n"
                + "Product Catagory:%s\nAmmount Of Product In Stock:%s\n",
                Name,PriceWithVAT,Weight,DateFormat.format(ExpiryDate),
                ProductCatagory,AmmountOfStock-MinAmmountOfStock);
    }
    
    private String toStringStockManagement(){
        SimpleDateFormat DateFormat=new SimpleDateFormat("dd/MM/yyyy");
        return String.format("ID:%s\nName:%s\nPrice Without VAT:%s\n"
                + "Price With VAT:%s\nExpiry Date:%s\nWeight:%s\n"
                + "Estimated Date To Order:%s\n",
                ID,Name,Price,PriceWithVAT,DateFormat.format(ExpiryDate),
                Weight,DateFormat.format(EstimatedDateToOrder));
    }
    
    private String toStringForReOrder(){
        double TotalPrice=RoundTwo(PriceWithVAT+ProductSupplier.getPrice());
        SimpleDateFormat DateFormat=new SimpleDateFormat("dd/MM/yyyy");
        return String.format("Advisable Ammount Of Products To Be ReOrdered:%s\n"
                + "Product Category:%s\nExpected Date Of Delivery:%s\n"
                + "Total Ammount To Pay:%s\nDate Of Last Order:%s\nCompany Name:%s\n",
                (MaxAmmountOfStock-MinAmmountOfStock),ProductCatagory,DateFormat.format(ProductSupplier.getDeliveryDate()),
                TotalPrice,getLastDateOfOrder(),
                ProductSupplier.getSupplierName());
    }
    
    public boolean LoadFromFile(BufferedReader bin){
        boolean IsRead;
        try{
            String NewLine;
            String[] Values;
            NewLine=bin.readLine();
            while(!((NewLine).equals("~~"))){
                Values=(NewLine.split(":"));
                switch (Values[0]){
                    case "ID":
                        setID(Values[1]);
                        break;
                    case "Name":
                        setName(Values[1]);
                        break;
                    case "Price":
                        setPrice(Values[1]);
                        break;
                    case "Weight":
                        setWeight(Values[1]);
                        break;
                    case "Expiry Date":
                        setExpiryDate(Values[1]);
                        break;
                    case "Image":
                        if(Values.length>2){
                            setProductImage((Values[1]+":"+Values[2]));
                        }else{
                            setProductImage(Values[1]);
                        }
                        break;
                    case "Product Catagory":
                        setProductCatagory(Values[1]);
                        break;
                    case "Max Stock":
                        setMaxAmmountOfStock(Values[1]);
                        break;
                    case "Min Stock":
                        setMinAmmountOfStock(Values[1]);
                        break;
                    case "Ammount Of Product In Stock":
                        setAmmountOfStock(Values[1]);
                        break;
                    case "Last Date Of Order:":
                        setLastDateOfOrder(Values[1]);
                }
                NewLine=bin.readLine();
            }
            ProductSupplier.LoadFromFile(bin);
            IsRead=true;
        }catch(IOException e){
            IsRead=false;
        }
        return IsRead;
    }
    
    boolean SaveToFile(FileWriter Writer){
        boolean IsWritten;
        try {
            Writer.write("##\n");
            Writer.write(String.valueOf(this));
            ProductSupplier.SaveToFile(Writer);
            Writer.write("--\n");
            IsWritten=true;
        } catch (IOException ex) {
            IsWritten=false;
        }
        return IsWritten;
    }
    
    public void Display(JTextArea src){
        src.append(String.valueOf(this)+"PriceWithVAT:"+PriceWithVAT+"\n");
        ProductSupplier.Display(src);
    }
    
    public void DisplayImage(JLabel src){
        src.setIcon(new ImageIcon(ProductImage));
    }

    public void DisplayStockManagement(JTextArea src){
        src.append(toStringStockManagement());
    }
    
    public void DisplayPurchase(JTextArea src){
        src.append(toStringPurchase());
    }
    
    public void Display(DefaultListModel src){
        src.addElement(String.valueOf(Name+"-"+ID+"-"+(AmmountOfStock-MinAmmountOfStock)));
    }
    
    public void DisplayForReOrder(JTextArea src){
        ProductSupplier.calculateDeliveryDate();
        src.append(toStringForReOrder());
    }
    
    public Date PurchaseProduct(String Ammount){
        int IntAmmount=setQuantityMinus(Ammount);
        setAmmountOfStock(String.valueOf(IntAmmount));
        ProductSupplier.calculateDeliveryDate();
        System.out.print(getAmmountOfAvailableStock());
        return ProductSupplier.getDeliveryDate();
    }
    
    public Date ReStock(String Ammount){
        int IntAmmount=setQuantityAdd(Ammount);
        setAmmountOfStock(String.valueOf(IntAmmount));
        LastDateOfOrder=new Date();
        ProductSupplier.calculateDeliveryDate();
        return ProductSupplier.getDeliveryDate();
    }
    
    private int setQuantityAdd(String Quantity){
        try{
            if(Integer.valueOf(Quantity)==0){
                throw new IllegalArgumentException("Quantity cant be zero");
            }
            int IntAmmountOfStock=Integer.valueOf(Quantity)+AmmountOfStock;
            if((IntAmmountOfStock>=MinAmmountOfStock)&&(IntAmmountOfStock<=MaxAmmountOfStock)){
                return IntAmmountOfStock;
            }else{
                throw new IllegalArgumentException("Quantity must be less than the max stock & more the min stock");
            }
        }catch(NumberFormatException nfe){
            throw new IllegalArgumentException("Invalid Quantity");
        }
    }
    
    private int setQuantityMinus(String Quantity){
        try{
            if(Integer.valueOf(Quantity)==0){
                throw new IllegalArgumentException("Quantity cant be zero");
            }
            int IntAmmountOfStock=((AmmountOfStock)-Integer.valueOf(Quantity));
            if((IntAmmountOfStock>=MinAmmountOfStock)&&(IntAmmountOfStock<=MaxAmmountOfStock)){
                return IntAmmountOfStock;
            }else{
                throw new IllegalArgumentException("Quantity must be less than the max stock & more the min stock");
            }
        }catch(NumberFormatException nfe){
            throw new IllegalArgumentException("Invalid Quantity");
        }
    }
    
    public boolean getOutOfStock(){return OutOfStock;}
    
    private void CalculatePriceWithVAT(){
        PriceWithVAT=Price*1.2;
        PriceWithVAT=RoundTwo(PriceWithVAT);
    }
    
    private double RoundTwo(double num){
        num = Math.round((num) * 100.0) / 100.0;
        return num;
    }
    
    public double getPriceWithVAT(){
        return PriceWithVAT;
    }
    
    public int getID(){
        return ID;
    }
    
    public String getLastDateOfOrder(){
        if(LastDateOfOrder==null){
            return "null";
        }
        SimpleDateFormat DateFormat=new SimpleDateFormat("dd/MM/yyyy");
        return DateFormat.format(LastDateOfOrder);
    }
    
    public int getAmmountOfAvailableStock(){return AmmountOfStock-MinAmmountOfStock;}
    
    public String getProductName(){return Name;}
    public String getExpiryDate(){
        SimpleDateFormat DateFormat=new SimpleDateFormat("dd/MM/yyyy");
        return DateFormat.format(ExpiryDate);}
    public String getWeight(){
        return String.valueOf(Weight);
    }
    public String getStringID(){
        return String.valueOf(getID());
    }
    public String getPrice(){
        return String.valueOf(Price);
    }
    public String getProductCatagory(){
        return (ProductCatagory);
    }
    public String getMaxLevelOfStock(){
        return String.valueOf(MaxAmmountOfStock);
    }
    public String getImageFile(){
        return (ProductImage);
    }
    public String getMinLevelOfStock(){
        return String.valueOf(MinAmmountOfStock);
    }
    public String getSupplierName(){
        return (ProductSupplier.getSupplierName());
    }
    public String getSupplierPrice(){
        return String.valueOf(ProductSupplier.getPrice());
    }
    public String getDeliveryPolicy(){
        return (ProductSupplier.getDeliveryPolicy());
    }
}
