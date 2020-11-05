package com.bsu;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CasinoReader {
    private File file;
    private String dateFormat;

    public CasinoReader(File file, String dateFormat) {
        this.file = file;
        this.dateFormat = dateFormat;
    }

    public List<Casino> readAllRecords() {
        List<Casino> records = new ArrayList<>();
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

    public Casino buildRecord(String str) throws IllegalArgumentException {
        String[] fields = str.split(";");
        if (fields.length < 5) {
            throw new IllegalArgumentException("Not enough info");
        }
        int id;
        String nameOfCasino = fields[1];
        String game = fields[2];
        LocalDate Date;
        int rateValue;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
            Date = LocalDate.parse(fields[3], formatter);
            id = Integer.parseInt(fields[0]);
            rateValue = Integer.parseInt(fields[4]);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Can't parse record");
        }

        return new Casino(id,nameOfCasino,game,Date,rateValue);
    }

}