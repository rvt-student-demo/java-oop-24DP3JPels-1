import java.util.Scanner;

public class DivisionPractice {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        String input;
        int num, div;

        while (true) {
            System.out.print("Enter the numerator: ");
            input = scan.next();

            // Parbauda vai lietotajs grib beigt imantot charAT()
            if (input.charAt(0) == 'q' || input.charAt(0) == 'Q') {
                break;
            }

            try {
                // Parveido inputu no string uz integer
                num = Integer.parseInt(input);

                System.out.print("Enter the divisor  : ");
                div = scan.nextInt();

                // parbauda vai dalas ar 0
                if (div == 0) {
                    System.out.println("You can't divide " + num + " by 0");
                } else {
                    System.out.println(num + " / " + div + " is " + (num / div));
                }

            } catch (Exception e) {
                // šis noņer "glarch" vai citus integer erorus
                System.out.println("You entered bad data.");
                System.out.println("Please try again.");
                scan.nextLine(); // Clear the scanner buffer
            }
            
            System.out.println(); 
        }
    }
}