package lab1;

// Tính giai thừa của 1 số.
public class Factorial {
    public static void main(String[] args) {
        int n = 20;
        long factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        System.out.println("The Factorial " + n + " is: " + factorial);
    }
}
