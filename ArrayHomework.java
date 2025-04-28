package Hw3_22000090.Hw2;

import java.util.Scanner;

public class ArrayHomework {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        do {
            System.out.print("Enter the number of items: ");
            n = sc.nextInt();
        } while (n <= 0);
        int[] arr = new int[n];
        System.out.print("Enter the value of all items (separated by space): ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        printArrayInStars(arr);
    }

    public static void printArrayInStars(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            printStars(arr[i],i);
            System.out.println();
        }
    }

    public static void printStars(int n, int k) {
        System.out.print(k + ": ");
        for (int i = 0; i < n; i++) {
            System.out.print("*");
        }
        System.out.print("(" + n + ")");
    }
}
