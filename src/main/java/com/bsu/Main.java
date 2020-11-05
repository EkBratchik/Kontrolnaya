package com.bsu;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static UserCasino currentUser = new UserCasino("", "", "", "", "");
    public static void main(String[] args) {
        File userInfo = new File(".\\src\\main\\java\\com\\bsu\\users.txt");
        File hostelInfo = new File(".\\src\\main\\java\\com\\bsu\\bets.txt");
        final String dateFormat = "dd.MM.yyyy";
        try (Scanner scanner = new Scanner(System.in)) {
            CasinoReader hostelReader = new CasinoReader(hostelInfo, dateFormat);
            CasinoReader userReader = new CasinoReader(userInfo);
            List<UserCasino> users = userReader.readAllRecords();
            List<Casino> casinoes = hostelReader.readAllRecords();
            FileWriter userWriter = new FileWriter(".\\src\\main\\java\\com\\bsu\\users.txt", true);
            showMenu();
            int req = scanner.nextInt();
            while (req != 7) {
                workWithRequests(casinoes, users, scanner, userWriter, dateFormat, req);
                showMenu();
                req = scanner.nextInt();
            }
            userWriter.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void showMenu() {
        System.out.println("Add new user");
        System.out.println("Login as existing user");
        System.out.println("Show all records");
        System.out.println("Find casino name by casino id(admin only)");
        System.out.println("Casino statistics");
        System.out.println("Top casinoes");
        System.out.println("Exit");
    }

    public static void workWithRequests(List<Casino> casinoes, List<UserCasino> users, Scanner scanner,
                                        FileWriter userWriter, String dateFormat, int req) {
        scanner.nextLine();
        if (currentUser.getRole().isEmpty()){
            if(req != 1 && req != 2){
                System.out.println("You need to login first");
                return;
            }
        }
        switch (Request.RequestPr.getRequestint(req)) {
            case ADD_USER:
                Request.addUser(users, userWriter, scanner);
                break;
            case LOGIN:
                currentUser = Request.login(users, scanner);
                break;
            case VIEW_ALL_RECORDS:
                Request.showAllCasinoesRecords(casinoes);
                break;
            case FIND_NAME_OF_CASINO_BY_ID:
                if(currentUser.getRole().equals("USER")){
                    System.out.println("Admin access only ");
                }else {
                    try {
                        System.out.println("Enter id: ");
                        int id = scanner.nextInt();
                        System.out.println(Request.findCasinoChainByCasinoId(casinoes,id));
                    }catch (Exception ex){
                        System.out.println("Wrong id");
                    }
                }
                break;
            case CASINO_STATEMENTS:
                System.out.println("Enter casino name:");
                String casino = scanner.nextLine();
                Request.chainStats(casinoes,casino);
                break;
            case TOP_CASINOES:
                try{
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
                    int n; LocalDate ub; LocalDate lb;
                    System.out.println("Enter " + "n:");
                    n = scanner.nextInt(); scanner.nextLine();
                    System.out.println("Enter date lower bound(dd.MM.yyyy)");
                    String lbstr = scanner.nextLine();
                    lb = LocalDate.parse(lbstr,formatter);
                    System.out.println("Enter date upper bound(dd.MM.yyyy)");
                    String ubstr = scanner.nextLine();
                    ub = LocalDate.parse(ubstr,formatter);
                    Request.topCasino(casinoes,lb,ub,n);
                }catch (Exception ex){
                    System.out.println("Can't parse expressions");
                }
                break;
            case DEFAULT:
                System.out.println("Unknown request");
                break;
        }
    }
}