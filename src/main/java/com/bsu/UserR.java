package com.bsu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserR {
    private File file;

    public UserR(File file) {
        this.file = file;
    }

    public List<UserCasino> readAllRecords() {
        List<UserCasino> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                records.add(buildRecord(scanner.nextLine()));
            }
        } catch (FileNotFoundException exception) {
            System.out.println("NO info");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return records;
    }

    public UserCasino buildRecord(String str) throws IllegalArgumentException {
        String[] fields = str.split(";");
        if (fields.length < 5) {
            throw new IllegalArgumentException("No info");
        }
        String username = fields[0];
        String login = fields[1];
        String email = fields[2];
        String password = fields[3];
        String role = fields[4];
        return new UserCasino(username,login,email,password,role);
    }


}

