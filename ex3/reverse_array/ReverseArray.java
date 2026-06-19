package ex3.reverse_array;

import java.util.Scanner;

public class ReverseArray {
    public static void main(String[] args) {
        int size;
        int[] array;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Enter a size:");
            size = input.nextInt();
            if(size <= 0){
                System.out.println("Invalid size!");
            }
            if(size > 20){
                System.out.println("Size doesn't exceed 20!");
            }
        } while( size <= 0 || size > 20);
        array = new int[size];
        for(int i = 0; i < size; i++){
            System.out.print("Enter element " + (i + 1) + ": ");
            array[i] = input.nextInt();
        }

        System.out.printf("%-20s%s", "Elements in array: ", "");
        for (int i : array) {
            System.out.print(i + "\t");
        }

        for (int j = 0; j < array.length / 2; j++) {
            int temp = array[j];
            array[j] = array[size - 1 - j];
            array[size - 1 - j] = temp;
        }

        System.out.printf("\n%-20s%s", "Reverse array: ", "");
        for (int i : array) {
            System.out.print(i + "\t");
        }
    }

}
