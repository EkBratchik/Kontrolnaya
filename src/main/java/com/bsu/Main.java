package com.bsu;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.*;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) throws FileException  {
        if (args.length >= 2) {
            File file = Request.createFile(args[0]);
            try (Scanner scan = new Scanner(System.in);
                 Scanner usersReader = new Scanner(file);
                 Scanner productsReader = new Scanner(Request.createFile(args[1]));
                 FileWriter out = new FileWriter(file, true)) {
                Request request = new Request();
                request.setUsers(request.transformListOfUsers(usersReader));
               request.setBets(request.transformToListOfBets(productsReader));

                if (request.getUsers().size() != 0 || request.getBets().size() != 0) {
                    showMenu();
                    int input;
                    do {
                        System.out.println("Введите номер запроса: ");
                        input = scan.nextInt();
                        processRequest(scan, request, input, out);
                    } while (input != 7);
                } else {
                    System.out.println("Ошибка.Нет исходных данных для чтения");
                }
            } catch (FileException | IOException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод запроса");
            }
        }
    }
    public static void showMenu() {
        System.out.println("Регистрация пользователя");
        System.out.println("Вход в систему");
        System.out.println("Просмотр всех записей");
        System.out.println("Поиск казино по номеру (только для админа)");
        System.out.println("Статистика по каждому казино: сколько разных игр, средняя ставка по всем играм");
        System.out.println("Топ-N казино по величине средней ставки определенной игры ");
        System.out.println("Выход");
    }

    public static void processRequest(Scanner scan, Request request, int input, FileWriter out)
            throws FileException, IOException {
        switch (input) {
            case 1: {
                System.out.println("Введите имя пользователя, логин, почту и пароль для регистрации:");
                scan.nextLine();
                String name = scan.nextLine();
                String login = scan.nextLine();
                String email = scan.nextLine();
                String password = scan.nextLine();
                User user = new User(name, login, email, password, "USER", true);
                if (request.getUsers().containsKey(user.getUsername())) {
                    throw new FileException("Пользователь уже существует");
                }
                out.write("\n" + user.toString());
                break;
            }
            case 2: {
                System.out.println("Введите логин и пароль:");
                scan.nextLine();
                String login = scan.nextLine();
                String password = scan.nextLine();
                if (!request.enterSystem(login, password).getEmail().equals("")) {
                    request.setLogged(true);
                    request.setUser(request.enterSystem(login, password));
                    System.out.println("Вход в систему");
                } else {
                    System.out.println("Неверно введены данные");
                }
                break;
            }
            case 3: {
                System.out.println("Все записи: ");
                request.showAllCasinoRecords();
                break;
            }
            case 4: {
                if (request.isLogged() && request.getUser().getRole().equalsIgnoreCase("ADMIN")) {
                    System.out.println("Введите номер казино: ");
                    Casino bet = request.findNameCasinoByNumber(scan.nextInt());
                    if (bet == null) {
                        System.out.println("Такого казино не существует");
                    } else {
                        System.out.println(bet.toString());
                    }
                } else {
                    System.out.println("Вы не являетесь админом");
                }
                break;
            }
            case 5: {
                request.showStatistics();
                break;
            }
            case 6: {
                try {
                    System.out.println("Введите число:");
                    int n = scan.nextInt();
                    System.out.println("Введите игру: ");
                    scan.nextLine();
                    String category = scan.nextLine();
                    request.topNCasino(n, category);
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case 7: {
                System.out.println("Завершение процесса");
                break;
            }
        }

    }
}