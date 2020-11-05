package com.bsu;

import java.util.Objects;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Casino {
    private String id;
    private String nameOfCasino;
    private Date actualDate;
    private String game;
    private Integer rateValue;
    public Casino(String CSVString) throws Exception {
        String[] fields = CSVString.split(";");
        if (fields.length < 5) {
            throw new Exception("error");
        }
        this.id = fields[0];
        this.nameOfCasino = fields[1];
        this.actualDate = new SimpleDateFormat("dd/MM/yyyy").parse(fields[2]);
        this.game = fields[3];
        this.rateValue = Integer.parseInt(fields[4]);
    }

    public void setId(String number) {
        this.id = number;
    }
    public void setNameOfCasino(String nameOfCasino) {
        this.nameOfCasino = nameOfCasino;
    }
    public void setActualDate(Date actualDate) {
        this.actualDate = actualDate;
    }
    public void setGame(String game) {
        this.game = game;
    }
    public void setRateValue(Integer rateValue) {
        this.rateValue = rateValue;
    }
    public String getId() {
        return id;
    }
    public Date getActualDate() {
        return actualDate;
    }
    public String getNameOfCasino() {
        return nameOfCasino;
    }
    public Integer getRateValue() {
        return rateValue;
    }
    public String getGame() {
        return game;
    }

    @Override
    public String toString() {
        return id + ";" + nameOfCasino + ";" + actualDate.toString() + ";" + game + ";" +
                rateValue.toString() + ";";
    }

    @Override
    public boolean equals(Object t) {
        if (this == t) return true;
        if (t == null || getClass() != t.getClass()) return false;
        Casino bet = (Casino) t;
        return Objects.equals(id, bet.id) &&
                Objects.equals(nameOfCasino, bet.nameOfCasino) &&
                Objects.equals(actualDate, bet.actualDate) &&
                Objects.equals(game, bet.game) &&
                Objects.equals(rateValue, bet.rateValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOfCasino, actualDate, game, rateValue);
    }
}
