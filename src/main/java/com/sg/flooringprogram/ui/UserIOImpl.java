package com.sg.flooringprogram.ui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * @author Alexm
 */
public class UserIOImpl implements UserIO {

    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        print(prompt);
        double num = Double.parseDouble(sc.nextLine());
        return num;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double num = readDouble(prompt);
        while (min > num || max < num) {
            print("Number not in range.");
            num = readDouble(prompt);
        }
        return num;
    }

    @Override
    public float readFloat(String prompt) {
        print(prompt);
        float num = Float.parseFloat(sc.nextLine());
        return num;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float num = readFloat(prompt);
        while (min > num || max < num) {
            print("Number not in range.");
        }
        return num;
    }

    @Override
    public int readInt(String prompt) {
        while (true) {
            print(prompt);
            try {
                int num = Integer.parseInt(sc.nextLine());
                return num;
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid integer.");
            }
        }
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        while (true) {
            try {
                int num = readInt(prompt);
                while (min > num || max < num) {
                    print("Number not in range.");
                }
                return num;
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid integer.");
            }
        }
    }

    @Override
    public long readLong(String prompt) {
        print(prompt);
        long num = Long.parseLong(sc.nextLine());
        return num;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long num = readLong(prompt);
        while (min > num || max < num) {
            print("Number not in range.");
        }
        return num;
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        String userString = sc.nextLine();
        return userString;
    }

    @Override
    public BigDecimal readDecimal(String prompt) {
        boolean retry;
        BigDecimal bd = null;
        do {
            String value = null;
            try {
                value = readString(prompt);
                bd = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
                retry = false;
            } catch (NumberFormatException e) {
                System.out.println(value + "is not a valid entry.\n Please re-enter.");
                retry = true;
            }
        } while (retry);
        return bd;
    }

    @Override
    public LocalDate readDate() {
        boolean retry;
        LocalDate date = LocalDate.of(1900, 1, 1);

        do {
            try {
                int month = readInt("Please enter Month. (1-12)");
                int day = readInt("Please enter Day. (1-31)");
                int year = readInt("Please enter Year. (YYYY)");
                date = LocalDate.of(year, month, day);
                retry = false;
            } catch (DateTimeException e) {
                print("Invalid Date. Please re-enter.");
                retry = true;
            }
        } while (retry);
        return date;

    }
}
