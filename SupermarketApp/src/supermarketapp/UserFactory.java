/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketapp;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author User
 */
public class UserFactory{
    public static User getUser(String UserType){
        switch(UserType){
            case "Customer":
                return new Customer();
            case "Owner":
                return new ShopOwner();
            case "Employee":
                return new Employee();
            default:
                return null;
        }
    }
}
