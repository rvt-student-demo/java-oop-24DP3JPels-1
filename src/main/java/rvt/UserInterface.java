package rvt;
import java.util.Scanner;
public class UserInterface {
    private TodoList saraksts;
    private Scanner skeneris;
    public UserInterface(TodoList saraksts, Scanner skeneris) {
        this.saraksts = saraksts;
        this.skeneris = skeneris;
    }
    public void start() {
        while (true) {
            System.out.print("Command: ");
            String komanda = skeneris.nextLine();
            if (komanda.equals("stop")) {
                break;
            }
            if (komanda.equals("add")) {
                System.out.print("To add: ");
                String darbs = skeneris.nextLine();
                saraksts.add(darbs);
            } else if (komanda.equals("list")) {
                saraksts.print();
            } else if (komanda.equals("remove")) {
                System.out.print("Which one is removed? ");
                int id = Integer.valueOf(skeneris.nextLine());
                saraksts.remove(id);
            }
        }
    }
}