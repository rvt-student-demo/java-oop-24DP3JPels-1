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

            // Pārbauda vai lietotājs vēlas beigt (q vai Q)
            if (input.charAt(0) == 'q' || input.charAt(0) == 'Q') {
                break;
            }

            try {
                // Pārvērt ievadi no string uz integer
                numerator = Integer.parseInt(input);

                System.out.print("Enter the divisor  : ");
                divisor = scan.nextInt();

                // Pārbauda vai dala ar 0
                if (divisor == 0) {
                    System.out.println("You can't divide " + numerator + " by 0");
                } else {
                    System.out.println(numerator + " / " + divisor + " is " + (numerator / divisor));
                }

            } catch (Exception e) {
                // Šis noķer nesakarīgus integer ievadus
                System.out.println("You entered bad data.");
                System.out.println("Please try again.");
                scan.nextLine(); // Notīra skenera buferi
            }
            
            System.out.println(); 
        }
    }
}