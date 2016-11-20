/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Iwan
 */
public class User {
    private int id;
    private String username;
    private String password;

    public User(int id, String userName, String password) {
        this.id = id;
        this.username = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
