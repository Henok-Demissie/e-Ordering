package com.Adminlogin.bean;

import java.io.Serializable;

/**
 * A bean class to encapsulate login credentials.
 * Implements {@link Serializable} to allow easy serialization for session management.
 */
public class LoginBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    /**
     * Gets the username.
     * 
     * @return the username as a {@link String}.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     * 
     * @param username the username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     * 
     * @return the password as a {@link String}.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     * 
     * @param password the password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "username='" + username + '\'' +
                ", password='******'" +  // Hides the actual password for security purposes
                '}';
    }
}
