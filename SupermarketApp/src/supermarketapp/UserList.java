/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

public class UserList {
    private ArrayList<User> Users;
    
    UserList(){
        Edit(new ArrayList());
        LoadFromFile();
    }
    
    private boolean LoadFromFile(BufferedReader bin){
        //Searches file for 'User Type:XXXX'
        //Seperates string into 'User Type' and 'XXXX'
        //Calls factory method and makes new Customer, Owner based on Type
        boolean IsRead;
        try{
            String NewLine;
            String[] Values;
            NewLine=bin.readLine();
            while(NewLine!=null){
                Values=(NewLine.split(":"));
                switch (Values[0]){
                    case "User Type":
                            User NewUser = UserFactory.getUser(Values[1]);
                            NewUser.LoadFromFile(bin);
                            Users.add(NewUser);
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
    
    private boolean LoadFromFile(){
        boolean IsRead=false;
        try {
            BufferedReader bin = new BufferedReader(new FileReader("Users.txt"));
            LoadFromFile(bin);
            bin.close();
            bin=null;
        } catch (IOException ex) {
            //Error
        }
        
        return IsRead;
    }
    public void Edit(ArrayList<User> Users){
        this.Users=Users;
    }
    
    public User ValidateUser(String UserName,String Password){
        for(User PotentialUser:Users){
            if((PotentialUser.GetUserName().equals(UserName))&&
                    (PotentialUser.GetPassword().equals(Password))){
                return PotentialUser;
            }
        }return null;
    }
    
}
