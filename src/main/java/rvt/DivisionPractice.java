package rvt;
import java.util.Scanner;

public class DivisionPractice {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input;
        int numerator, divisor, result;




        while (true) {
            System.out.print("Enter the numerator: ");
            input = scan.next();

            if (input.toLowerCase().charAt(0) == 'q') {
                break;
            }


            try {
                numerator = Integer.parseInt(input);

                System.out.print("Enter the divisor: ");
                divisor = scan.nextInt();


                
                if (divisor == 0) {
                    throw new ArithmeticException("zero");
                }

                result = numerator / divisor;
                System.out.println(numerator + " / " + divisor + " is " + result);

            } catch (NumberFormatException e) {
                
                System.out.println("Ievadiji nepareizus datus.");
                System.out.println("Megini velreiz.");
                scan.nextLine(); 
            } catch (ArithmeticException e) {

                System.out.println("You can't divide by zero.");
            }
            


            
            System.out.println(); 
        }
        
        scan.close();
    }
}