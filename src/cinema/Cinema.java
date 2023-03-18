package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        char[][] arr = new char[rows][seats];
        for (char[] chars : arr) {
            Arrays.fill(chars, 'S');
        }
        int[] stats = {0, 0, 0}; // index 0 number of purchased tickets, index 1 current income, index 2 total income
        if (rows * seats > 60) {
            stats[2] += (Math.round((double) rows / 2) * seats) * 8;
            stats[2] += ((rows / 2) * seats) * 10;
        } else {
            stats[2] += (rows * seats) * 10;
        }
        play(arr, rows, seats, stats);
    }

    public static void play(char[][] arr, int rows, int seats, int[] stats) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int action = scanner.nextInt();
            System.out.println();

            switch (action) {
                case 0 -> {
                    return;
                }
                case 1 -> draw(arr);
                case 2 -> buyTicket(arr, rows, seats, stats);
                case 3 -> statics(stats, rows, seats);
                default -> throw new RuntimeException(String.format("unknown menu command %d", action));
            }
        }
    }


    public static void buyTicket(char[][] arr, int rows, int seats, int[] stats) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int numRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int numSeat = scanner.nextInt();
        if ((numRow <= rows && numRow > 0) && (numSeat <= seats && numSeat > 0)) {
            if (arr[numRow - 1][numSeat - 1] != 'B') {
                arr[numRow - 1][numSeat - 1] = 'B';
                if (rows * seats > 60) {
                    if (numRow >= Math.round((double) rows / 2)) {
                        System.out.println("Ticket price: $8");
                        stats[1] += 8;
                    } else {
                        System.out.println("Ticket price: $10");
                        stats[1] += 10;
                    }
                } else {
                    System.out.println("Ticket price: $10");
                    stats[1] += 10;
                }
                stats[0]++;
            } else {
                System.out.println("That ticket has already been purchased!");
                buyTicket(arr, rows, seats, stats);
            }
        } else {
            System.out.println("Wrong input!");
            buyTicket(arr, rows, seats, stats);
        }
    }

    static void draw(char[][] arr) {
        System.out.println();
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= arr[0].length; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i + 1);
            for (char j : arr[i]) {
                System.out.print(" " + j);
            }
            System.out.println();
        }
    }

    static void statics(int[] stats, int rows, int seats) {
        System.out.println("Number of purchased tickets: " + stats[0]);
        System.out.printf("Percentage: %.2f", (float) stats[0] / (float) (rows * seats) * 100);
        System.out.println("%\nCurrent income: $" + stats[1]);
        System.out.println("Total income: $" + stats[2]);
    }

}