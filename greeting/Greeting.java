package greeting;

import java.util.Scanner;

public class Greeting {
    public static void main(String[] args) {
        System.out.println("Enter your name:");
        String name = new Scanner(System.in).next();
        System.out.println("Hello, " + name);
    }
}
