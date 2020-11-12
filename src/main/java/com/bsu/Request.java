package com.bsu;
import java.io.File;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.*;

public class Request{
    private User user;
    private boolean logged = false;

    private Map<String, User> users;
    private Map<Integer, Casino> bets;
    private Map<String, List<Casino>> games;


    public Request() {
        users = new HashMap<>();
        bets = new HashMap<>();
        games = new HashMap<>();
        user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

    public Map<Integer, Casino> getBets() {
        return bets;
    }

    public void setBets(Map<Integer, Casino> bets) {
        this.bets = bets;
    }

    public Map<String, List<Casino>> getGames() {
        return games;
    }

    public void setGames(Map<String, List<Casino>> games) {
        this.games = games;
    }

    public User enterSystem(String login, String password) {
        for (User user : users.values()) {
            if (user.getLogin().equalsIgnoreCase(login) && user.getPassword().equalsIgnoreCase(password)) {
                user.setLogged(true);
                return user;
            }
        }
        return new User();
    }

    public void showAllCasinoRecords() {
        for (Casino bet : bets.values()) {
            System.out.println(bet.toString());
        }
        System.out.println();
    }

    public Casino findNameCasinoByNumber(int id) {
        return bets.get(id);
    }

    public void showStatistics() {
        bets.forEach((key, value) ->
                System.out.println(key + ": " + value.getGameAndRateValue() + "\n")
        );
    }

    public void topNCasino(int n, String game) {
        List<Casino> c = games.get(game);
        if (c != null && c.size() >= n) {
            System.out.println(game + ": ");
            c.sort(Comparator.comparing(o -> o.getGameAndRateValue().get(game)));
            for (int i = 0; i < n; i++) {
                System.out.println(c.get(c.size() - i - 1));
            }
        } else {
            System.out.println("Ошибка");
        }
    }
    public static File createFile(String fileName) throws FileException {
        try {
            return new File(System.getProperty("user.dir") + "\\src\\" + fileName);
        } catch (Exception e) {
            throw new FileException("Ошибка при создании файла");
        }
    }

    public Map<String, User> transformListOfUsers(Scanner in)
            throws FileException {
        try {
            while (in.hasNext()) {
                String[] info = in.nextLine().split(" ");
                if (!(info[4].equalsIgnoreCase("ADMIN") || info[4].equalsIgnoreCase("USER")) || users.containsKey(info[0])) {
                    throw new FileException("Неверные входные данные");
                }
                users.put(info[0], new User(info[0], info[1], info[2], info[3], info[4], false));
            }
            return users;
        } catch (NumberFormatException ex) {
            throw new FileException("Ошибка синтаксического анализа файла");
        }
    }

    public Map<Integer, Casino> transformToListOfBets(Scanner in)
            throws FileException {
        try {
            while (in.hasNext()) {
                String[] info = in.nextLine().split(" ");
                int id = Integer.parseInt(info[0]);
                double rateValue = Double.parseDouble(info[4]);
                Casino c = new Casino(id, info[1], LocalDate.parse(info[2]), info[3], rateValue);
                if (bets.containsKey(id)) {
                    bets.get(id).getGameAndRateValue().put(info[3], rateValue);
                } else {
                    bets.put(id, c);
                }
                if (!games.containsKey(info[3])) {
                    games.put(info[3], new ArrayList<>());
                }
                games.get(info[3]).add(c);

            }
            return bets;
        } catch (Exception ex) {
            throw new FileException("Ошибка синтаксического анализа файла");
        }
    }

}

