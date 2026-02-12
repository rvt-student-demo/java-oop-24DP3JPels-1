package rvt;
import java.util.Scanner;

public class UserInterface {
    private TodoList todoList;
    private Scanner scanner;

    public UserInterface(TodoList todoList, Scanner scanner) {
        this.todoList = todoList;
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            System.out.print("Command: ");
            String command = scanner.nextLine();

            if (command.equals("stop")) {
                break;
            }

            if (command.equals("add")) {
                System.out.print("To add: ");
                String task = scanner.nextLine();
                this.todoList.add(task);

            } else if (command.equals("list")) {
                this.todoList.print();

            } else if (command.equals("remove")) {
                System.out.print("Which one is removed? ");
                
                int id = Integer.parseInt(scanner.nextLine());
                this.todoList.remove(id);
            }
        }
    }
}

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