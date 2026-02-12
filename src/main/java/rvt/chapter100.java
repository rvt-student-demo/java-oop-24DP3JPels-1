package rvt;
import java.util.Scanner;


public class chapter100 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input;
        int num, div;
        while (true) {
            System.out.print("Enter the numerator: ");
            input = scan.next();
            if (input.charAt(0) == 'q' || input.charAt(0) == 'Q') {
                break;
            }
            try {
                num = Integer.parseInt(input);
                System.out.print("Enter the divisor  : ");
                div = scan.nextInt();
                if (div == 0) {
                    System.out.println("You can't divide " + num + " by 0");
                } else {
                    System.out.println(num + " / " + div + " is " + (num / div));
                }
            } catch (Exception e) {  
                System.out.println("You entered bad data.");
                System.out.println("Please try again.");
                scan.nextLine(); 
            }
            System.out.println(); 
        }
    }
}