package com.bsu;

import java.util.Objects;

public class UserCasino {
    private String username;
    private String login;
    private String email;
    private String password;
    private String role;

    public UserCasino(String CSVString) throws Exception {
        String[] fields = CSVString.split(";");
        if (fields.length < 6) {
            throw new Exception("error");
        }
        this.username = fields[0];
        this.login = fields[1];
        this.email = fields[2];
        this.password = fields[3];
        this.role = fields[4];
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
        return username + ";" + login + ";" +  email + ";" + password + ";" +
                role + ";";
    }

    @Override
    public boolean equals(Object t) {
        if (this == t) return true;
        if (t == null || getClass() != t.getClass()) return false;
        Casino casino = (Casino) t;
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

