package com.bsu;

import java.io.FileWriter;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Request{
    enum RequestPr {
        ADD_USER(1),
        LOGIN(2),
        VIEW_ALL_RECORDS(3),
        FIND_CASINO_BY_ID(4),
        CASINO_STATEMENTS(5),
        TOP_CASINOES(6),
        DEFAULT(0);

        private int value;

        RequestPr(int val) {
            this.value = val;
        }

        public int getValue() {
            return value;
        }

        public static RequestPr getRequestint(int val) {
            for (RequestPr req : values()) {
                if (req.value == val) return req;
            }
            return RequestPr.DEFAULT;
        }

    }

    public static boolean addUser(List<UserCasino> users, FileWriter writer, Scanner scanner) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter login:");
        String login = scanner.nextLine();
        for (UserCasino user : users) {
            if (login.equals(user.getLogin())) {
                System.out.println("Error");
                return false;
            }
        }
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        UserCasino newUser = new UserCasino(username, login, email, password, "USER");
        try {
            writer.write(newUser.toString() + System.lineSeparator());
        } catch (Exception ex) {
            System.out.println("Can't add user");
            return false;
        }
        users.add(newUser);
        return true;
    }

    public static UserCasino login(List<UserCasino> users, Scanner scanner) {
        System.out.println("Enter login:");
        String login = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        for (UserCasino user : users) {
            if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                System.out.println("" + user.getUsername());
                return user;
            }
        }
        System.out.println("Again");
        return new UserCasino("", "", "", "", "USER");
    }

    public static void showAllCasinoRecords(List<Casino> casinoes) {
        for (Casino casino : casinoes) {
            System.out.println(casinoes.toString());
        }
    }

    public static String findNameOfCasinoById(List<Casino> casinoes, int id) {
        for (Casino casino : casinoes) {
            if (casino.getId() == id) {
                return casinoes.getNameOfCasino();
            }
        }
        return "No info";
    }

    public static void chainStats(List<Casino> casinoes, String chain) {
        double rank = 0;
        int numberOfCasino = 0;
        for (Casino casino : casinoes) {
            if (casino.getNameOfCasino().equals(chain)) {
                rank += casino.getRateValue();
                numberOfCasino++;
            }
        }
        if (numberOfCasino == 0) {
            System.out.println("Unknown");
        } else {
            rank /= numberOfCasino;
            System.out.println("Number of casino: " + numberOfCasino + ", : " + rank);
        }
    }

    public static void topCasino(List<Casino> casinoes, LocalDate lb, LocalDate ub, int n) {
        casinoes.stream().sorted(new Comparator<Casino>() {
            @Override
            public int compare(Casino o1, Casino o2) {
                return o2.getRateValue() - o1.getRateValue();
            }
        }).filter(new Predicate<Casino>() {
            @Override
            public boolean test(Casino casino) {
                LocalDate od = casino.getActualDate();
                if ((od.compareTo(lb) >= 0) && (od.compareTo(ub) <= 0)){
                    return true;
                }
                return false;
            }
        }).limit(n).forEach(System.out::println);
    }
}

