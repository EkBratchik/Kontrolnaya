package com.bsu;

import java.util.Objects;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class Casino {
    private int id;
    private String nameOfCasino;
    private LocalDate actualDate;
    private Map<String, Double> gameAndRateValue;

    Casino (int id, String nameOfCasino, LocalDate actualDate, String game, double rateValue) {
        this.id = id;
        this.nameOfCasino = nameOfCasino;
        this.actualDate = actualDate;
        this.gameAndRateValue = new HashMap<>();
        gameAndRateValue.put(game, rateValue);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfCasino() {
        return nameOfCasino;
    }

    public void setNameOfCasino(String nameOfCasino) {
        this.nameOfCasino = nameOfCasino;
    }

    public LocalDate getActualDate() {
        return actualDate;
    }

    public void setActualDate(LocalDate actualDate) {
        this.actualDate = actualDate;
    }

    public Map<String, Double> getGameAndRateValue() {
        return gameAndRateValue;
    }

    public void setGameAndRateValue(Map<String, Double> gameAndRateValue) {
        this.gameAndRateValue = gameAndRateValue;
    }

    @Override
    public String toString() {
        return "[ " +
                "id = " + id +
                ", nameOfCasino = '" + nameOfCasino + '\'' +
                ", actulaDate = " + actualDate +
                ", gameAndRateValue = " + gameAndRateValue +
                " ]";
    }

    @Override
    public boolean equals(Object t) {
        if (this == t) return true;
        if (t == null || getClass() != t.getClass()) return false;
        Casino bet = (Casino) t;
        return Objects.equals(id, bet.id) &&
                Objects.equals(nameOfCasino, bet.nameOfCasino) &&
                Objects.equals(actualDate, bet.actualDate) &&
                Objects.equals(gameAndRateValue, bet.gameAndRateValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOfCasino, actualDate, gameAndRateValue);
    }
}
