/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author Iwan
 */
public class UserMapper {
    public void createUser(){}
    
//    public User getUser(int id){}
    
    //Metode til at få returneret en liste med brugerne i databasen:
    public List<User> getAllUsers(){
        
    List<User> userlist = new ArrayList<User>();
    
    try {   
            String sql = "SELECT * FROM usertable";
            Connection con = DB.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
               
               int idFromDB = rs.getInt("id");
               String usernameFromDB = rs.getString("username");
               String passwordFromDB = rs.getString("password");
               User user = new User(idFromDB, usernameFromDB, passwordFromDB);
               userlist.add(user);                
            }
            con.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();            
        }
        return userlist;
      }
    
    //Metode til at indsætte en ny bruger i databasen:
    public void insertUserIntoDatabase(String username, String password){
        
        try {
            String sql = "INSERT INTO usertable(username, password) VALUES(?,?)";
            Connection con = DB.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            con.close();
        }
            catch (SQLException ex) {
            ex.printStackTrace(); 
            }
    }
    
//    public void deleteUser(int id){}
    
    public void editUser(User user){}
    
    public boolean authenticateUser(String username, String password){
        try {
            String sql = "select username, password from usertable where username = ?";
            Connection con = DB.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
               String passwordFromDB = rs.getString("password");
               if (passwordFromDB.equals(password)) {
                   return true;
               }
            }
            else {
               return false;
            }
        con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
      }
    public static void main(String[] args) {
        UserMapper um = new UserMapper();
        boolean isAuthenticated = um.authenticateUser("testuser", "password123");
        if(isAuthenticated){
            System.out.println("YES we are in");
        }
        else {
            System.out.println("Oh no no access");
        }
    }
}
