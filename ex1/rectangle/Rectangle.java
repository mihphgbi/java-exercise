package ex1.rectangle;

import java.util.Scanner;

public class Rectangle {
    static void main() {
     float width;
     float height;
     Scanner scanner = new Scanner(System.in);

     System.out.println("Enter width");
     width = scanner.nextFloat();
     System.out.println("Enter height");
     height = scanner.nextFloat();
     System.out.println("Area of ex1.rectangle is: " + (width * height));
    }
}
