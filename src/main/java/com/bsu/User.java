package com.bsu;
import java.util.Objects;

public class User {
    private String username;
    private String login;
    private String email;
    private String password;
    private String role;
    private boolean logged = false;

    public User(String username, String login, String email, String password, String role, boolean logged) {
        this.username = username;
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;
        this.logged = logged;
    }
    public User() {
        this.username = "";
        this.login = "";
        this.email = "";
        this.password = "";
        this.role = "USER";
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }
    public String getUsername() {
        return username;
    }
    public String getLogin() {
        return login;
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public String getRole() {
        return role;
    }
    @Override
    public String toString() {
        return username + ' ' + login + ' ' + email + ' ' + password + ' ' + role + ' ';
    }

    @Override
    public boolean equals(Object t) {
        if (this == t) return true;
        if (t == null || getClass() != t.getClass()) return false;
        User casino = (User) t;
        return Objects.equals(username, casino.username) &&
                Objects.equals(login, casino.login) &&
                Objects.equals(email, casino.email) &&
                Objects.equals(password, casino.password) &&
                Objects.equals(role, casino.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, login, email, password, role);
    }

}


